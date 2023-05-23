package cn.edu.jnu.agile7.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.bill.Bill;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Bill>> billList = new MutableLiveData<>();

    public void setBillList(ArrayList<Bill> list) {
        billList.setValue(list);
    }

    public LiveData<ArrayList<Bill>> getBillList() {
        return billList;
    }
}


