package cn.edu.jnu.agile7.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.edu.jnu.agile7.ui.notifications.AccountFragment;
import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.dashboard.IncomeFragment;

public class AccountViewModel extends ViewModel {

    private ArrayList<Account> accounts = new ArrayList<>();
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}