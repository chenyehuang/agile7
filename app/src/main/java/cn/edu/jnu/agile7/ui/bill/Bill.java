package cn.edu.jnu.agile7.ui.bill;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;
import java.util.UUID;

//viewmodel:系统模板自带的
public class BillViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<String> mText;

    public BillViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bill fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

class Bill {
    //每一个账单有一个特定的id
    UUID id;
    //账单名
    String billName;
    //账单类型
    String type;
    //账单备注
    String billNote;
    int year;
    int month;
    int day;
    //账户
    String account;
    //账单金额 ＞0:收入 ＜0:支出
    double price;

    //    构造函数
    public Bill(){
        ;
    }

    public Bill(String billName, String type, String billNote, int year, int month, int day, String account, double price) {
        this.billName = billName;
        this.type = type;
        this.billNote = billNote;
        this.year = year;
        this.month = month;
        this.day = day;
        this.account = account;
        this.price = price;
        //极小概率会重复
        this.id= UUID.randomUUID();
    }

    public String getBillName() {
        return billName;
    }
    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBillNote() {
        return billNote;
    }
    public void setBillNote(String billNote) {
        this.billNote = billNote;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    public UUID getId(){
        return id;
    }
}

