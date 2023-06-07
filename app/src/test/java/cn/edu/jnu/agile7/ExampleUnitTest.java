package cn.edu.jnu.agile7;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.bill.BillFragment;
import cn.edu.jnu.agile7.ui.bill.DataServer;
import cn.edu.jnu.agile7.ui.dashboard.Account;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ArrayList<Account> accountArrayList = new ArrayList<>();
    @Before
    public void reset() {
        Account account=new Account("支出","餐饮",-1000.0,"支付宝",2021,5,20,"美团外卖","好吃");
        Account account2=new Account("支出","餐饮",-100.0,"支付宝",2022,5,20,"美团外卖2","好吃");
        Account account3=new Account("支出","餐饮",-10.0,"支付宝",2023,5,20,"美团外卖3","好吃");
        accountArrayList.add(0,account);
        accountArrayList.add(1,account2);
        accountArrayList.add(2,account3);
    }
    @Test
    public void addition_isCorrect() {
        int position = 1;
        changeData(position);
        assertEquals(-99, accountArrayList.get(position).getMoney());
    }

    private void changeData(int position) {
    }
}