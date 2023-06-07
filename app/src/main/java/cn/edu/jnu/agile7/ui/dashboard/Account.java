package cn.edu.jnu.agile7.ui.dashboard;

import java.io.Serializable;
import java.util.UUID;

public class Account implements Serializable {
    private String Type;         //收入or支出
    private String category;     //哪种账目
    private double money;           //金额
    private String account;      //账户
    private int year;
    private int month;
    private int day;
    private String title;
    private String remake;
    private UUID id;            //用于搜集界面那里点击删除后返回主界面，然后主界面也要删除(需要用这个id遍历检索才能找到)

    public Account(String type, String category, double money, String account, int year, int month, int day, String title, String remake) {
        Type = type;
        this.category = category;
        this.money = money;
        this.account = account;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.remake = remake;
        //极小概率会重复
        this.id= UUID.randomUUID();
    }

    public void setType(String type) {
        Type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getType() {
        return Type;
    }

    public String getCategory() {
        return category;
    }

    public double getMoney() {
        return money;
    }

    public String getAccount() {
        return account;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public String getRemake() {
        return remake;
    }

    public UUID getId(){
        return id;
    }
}
