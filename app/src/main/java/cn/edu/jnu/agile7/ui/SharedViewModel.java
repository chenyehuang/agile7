package cn.edu.jnu.agile7.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


import cn.edu.jnu.agile7.ui.dashboard.Account;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Account>> Accountlist = new MutableLiveData<>();

    public void setBillList(ArrayList<Account> list) {
        Accountlist.setValue(list);
    }

    public LiveData<ArrayList<Account>> getBillList() {
        return Accountlist;
    }
}


