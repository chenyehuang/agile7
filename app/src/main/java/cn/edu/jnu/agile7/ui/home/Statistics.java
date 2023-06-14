package cn.edu.jnu.agile7.ui.home;
/**
 * @author Administrator
 */
public class Statistics {
    int year;
    int month;
    double income;
    double expanditure;
    double netincome;

    public Statistics(int year, int month, double income, double expanditure, double netincome) {
        this.year = year;
        this.month = month;
        this.income = income;
        this.expanditure = expanditure;
        this.netincome = netincome;
    }

//有可能是按年份查找
    public Statistics(int year, double income, double expanditure, double netincome) {
        this.year = year;
        this.income = income;
        this.expanditure = expanditure;
        this.netincome = netincome;
        this.month=0;//代表没有
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpanditure() {
        return expanditure;
    }

    public void setExpanditure(double expanditure) {
        this.expanditure = expanditure;
    }

    public double getNetincome() {
        return netincome;
    }

    public void setNetincome(double netincome) {
        this.netincome = netincome;
    }
}
