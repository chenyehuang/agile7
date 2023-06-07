package cn.edu.jnu.agile7.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.jnu.agile7.R;

public class AccountAddActivity extends AppCompatActivity {

    public static final int RESULT_CODE_NO_ADD = -1;
    public static final int RESULT_CODE_ADD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        initConfirmButton();
    }

    private void initConfirmButton () {
        Button button = findViewById(R.id.button_confirm_add_account);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(true);
            }
        });
    }

    private void goBack(boolean isAdd) {
        Intent intent = new Intent();
        if (isAdd) {
            Bundle bundle = new Bundle();
            setResult(RESULT_CODE_ADD, intent);
        }
        else
            setResult(RESULT_CODE_NO_ADD);
        AccountAddActivity.this.finish();
    }
}