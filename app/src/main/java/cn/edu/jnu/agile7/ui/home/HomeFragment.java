package cn.edu.jnu.agile7.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.databinding.FragmentHomeBinding;
import cn.edu.jnu.agile7.ui.SharedViewModel;
import cn.edu.jnu.agile7.ui.bill.Bill;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ImageButton returnImageButton;
    private ImageButton statisticsImagebutton;


    private SharedViewModel sharedViewModel;


    //    统计界面需要的适配器
    RecyclerView statisticsRecycleview;
    private Homeadapter homeadapter;
    private ArrayList<Statistics>statisticsArrayList = new ArrayList<>();
//    private ArrayList<Bill>billArrayList=new ArrayList<>();

    //    开始时间
    int selectedStartYear;
    int selectedStartMonth;

    //    截止时间
    int selectedEndYear;
    int selectedEndMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        点击返回按钮
        returnImageButton=rootView.findViewById(R.id.return_imageButton);
        returnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               TODO: 跳转到账目记录页面
            }
        });

        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        // 初始化起始时间NumberPicker
        NumberPicker npYear = rootView.findViewById(R.id.statistics_np_year);
        NumberPicker npMonth = rootView.findViewById(R.id.statistics_np_month);

        // 设置NumberPicker的范围
        npYear.setMinValue(year - 5);
        npYear.setMaxValue(year + 5);
        npYear.setValue(year);

//        如果起始和截止都选取0，就代表选一年一年的显示
        npMonth.setMinValue(0);
        npMonth.setMaxValue(12);
        npMonth.setValue(month + 1);

// 获取选定的日期
        selectedStartYear = npYear.getValue();
        selectedStartMonth = npMonth.getValue();

        npYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedStartYear=newVal;
            }
        });
        npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedStartMonth=newVal;
                Log.i("month",String.valueOf(newVal));
            }
        });


        // 初始化截止时间的NumberPicker
        NumberPicker npYear2 = rootView.findViewById(R.id.statistics_end_np_year);
        NumberPicker npMonth2 = rootView.findViewById(R.id.statistics_end_np_month);

        // 设置NumberPicker的范围,当前的前后100年
        npYear2.setMinValue(year - 5);
        npYear2.setMaxValue(year + 5);
        npYear2.setValue(year);

//        如果起始和截止都选取0，就代表一年一年的显示
        npMonth2.setMinValue(0);
        npMonth2.setMaxValue(12);
        npMonth2.setValue(month + 1);

        // 获取选定的日期
        selectedEndYear = npYear2.getValue();
        selectedEndMonth = npMonth2.getValue();

        npYear2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedEndYear=newVal;
            }
        });
        npMonth2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedEndMonth=newVal;
            }
        });

        statisticsRecycleview=rootView.findViewById(R.id.recycle_view_statistics);
        statisticsRecycleview.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getContext(),LinearLayoutManager.VERTICAL,false));
        homeadapter=new Homeadapter(statisticsArrayList,this.getActivity());
        statisticsRecycleview.setAdapter(homeadapter);
        homeadapter.notifyDataSetChanged();

        statisticsImagebutton=rootView.findViewById(R.id.statistics_imageButton);
        statisticsImagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.getBillList().observe(getViewLifecycleOwner(), billArrayList -> {
                    // 使用获取到的数据
                    Log.i("testlist",String.valueOf(billArrayList.size()));//可以正常显示
                    statisticsArrayList.clear();//每一次查询之前先清空
                    query(billArrayList);
                    statisticsRecycleview.setAdapter(homeadapter);
                    homeadapter.setStatisticsArrayList(statisticsArrayList);
                    homeadapter.notifyDataSetChanged();
                });
            }
        });
        return rootView;
    }

    //    查询，将找到的对应日期的账单添加到statisticsArrayList中
    public void query(ArrayList<Bill>billArrayList){
        double income=0;
        double expanditure=0;
        double sum=0;
        Statistics statistics;
        Bill bill;
        if(billArrayList!=null&&billArrayList.size()!=0){
//            只查询年份 如 2023.0-2024.0
            if(selectedStartMonth==0&&selectedEndMonth==0)
            {
//                从起始年份遍历到截止年份
                for(int k=selectedStartYear;k<=selectedEndYear;k++)
                {
                    for(int j=0;j<billArrayList.size();j++)
                    {
                        bill=billArrayList.get(j);
                        if(bill.getYear()==k){
                            if(bill.getPrice()>=0){
                                income+=bill.getPrice();
                            }
                            else{
                                expanditure+=bill.getPrice();
                            }
                        }
                    }
                    sum=(income+expanditure);
                    statistics=new Statistics(k,income,expanditure,sum);
                    statisticsArrayList.add(statistics);
                }
            }
//            如2022.1-2023.5
            else if(selectedStartMonth!=0&&selectedEndMonth!=0)
            {
//                同一年 如2022.5-2022.9
                if(selectedEndYear==selectedStartYear){
                    for(int i=selectedStartMonth;i<=selectedEndMonth;i++){
                        income=expanditure=sum=0;//清0，重新计算
                        for(int j=0;j<billArrayList.size();j++){
                            bill=billArrayList.get(j);
                            if(bill.getYear()==selectedStartYear&&bill.getMonth()==i){
                                Log.i("price",String.valueOf(bill.getPrice()));
                                if(bill.getPrice()>=0){
                                    income=income+bill.getPrice();
                                }
                                else{
                                    expanditure=expanditure+bill.getPrice();
                                }
                            }
                        }
                        sum=(income+expanditure);
                        statistics=new Statistics(selectedStartYear, i, income,expanditure,sum);
                        statisticsArrayList.add(statistics);
                    }
                }
//                如2022.1-2024.9
                else if(selectedStartYear<selectedEndYear)
                {
//                   先算2022.1-2023.12
                    for(int k=selectedStartYear;k<=selectedEndYear-1;k++)
                    {
                        for(int i=1;i<=12;i++){
                            income=expanditure=sum=0;//清0，重新计算
                            for(int j=0;j<billArrayList.size();j++){
                                bill=billArrayList.get(j);
                                if(bill.getYear()==k&&bill.getMonth()==i){
                                    if(bill.getPrice()>=0){
                                        income+=bill.getPrice();
                                    }
                                    else{
                                        expanditure+=bill.getPrice();
                                    }
                                }
                            }
                            sum=(income+expanditure);
                            statistics=new Statistics(k, i, income,expanditure,sum);
                            statisticsArrayList.add(statistics);
                        }
                    }
//                   再算 2024.1-2024.9
                    for(int i=1;i<=selectedEndMonth;i++)
                    {
                        for(int j=0;j<billArrayList.size();j++){
                            bill=billArrayList.get(j);
                            if(bill.getYear()==selectedEndYear&&bill.getMonth()==i){
                                if(bill.getPrice()>=0){
                                    income+=bill.getPrice();
                                }
                                else{
                                    expanditure+=bill.getPrice();
                                }
                            }
                        }
                        sum=(income+expanditure);
                        statistics=new Statistics(selectedEndYear, i, income,expanditure,sum);
                        statisticsArrayList.add(statistics);
                    }
                }
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}