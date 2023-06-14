package cn.edu.jnu.agile7;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.bill.DataServer;
import cn.edu.jnu.agile7.ui.dashboard.Bill;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EditBillUnitTest {
    ArrayList<Bill> accountArrayList = new ArrayList<>();
    //DataServer dataServer = new DataServer();
    private Bill account_expend;

    @Before
    public void reset() {
        Bill account=new Bill("支出","餐饮",-1000.0,"支付宝",2021,5,20,"美团外卖","好吃");
        Bill account2=new Bill("支出","餐饮",-100.0,"支付宝",2022,5,20,"美团外卖2","好吃");
        Bill account3=new Bill("支出","餐饮",-10.0,"支付宝",2023,5,20,"美团外卖3","好吃");
        accountArrayList.add(0,account);
        accountArrayList.add(1,account2);
        accountArrayList.add(2,account3);
    }
    @Test
    public void addition_isCorrect() {
        int position = 1;
        editData(position);
        //accountArrayList = dataServer.Load(InstrumentationRegistry.getInstrumentation().getTargetContext());
        assertEquals(-123.0, accountArrayList.get(position).getMoney(), 10e-2);
        assertEquals("餐饮", accountArrayList.get(position).getCategory());
        assertEquals("账户2", accountArrayList.get(position).getAccount());
    }

    private void editData(int position) {
        account_expend = new Bill("支出", "餐饮", (-1.0) * 123, "账户2", 2003, 1, 1, "测试", "ceshi");
        accountArrayList.set(position, account_expend);
        //dataServer.Save(InstrumentationRegistry.getInstrumentation().getTargetContext(), accountArrayList);
    }
    @Test
    public void deleteBillTest() {
        int position = 1;
        removeData(position);
        assertEquals(2, accountArrayList.size());
    }
    public void removeData(int position){
        accountArrayList.remove(position);
    }
}