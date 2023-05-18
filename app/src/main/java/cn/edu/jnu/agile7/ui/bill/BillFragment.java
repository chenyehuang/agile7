package cn.edu.jnu.agile7.ui.bill;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.databinding.FragmentDashboardBinding;
public class BillFragment extends Fragment {
    private FragmentDashboardBinding binding;

    private RecyclerView recycleViewbill;
//    主要用的adapter
    private BillAdapter billadapter;
    private ArrayList<Bill> billList = new ArrayList<>();

//    存储搜索数据的列表
    private ArrayList<Bill>filterbills;
    private SearchView searchView;
//    用于搜索功能的适配器,会同步搜索内容更新列表
    private BillSearchAdapter billSearchAdapter;

    //    初始化列表测试数据
    public void initListdata(){
        Bill bill=new Bill("美团外卖","食物","测试一下",2020,5,18,"小明", 1000.0);
        Bill bill2=new Bill("美团外卖2","食物","测试一下",2020,5,18,"小明", 100.0);
        Bill bill3=new Bill("美团外卖3","食物","测试一下",2020,5,18,"小明", 10.0);
        billList.add(0,bill);
        billList.add(1,bill2);
        billList.add(2,bill3);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BillViewModel billViewModel =
                new ViewModelProvider(this).get(BillViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);

        initListdata();
        searchView=rootView.findViewById(R.id.account_search);
        search();
        recycleViewbill=rootView.findViewById(R.id.recycle_view_account);
        //下面一行如果用模板自带的 View root = binding.getRoot()初始化会报空指针错误，所以注释掉了
        recycleViewbill.setLayoutManager(new LinearLayoutManager(BillFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));//true和false有区别,第三个参数表示是否反转,
        billadapter = new BillAdapter(billList,this.getActivity());
        recycleViewbill.setAdapter(billadapter);


        return rootView;
    }

//    自定义搜索函数
    public void search(){
        searchView.setQueryHint("搜索账单名字");
        //显示提交按钮，而不是需要按回车
        searchView.setSubmitButtonEnabled(true);
//        先获取context，才能getSystemService
        SearchManager searchManager = (SearchManager) this.requireContext().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.requireActivity().getComponentName()));
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(BillFragment.this.getContext(), "关闭", Toast.LENGTH_SHORT).show();
                //关闭搜索功能后让recyclerview重新恢复原来的适配器
                recycleViewbill.setAdapter(billadapter);
//                恢复原来的数据
                billadapter.notifyDataSetChanged();

                //如果是return true，会导致关闭不了
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            //每次修改的文本在newtext
            public boolean onQueryTextChange(String newText) {
                filterbills=filter(billList,newText);
//                在搜索的时候也会操作billadapter的数据，所以传入billadapter
                billSearchAdapter=new BillSearchAdapter(filterbills,BillFragment.this.getContext(),billadapter);
                //更换成 搜索适配器
                recycleViewbill.setAdapter(billSearchAdapter);
//                更新搜索内容
                billSearchAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    //搜索的过滤器
    private ArrayList<Bill>filter(ArrayList<Bill>strings,String text){
        filterbills = new ArrayList<>();
        for (Bill bill: billadapter.getList()){
            if (bill.getBillName().contains(text)) {
                filterbills.add(bill);
            }
            else if (bill.getPrice().equals(Double.valueOf(text))) {
                filterbills.add(bill);
            }
            else if (bill.getBillNote().contains(text)) {
                filterbills.add(bill);
            }
//            账户account
            else if (bill.getAccount().contains(text)) {
                filterbills.add(bill);
            }
            else if (bill.getYear()==Integer.parseInt(text)){
                filterbills.add(bill);
            }
            else if (bill.getMonth()==Integer.parseInt(text)){
                filterbills.add(bill);
            }
            else if (bill.getDay()==Integer.parseInt(text)){
                filterbills.add(bill);
            }
        }
        return filterbills;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}