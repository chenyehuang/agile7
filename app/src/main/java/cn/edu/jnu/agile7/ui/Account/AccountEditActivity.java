package cn.edu.jnu.agile7.ui.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.jnu.agile7.R;

public class AccountEditActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextMoney;
    private Button buttonSave;
    public static final int RESULT_CODE_NO_EDIT = -1;
    public static final int RESULT_CODE_EDIT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        // 获取传递的数据
        Intent intent = getIntent();
        String accountName = intent.getStringExtra("account_name");
        double accountMoney = intent.getDoubleExtra("account_money", 0.0);

        // 初始化视图
        editTextName = findViewById(R.id.account_name_edit);
        editTextMoney = findViewById(R.id.account_money_edit);
        buttonSave = findViewById(R.id.button_confirm_add_account_edit);

        // 设置初始值
        editTextName.setText(accountName);
        editTextMoney.setText(String.valueOf(accountMoney));

        // 点击保存按钮
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccount();
            }
        });
    }

    private void saveAccount() {
        // 获取输入的数据
        String name = editTextName.getText().toString();
        double money = Double.parseDouble(editTextMoney.getText().toString());

        // 创建返回的意图并添加数据
        Intent resultIntent = new Intent();
        resultIntent.putExtra("account_name", name);
        resultIntent.putExtra("account_money", money);

        // 设置结果并关闭活动
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}