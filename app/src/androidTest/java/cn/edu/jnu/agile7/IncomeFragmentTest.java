package cn.edu.jnu.agile7;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.bytebuddy.dynamic.TypeResolutionStrategy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cn.edu.jnu.agile7.ui.dashboard.Bill;
import cn.edu.jnu.agile7.ui.dashboard.IncomeFragment;

@RunWith(AndroidJUnit4.class)
public class IncomeFragmentTest {
    private IncomeFragment incomeFragment;
    private MainActivity activity;

    @Mock
    private NavController navController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(mainActivity -> {
            activity = mainActivity;
            Fragment fragment = mainActivity.getSupportFragmentManager().getPrimaryNavigationFragment();
            if (fragment instanceof IncomeFragment) {
                incomeFragment = (IncomeFragment) fragment;
                // 设置导航控制器
                when(Navigation.findNavController(incomeFragment.getView())).thenReturn(navController);
            }
        });

    }

    @After
    public void tearDown() throws Exception {
        incomeFragment = null;
        activity = null;
        navController = null;
    }

    @Test
    public void testBillAdd() {
        activity.runOnUiThread(() -> {
            //设置点击组件标志位
            incomeFragment.clickedComponentFlag[0] = 1;

            //设置金额
            EditText amountMoney = incomeFragment.getView().findViewById(R.id.income_money);

            //设置标题输入框
            EditText title = incomeFragment.getView().findViewById(R.id.income_title);

            //设置备注输入框
            EditText remake = incomeFragment.getView().findViewById(R.id.income_remark);

            //设置日期选择器
            NumberPicker npYear = incomeFragment.getView().findViewById(R.id.income_np_year);
            NumberPicker npMonth = incomeFragment.getView().findViewById(R.id.income_np_month);
            NumberPicker npDay = incomeFragment.getView().findViewById(R.id.income_np_day);

            npYear.setValue(2023);
            npMonth.setValue(6);
            npDay.setValue(10);

            //运行BillAdd方法
            incomeFragment.BillAdd();
            // 验证导航控制器的导航方法是否被调用
            verify(navController).navigate(eq(R.id.navigation_bill), any(Bundle.class));

            // 获取传递给目标 Fragment 的 Bundle
            ArgumentCaptor<Bundle> argumentCaptor = ArgumentCaptor.forClass(Bundle.class);
            verify(navController).navigate(eq(R.id.navigation_bill), argumentCaptor.capture());
            Bundle bundle = argumentCaptor.getValue();

            // 从 Bundle 中获取传递的 Bill 对象
            Bill bill = (Bill) bundle.getSerializable("bill");
            Assert.assertNotNull(bill);

            // 验证 Bill 对象中的各个字段是否正确
            Assert.assertEquals("收入", bill.getType());
            Assert.assertEquals("工资", bill.getCategory());
            Assert.assertEquals(100, bill.getMoney(),1e-2);
            Assert.assertEquals("账户1", bill.getAccount());
            Assert.assertEquals(2023, bill.getYear());
            Assert.assertEquals(6, bill.getMonth());
            Assert.assertEquals(6, bill.getDay());
            Assert.assertEquals("Income Title", bill.getTitle());
            Assert.assertEquals("Income Remake", bill.getRemake());

        });
    }
}