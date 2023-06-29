package cn.edu.jnu.agile7.ui.Account;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AccountViewModel extends ViewModel {

    private ArrayList<Account> accounts = new ArrayList<>();
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}