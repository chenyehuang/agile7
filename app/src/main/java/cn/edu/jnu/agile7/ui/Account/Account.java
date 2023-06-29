package cn.edu.jnu.agile7.ui.Account;

import java.io.Serializable;

public class Account implements Serializable {
    private String name;
    private double amount;

    public Account(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
