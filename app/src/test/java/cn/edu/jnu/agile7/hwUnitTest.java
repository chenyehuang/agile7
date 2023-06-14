package cn.edu.jnu.agile7;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.dashboard.Bill;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class hwUnitTest {
    ArrayList<Bill> accountArrayList;

    @Before
    public void setup() throws Exception{
        accountArrayList=new ArrayList<>();
    }

    @Test
    public void test_add(){
        int len=accountArrayList.size();
        String category="工资".toString();
        double money_number=100;
        String select_account="工商银行";
        int selectedYear=2023;
        int selectedMonth=5;
        int selectedDay=8;
        String remake_string="好吃";
        String title_string="外卖";
        Bill account_income = new Bill("收入", category, money_number, select_account, selectedYear, selectedMonth, selectedDay, title_string, remake_string);
        accountArrayList.add(account_income);
        assertEquals(len+1,accountArrayList.size());
        assertEquals("收入",accountArrayList.get(len).getType());
        assertEquals("工资",accountArrayList.get(len).getCategory());
        assertEquals(100,accountArrayList.get(len).getMoney(),1e-7);
    }
}