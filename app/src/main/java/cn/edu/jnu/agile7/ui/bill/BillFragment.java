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
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.databinding.FragmentDashboardBinding;
import cn.edu.jnu.agile7.ui.SharedViewModel;
import cn.edu.jnu.agile7.ui.dashboard.Account;

public class BillFragment extends Fragment {
    private SharedViewModel sharedViewModel;

    private RecyclerView recycleViewbill;
//    主要用的adapter
    private BillAdapter billadapter;

//账目记录界面的列表
    private ArrayList<Account> accountArrayList = new ArrayList<>();

//    存储搜索数据的列表
    private ArrayList<Account>filterbills;
    private SearchView searchView;

//    用于搜索功能的适配器,会同步搜索内容更新列表
    private BillSearchAdapter billSearchAdapter;
    private DataServer dataServer=new DataServer();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        从文件中加载数据
        accountArrayList=dataServer.Load(BillFragment.this.getContext());
        Log.i("billList",String.valueOf(accountArrayList.size())+"onCreate");//还是为0，data.dat为空？？？？？》

        Account account=new Account("支出","餐饮",-1000.0,"支付宝",2021,5,20,"美团外卖","好吃");
        Account account2=new Account("支出","餐饮",-100.0,"支付宝",2022,5,20,"美团外卖2","好吃");
        Account account3=new Account("支出","餐饮",-10.0,"支付宝",2023,5,20,"美团外卖3","好吃");

//        如果从文件中加载的数据长度为0，自动先加入三条数据
        if(accountArrayList.size()==0) {
            accountArrayList.add(0,account);
            accountArrayList.add(1,account2);
            accountArrayList.add(2,account3);
        }

        Log.i("billList",String.valueOf(accountArrayList.size())+"onCreate2");
//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        sharedViewModel.setBillList(accountArrayList);

        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);

        searchView=rootView.findViewById(R.id.account_search);
        search();
        recycleViewbill=rootView.findViewById(R.id.recycle_view_account);
        //下面一行如果用模板自带的 View root = binding.getRoot()初始化会报空指针错误，所以注释掉了
        recycleViewbill.setLayoutManager(new LinearLayoutManager(BillFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));//true和false有区别,第三个参数表示是否反转,
        billadapter = new BillAdapter(accountArrayList,this.getActivity());
        recycleViewbill.setAdapter(billadapter);
        billadapter.notifyDataSetChanged();

        Log.i("billList", accountArrayList.size() +"onCreate3");
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
                filterbills=filter(accountArrayList,newText);
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
    private ArrayList<Account>filter(ArrayList<Account>strings,String text){
        filterbills = new ArrayList<>();
        for (Account bill: billadapter.getList()){
            if (bill.getTitle().contains(text)) {
                filterbills.add(bill);
            }
            else if (bill.getRemake().contains(text)) {
                filterbills.add(bill);
            }
//            账户account
            else if (bill.getAccount().contains(text)) {
                filterbills.add(bill);
            }
            else if (bill.getCategory().contains(text)) {
                filterbills.add(bill);
            }
            else if (bill.getType().contains(text)) {
                filterbills.add(bill);
            }
//            TODO:下面是搜索数字，但是还没对非数字进行处理，所以输入非数字搜索会报错
            else if (bill.getMoney()==(Double.parseDouble(text))) {
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
        Log.i("billList",String.valueOf(accountArrayList.size())+"onDestroy");
        accountArrayList= billadapter.getList();
        Log.i("billList",String.valueOf(accountArrayList.size())+"onDestroy2");
        dataServer.Save(BillFragment.this.getContext(),accountArrayList);
        super.onDestroyView();
    }
}