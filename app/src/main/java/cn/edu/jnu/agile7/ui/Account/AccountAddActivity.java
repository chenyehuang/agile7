package cn.edu.jnu.agile7.ui.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.jnu.agile7.R;

public class AccountAddActivity extends AppCompatActivity {

    public static final int RESULT_CODE_NO_ADD = -1;
    public static final int RESULT_CODE_ADD = 0;
    private Button button;
    private EditText account_name;
    private EditText account_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);
        account_name = findViewById(R.id.account_name);
        account_money = findViewById(R.id.account_money);
        initConfirmButton();
    }

    private void initConfirmButton () {
        button = findViewById(R.id.button_confirm_add_account);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountAdd(true);
            }
        });
    }

    // 发送数据
    private void AccountAdd(boolean isAdd) {
        Intent intent = new Intent();
        if (isAdd) {
            Bundle bundle = new Bundle();
            String account_name_string = account_name.getText().toString();
            double account_money_amount = Double.parseDouble(account_money.getText().toString());
            bundle.putString("account_name", account_name_string);
            bundle.putDouble("account_money", account_money_amount);
            intent.putExtras(bundle);
            setResult(RESULT_CODE_ADD, intent);
        }
        else
            setResult(RESULT_CODE_NO_ADD);
        AccountAddActivity.this.finish();
    }
}