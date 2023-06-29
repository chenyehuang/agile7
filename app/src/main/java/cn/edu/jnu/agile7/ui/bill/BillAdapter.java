package cn.edu.jnu.agile7.ui.bill;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.edu.jnu.agile7.MainActivity;
import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.ui.dashboard.Bill;

/**
 * @author Administrator
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyHolder>{
    //设置context，让其他界面调用
    private Context context;
    private View rootView;
    private ArrayList<Bill> billArrayList;
    private ImageButton imageButton;
    private ImageButton button_edit;
    DataServer dataServer = new DataServer();
    NavController navController;

    //获取列表
    public ArrayList<Bill> getList(){
        return this.billArrayList;
    }
    public void setList(ArrayList<Bill>billArrayList){this.billArrayList=billArrayList;}

    //待会在activity的onCreate中需要用到
    public BillAdapter(ArrayList<Bill> billArrayList, Context context, View rootView) {
        this.billArrayList=billArrayList;//从主页传过来的
        this.context = context;//因为和主页分离了，所以需要获取主页上下文
        Log.i("add and edit", "BillAdapter的context" + context);
        this.rootView = rootView;
        if(context instanceof MainActivity) {
            this.navController = MainActivity.navController;
            Log.i("add and edit", "context正常");

        }
    }

    @NonNull
    @Override
    public BillAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //定义布局
        View v1=View.inflate(context, R.layout.accountitem,null);
        //或者View v1= LayoutInflater.from(xxActivity.this).inflate(R.layout.itemlayout,parent,false);
        return new MyHolder(v1);
    }
    @Override
    public void onBindViewHolder(@NonNull BillAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.gettextviewBillName().setText(billArrayList.get(position).getTitle());
        holder.gettextviewYear().setText(String.valueOf(billArrayList.get(position).getYear()));
        holder.gettextviewMonth().setText(String.valueOf(billArrayList.get(position).getMonth()));
        holder.gettextviewDay().setText(String.valueOf(billArrayList.get(position).getDay()));
        holder.gettextviewAmount().setText(String.valueOf(billArrayList.get(position).getMoney()));

    }
    public void removeData(int position){
        billArrayList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        //有可能删除中间的item会出现错乱，所以下面changed一次
        notifyDataSetChanged();
        dataServer.Save(context, billArrayList);
    }
    @Override
    public int getItemCount() {
        return billArrayList==null?0:billArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        //账单名
        private TextView billName;
        private TextView year;
        private TextView month;
        private TextView day;
        private TextView amount;        //账单金额

        public MyHolder(@NonNull View itemView) {
            super(itemView);////父类容器，不调用可能有些事件读取不到 和button的重写方法一样(如果是通过继承实现的话)都需要super(View)
            billName=itemView.findViewById(R.id.text_account_name);
            year=itemView.findViewById(R.id.text_account_year);
            month=itemView.findViewById(R.id.text_account_month);
            day=itemView.findViewById(R.id.text_account_day);
//            金额
            amount=itemView.findViewById(R.id.text_account_amount);
//            删除按钮
            imageButton=itemView.findViewById(R.id.imagebutton_delete);
//            编辑按钮
            button_edit = itemView.findViewById(R.id.button_edit);

            //编辑数据
            button_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("add and edit", "Clicked");
                    int position = getBindingAdapterPosition();
                    Bundle args = new Bundle();
                    args.putInt("myArg", position);
                    Log.i("add and edit", position +" in adapter发送端");
                    if(navController == null){
                        Log.i("add and edit", "navController为null");
                        navController = MainActivity.navController;
                    }
                    Log.i("add and edit", "BillAdapter里面的navController存在" + (navController != null));
                    navController.navigate(R.id.navigation_dashboard, args);
                    Log.i("add and edit", "完成");
                    notifyDataSetChanged();
                }
            });
            //删除数据
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    removeData(position);
                    Log.i("billList",String.valueOf(billArrayList.size())+"adapter");
                    //删除后更新文件数据，即保存数据
                }
            });
        }
        public TextView gettextviewBillName() {return billName;}
        public TextView gettextviewYear() {return year;}
        public TextView gettextviewMonth() {return month;}
        public TextView gettextviewDay() {return day;}
        public TextView gettextviewAmount() {return amount;}
    }

}


