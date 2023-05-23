package cn.edu.jnu.agile7.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.ui.bill.Bill;

/**
 * @author Administrator
 */
public class Homeadapter extends RecyclerView.Adapter<Homeadapter.MyHolder> {

    private ArrayList<Bill>billArrayList=new ArrayList<>();

    private Context context;
    private ArrayList<Statistics> statisticsArrayList;

    public Homeadapter(ArrayList<Statistics> statisticsArrayList, Context context){
        this.statisticsArrayList=statisticsArrayList;
        this.context = context;
    }

    //获取列表
    public ArrayList<Statistics> getStatisticsArrayList(){
        return this.statisticsArrayList;
    }
    public void setStatisticsArrayList(ArrayList<Statistics>statisticsArrayList){this.statisticsArrayList=statisticsArrayList;}

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //定义布局
        View v1=View.inflate(context, R.layout.statistics_item,null);
        return new MyHolder(v1);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.getIncome().setText(String.valueOf(statisticsArrayList.get(position).getIncome()));
        holder.getExpanditures().setText(String.valueOf(statisticsArrayList.get(position).getExpanditure()));
        holder.getnetincome().setText(String.valueOf(statisticsArrayList.get(position).getNetincome()));
        if(statisticsArrayList.get(position).getMonth()!=0)
        {
            String year= String.valueOf(String.valueOf(statisticsArrayList.get(position).getYear()));
            String month= String.valueOf(String.valueOf(statisticsArrayList.get(position).getMonth()));
            holder.getTime().setText(year+" "+month);
        }
        else{
            String year= String.valueOf(String.valueOf(statisticsArrayList.get(position).getYear()));
            holder.getTime().setText(year);
        }
    }

    @Override
    public int getItemCount() {
        return statisticsArrayList.size();
    }
    //    容纳数据
    class MyHolder extends RecyclerView.ViewHolder{
        private TextView time;
        //        收入
        private TextView income;
        //        支出
        private TextView expanditures;
        //        净收入
        private TextView netincome;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time_textView);
            income=itemView.findViewById(R.id.income_changetext);
            expanditures=itemView.findViewById(R.id.expanditure_changetext);
            netincome=itemView.findViewById(R.id.netincome_changetext);
        }
        public TextView getTime() {
            return time;
        }
        public TextView getIncome() {
            return income;
        }
        public TextView getExpanditures() {
            return expanditures;
        }
        public TextView getnetincome() {
            return netincome;
        }
    }
}

