package cn.edu.jnu.agile7.ui.dashboard;
public class Account {
    private String Type;         //收入or支出
    private String category;     //哪种账目
    private int money;           //金额
    private String account;      //账户
    private int year;
    private int month;
    private int day;
    private String title;
    private String remake;

    public Account(String type, String category, int money, String account, int year, int month, int day, String title, String remake) {
        Type = type;
        this.category = category;
        this.money = money;
        this.account = account;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.remake = remake;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMoney(int money) {
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

    public int getMoney() {
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
}
