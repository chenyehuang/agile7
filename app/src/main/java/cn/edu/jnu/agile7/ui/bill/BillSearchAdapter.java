package cn.edu.jnu.agile7.ui.bill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.UUID;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.ui.dashboard.Account;

/**
 * @author Administrator
 */
public class BillSearchAdapter extends RecyclerView.Adapter<BillSearchAdapter.MyHolder> {

    private Context context;
    private ArrayList<Account> billArrayList;
    private ImageButton imageButton;
    private BillAdapter billAdapter;

    BillSearchAdapter(ArrayList<Account>billArrayList, Context context, BillAdapter billAdapter){//待会在activity的oncreate中需要用到
        //从主页传过来的list
        this.billArrayList=billArrayList;
        //因为和主页分离了，所以需要获取主页上下文
        this.context=context;
        this.billAdapter=billAdapter;
    }

    @NonNull
    @Override
    public BillSearchAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //定义布局
        View v1=View.inflate(context, R.layout.accountitem,null);
        return new BillSearchAdapter.MyHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull BillSearchAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.gettextviewBillName().setText(billArrayList.get(position).getTitle());
        holder.gettextviewYear().setText(String.valueOf(billArrayList.get(position).getYear()));
        holder.gettextviewMonth().setText(String.valueOf(billArrayList.get(position).getMonth()));
        holder.gettextviewDay().setText(String.valueOf(billArrayList.get(position).getDay()));
        holder.gettextviewAmount().setText(String.valueOf(billArrayList.get(position).getMoney()));
        //        搜索时候也可以点击按钮删除数据
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID id=billArrayList.get(position).getId();
                removedata_search(position);
                //得到item位置，在主页面那里也删除
                for(int i=0;i<billAdapter.getList().size();i++){
                    if (id==billAdapter.getList().get(i).getId()){
                        billAdapter.removeData(i);
                        // 删除后更新文件数据，即保存数据a
                        new DataServer().Save(context,billAdapter.getList());
                        break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return billArrayList==null?0:billArrayList.size();
    }

    //在搜索"窗口页面"删除数据
    public void removedata_search(int position){
        billArrayList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    //public内部类，不用也ok  //viewholder:携带xx布局的数据,holder:存东西的
    class MyHolder extends RecyclerView.ViewHolder{
        //账单名
        private TextView billName;
        private TextView year;
        private TextView month;
        private TextView day;
        //账单金额
        private TextView amount;

        public MyHolder(@NonNull View itemView) {
            super(itemView);////父类容器，不调用可能有些事件读取不到 和button的重写方法一样(如果是通过继承实现的话)都需要super(View)
            billName=itemView.findViewById(R.id.text_account_name);
            year=itemView.findViewById(R.id.text_account_year);
            month=itemView.findViewById(R.id.text_account_month);
            day=itemView.findViewById(R.id.text_account_day);
            amount=itemView.findViewById(R.id.text_account_amount);
            imageButton=itemView.findViewById(R.id.imagebutton_delete);
        }
        public TextView gettextviewBillName() {return billName;}
        public TextView gettextviewYear() {return year;}
        public TextView gettextviewMonth() {return month;}
        public TextView gettextviewDay() {return day;}
        public TextView gettextviewAmount() {return amount;}
    }

    //为了搜索功能:给适配器设置过滤器
    public void setFilter(ArrayList<Account> filterBills){
        //动态数组只要创建了就不会为null了，神奇，必须用这个方法
        if(filterBills.isEmpty()){
            //相当于没改变
            return;
        }
        billArrayList=filterBills;
    }
}
