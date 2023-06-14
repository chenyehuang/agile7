package cn.edu.jnu.agile7.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


import cn.edu.jnu.agile7.ui.dashboard.Bill;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Bill>> Accountlist = new MutableLiveData<>();

    public void setBillList(ArrayList<Bill> list) {
        Accountlist.setValue(list);
    }

    public LiveData<ArrayList<Bill>> getBillList() {
        return Accountlist;
    }
}


