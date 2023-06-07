package cn.edu.jnu.agile7.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import cn.edu.jnu.agile7.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragment extends Fragment {
    final int[] clickedComponentFlag = new int[1];
    private ImageView imageView_salary;
    private ImageView imageView_manage;
    private ImageView imageView_part_time;
    private ImageView imageView_sideline;
    private String category;//类别
    private EditText amount_money;
    private int money_number=0;
    private String select_account;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private EditText Title;
    private String title_string;
    private EditText Remake;
    private String remake_string;
    private Button button;
    private Account account_income;

    public IncomeFragment() {
        // Required empty public constructor
    }

    public static IncomeFragment newInstance() {
        IncomeFragment fragment = new IncomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);

        imageView_salary = rootView.findViewById(R.id.salary_background);
        imageView_manage = rootView.findViewById(R.id.manage_background);
        imageView_part_time = rootView.findViewById(R.id.part_time_background);
        imageView_sideline = rootView.findViewById(R.id.sideline_background);

//        select_type(true, false, false, false);
//        select_type(false, true, false, false);
//        select_type(false, false, true, false);
//        select_type(false, false, false, true);

        imageView_salary.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_salary.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_salary.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 1;
                }
                imageView_manage.setBackgroundResource(R.drawable.circle_background2);
                imageView_part_time.setBackgroundResource(R.drawable.circle_background2);
                imageView_sideline.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;
            }
        });

        imageView_manage.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_manage.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_manage.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 2;
                }
                imageView_salary.setBackgroundResource(R.drawable.circle_background2);
                imageView_part_time.setBackgroundResource(R.drawable.circle_background2);
                imageView_sideline.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;

            }
        });

        imageView_part_time.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_part_time.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_part_time.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 3;
                }
                imageView_salary.setBackgroundResource(R.drawable.circle_background2);
                imageView_manage.setBackgroundResource(R.drawable.circle_background2);
                imageView_sideline.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;

            }

        });

        imageView_sideline.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_sideline.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_sideline.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 4;
                }
                imageView_salary.setBackgroundResource(R.drawable.circle_background2);
                imageView_manage.setBackgroundResource(R.drawable.circle_background2);
                imageView_part_time.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;
            }
        });

        amount_money = (EditText) rootView.findViewById(R.id.income_money);

        amount_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 在文本改变前执行的操作（这里可以不做处理）
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 在文本改变时执行的操作
                String inputText = s.toString();
                money_number = Integer.parseInt(inputText);
                Log.i(String.valueOf(money_number), "input_number");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 在文本改变后执行的操作
            }
        });


        String[] options = {"账户1", "账户2", "账户3"};
        ArrayList<String> account_List = new ArrayList<>(Arrays.asList(options));
//        //增加
//         account_List.add("新的账户");
//        //删除
//        account_List.remove("新的账户");

        ArrayAdapter adapter = new ArrayAdapter<>(this.getActivity(), R.layout.spinner_item, account_List);

        Spinner spinner = rootView.findViewById(R.id.income_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = account_List.get(position);
                select_account = account_List.get(position);
//                Log.i(select_account, "select");
                // 在这里处理选择的选项
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 当没有选项被选择时的处理
            }
        });

        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // 初始化NumberPicker
        NumberPicker npYear = rootView.findViewById(R.id.income_np_year);
        NumberPicker npMonth = rootView.findViewById(R.id.income_np_month);
        NumberPicker npDay = rootView.findViewById(R.id.income_np_day);

        // 设置NumberPicker的范围
        npYear.setMinValue(year - 100);
        npYear.setMaxValue(year + 100);
        npYear.setValue(year);

        npMonth.setMinValue(1);
        npMonth.setMaxValue(12);
        npMonth.setValue(month + 1);

        npDay.setMinValue(1);
        npDay.setMaxValue(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        npDay.setValue(dayOfMonth);
        // 设置NumberPicker的监听器
        npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // 更新天数
                int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                npDay.setMaxValue(maxDay);
                if (npDay.getValue() > maxDay) {
                    npDay.setValue(maxDay);
                }
            }
        });

        // 获取选定的日期
        selectedYear = npYear.getValue();
        selectedMonth = npMonth.getValue();
        selectedDay = npDay.getValue();

        // 将日期转换为字符串
//        String selectedDate = String.format("%d-%02d-%02d", selectedYear, selectedMonth, selectedDay);

        Title = (EditText) rootView.findViewById(R.id.income_title);

        Title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 在文本改变前执行的操作（这里可以不做处理）
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 在文本改变时执行的操作
                title_string =  s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 在文本改变后执行的操作
            }
        });

        Remake = (EditText) rootView.findViewById(R.id.income_remark);

        Remake.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 在文本改变前执行的操作（这里可以不做处理）
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 在文本改变时执行的操作
                remake_string =  s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 在文本改变后执行的操作
            }
        });

        if (clickedComponentFlag[0]==1){
            category = "工资";
        }
        else if(clickedComponentFlag[0]==2){
            category = "理财";
        }
        else if(clickedComponentFlag[0]==3){
            category = "兼职";
        }
        else if(clickedComponentFlag[0]==4){
            category = "副业";
        }

        account_income = new Account("收入", category, money_number, select_account, selectedYear, selectedMonth, selectedDay, title_string, remake_string);

        button = rootView.findViewById(R.id.income_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时操作
            }
        });

        return rootView;
    }
}