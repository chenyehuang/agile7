package cn.edu.jnu.agile7.ui.Account;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.PopupMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.databinding.FragmentHomeBinding;


public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private View view;
    private FragmentHomeBinding binding;
    private AccountFragment.CustomAdapter recyclerViewAdapter;
    private ArrayList<Account> accountsShow =  new ArrayList<>();
    private int real_position=1;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_account, container, false);
        accountsShow = new AccountServer().Load(this.getContext());
        initAddButton();
        initRecyclerView();
        recyclerViewAdapter.notifyDataSetChanged();
        return view;
    }

    private final ActivityResultLauncher<Intent> accountAddLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (null != result) {
                    Intent intent=result.getData();
                    if(result.getResultCode()==AccountAddActivity.RESULT_CODE_ADD)
                    {
                        AddAccount(intent);
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> accountEditLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (null != result) {
                    Intent intent=result.getData();
                    EditAccount(intent);
//                    if(result.getResultCode()==AccountEditActivity.RESULT_CODE_EDIT)
//                    {
//                        EditAccount(intent);
//                    }
                }
            }
    );

    public void AddAccount(Intent intent){
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("account_name");
        double money = bundle.getDouble("account_money");
        accountsShow.add(new Account(name, money));
        new AccountServer().Save(this.getContext(), accountsShow);
        recyclerViewAdapter.notifyItemChanged(accountsShow.size());
    }

    public void EditAccount(Intent intent){
//        Account account = accountsShow.get(position[0]);
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("account_name");
        double money = bundle.getDouble("account_money");
        accountsShow.get(real_position).setAmount(money);
        accountsShow.get(real_position).setName(name);
        new AccountServer().Save(this.getContext(), accountsShow);
        recyclerViewAdapter.notifyItemChanged(real_position);
    }

    public void get_position(int position){
        real_position = position;

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fg_account);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new AccountFragment.CustomAdapter(accountsShow);
        recyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = recyclerView.getChildAdapterPosition(v); // 获取点击的位置
                editItem(clickedPosition);
//                Intent intent = new Intent(getActivity(), AccountDetailsActivity.class);
//                accountAddLaunch.launch(intent);
            }
        });
        recyclerViewAdapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initAddButton() {
        Button addButton = view.findViewById(R.id.button_add_account);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountAddActivity.class);
                accountAddLaunch.launch(intent);
            }
        });
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private View.OnClickListener onClickListener;
        private View.OnLongClickListener onLongClickListener;
        private List<Account> localDataSet;

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textViewName;
            private final TextView textViewMoney;
            private final ConstraintLayout constraintLayout;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                constraintLayout = view.findViewById(R.id.constraintLayout_account_item);
                textViewName = view.findViewById(R.id.textView_account_item_name);
                textViewMoney = view.findViewById(R.id.textView_account_item_money);

//                constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        showDeleteDialog(getAdapterPosition()); // 弹出删除对话框
//                        return true;
//                    }
//                });
            }

            public TextView getTextViewName() {
                return textViewName;
            }

            public TextView getTextViewMoney() {
                return textViewMoney;
            }

            public ConstraintLayout getConstraintLayout() {
                return constraintLayout;
            }
        }


        public CustomAdapter(List<Account> dataSet) {
            localDataSet = dataSet;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_account, viewGroup, false);
            return new AccountFragment.CustomAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
            viewHolder.getTextViewName().setText(localDataSet.get(position).getName());
            System.out.println(localDataSet.get(position).getName());
            viewHolder.getTextViewMoney().setText(String.valueOf(localDataSet.get(position).getAmount()));
            ConstraintLayout constraintLayout = viewHolder.getConstraintLayout();
            constraintLayout.setOnClickListener(onClickListener);
            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // 创建弹出菜单
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_account_item, popupMenu.getMenu());

                    // 设置菜单项点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_edit:
                                    // 执行修改操作
                                    editItem(position);
                                    get_position(position);
                                    return true;
                                case R.id.menu_delete:
                                    // 执行删除操作
                                    deleteItem(position);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });

                    // 显示弹出菜单
                    popupMenu.show();
                    return true;
                }
            });

//            constraintLayout.setOnLongClickListener(onLongClickListener);
//            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    showDeleteDialog(viewHolder.getAdapterPosition()); // 弹出删除对话框
//                    return true;
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
            this.onLongClickListener = onLongClickListener;
        }
    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("删除")
                .setMessage("确定要删除这个项吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(position); // 删除选中项
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // 取消删除，关闭对话框
                    }
                })
                .create()
                .show();
    }

    public void editItem(int position) {
        // 获取要编辑的账户对象
        Account account = accountsShow.get(position);

        // 创建编辑账户的 Intent
        Intent intent = new Intent(getActivity(), AccountEditActivity.class);
        // 将要编辑的账户信息传递给编辑界面
        intent.putExtra("account_name", account.getName());
        intent.putExtra("account_money", account.getAmount());

        // 启动编辑界面，并注册结果回调
        accountEditLaunch.launch(intent);
    }



    public void deleteItem(int position){
        accountsShow.remove(position);
        new AccountServer().Save(this.getContext(), accountsShow);
        recyclerViewAdapter.notifyItemRemoved(position);
    }




    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Override
    public void onDestroyView() {
        new AccountServer().Save(this.getContext(), accountsShow);
        binding = null;
        super.onDestroyView();
    }

}