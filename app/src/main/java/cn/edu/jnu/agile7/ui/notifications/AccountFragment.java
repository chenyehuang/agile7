package cn.edu.jnu.agile7.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private View view;
    private FragmentHomeBinding binding;
    private AccountFragment.CustomAdapter recyclerViewAdapter;
    private ArrayList<Account> accountsShow;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_account, container, false);
        accountsShow = new ArrayList<>();
        Account account = new Account("工商银行", 1000);
        accountsShow.add(account);
        accountsShow.add(account);
        accountsShow.add(account);

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
                    }
                }
            }
    );



    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fg_account);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new AccountFragment.CustomAdapter(accountsShow);
        recyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.getTextViewName().setText(localDataSet.get(position).getName());
            System.out.println(localDataSet.get(position).getName());
            viewHolder.getTextViewMoney().setText(String.valueOf(localDataSet.get(position).getAmount()));
            ConstraintLayout constraintLayout = viewHolder.getConstraintLayout();
            constraintLayout.setOnClickListener(onClickListener);
            constraintLayout.setOnLongClickListener(onLongClickListener);
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

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}