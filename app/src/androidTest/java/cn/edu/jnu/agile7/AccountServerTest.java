package cn.edu.jnu.agile7;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.notifications.Account;
import cn.edu.jnu.agile7.ui.notifications.AccountServer;

@RunWith(AndroidJUnit4.class)
public class AccountServerTest {

    AccountServer dataSaverBackup;
    ArrayList<Account> shopItemsBackup;

    @Before
    public void setUp() throws Exception {
        dataSaverBackup=new AccountServer();
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        shopItemsBackup=dataSaverBackup.Load(targetContext);

    }

    @After
    public void tearDown() throws Exception {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dataSaverBackup.Save(targetContext,shopItemsBackup);
    }

    @Test
    public void saveAndLoad() {
        AccountServer dataSaver=new AccountServer();
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ArrayList<Account> accountItems=new ArrayList<>();
        Account accountItem=new Account("测试",56.7);
        accountItems.add(accountItem);
        accountItem=new Account("正常",12.3);
        accountItems.add(accountItem);
        dataSaver.Save(targetContext,accountItems);

        AccountServer dataLoader=new AccountServer();
        ArrayList<Account> shopItemsRead=dataLoader.Load(targetContext);
        Assert.assertEquals(accountItems.size(),shopItemsRead.size());
        for(int index=0;index<accountItems.size();++index)
        {
            Assert.assertEquals(accountItems.get(index).getName(),shopItemsRead.get(index).getName());
            Assert.assertEquals(accountItems.get(index).getAmount(),shopItemsRead.get(index).getAmount(),1e-2);
        }

    }


}