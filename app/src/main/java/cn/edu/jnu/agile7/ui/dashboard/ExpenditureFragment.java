package cn.edu.jnu.agile7.ui.dashboard;

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

public class ExpenditureFragment extends Fragment {
    final int[] clickedComponentFlag = new int[1];
    private ImageView imageView_food;
    private ImageView imageView_shopping;
    private ImageView imageView_bus;
    private ImageView imageView_else;
    private String category;
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
    private Bill account_expend;


    public ExpenditureFragment() {
        // Required empty public constructor
    }

    public static ExpenditureFragment newInstance() {
        ExpenditureFragment fragment = new ExpenditureFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expenditure, container, false);

        imageView_food = rootView.findViewById(R.id.food_background);
        imageView_shopping = rootView.findViewById(R.id.shopping_background);
        imageView_bus = rootView.findViewById(R.id.bus_background);
        imageView_else = rootView.findViewById(R.id.else_background);

        imageView_food.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_food.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_food.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 1;
                }
                imageView_shopping.setBackgroundResource(R.drawable.circle_background2);
                imageView_bus.setBackgroundResource(R.drawable.circle_background2);
                imageView_else.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;
            }
        });

        imageView_shopping.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_shopping.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_shopping.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 2;
                }
                imageView_food.setBackgroundResource(R.drawable.circle_background2);
                imageView_bus.setBackgroundResource(R.drawable.circle_background2);
                imageView_else.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;

            }
        });

        imageView_bus.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_bus.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_bus.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 3;
                }
                imageView_food.setBackgroundResource(R.drawable.circle_background2);
                imageView_shopping.setBackgroundResource(R.drawable.circle_background2);
                imageView_else.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;

            }

        });

        imageView_else.setOnClickListener(new View.OnClickListener() {
            boolean isBackgroundSelected = true;

            @Override
            public void onClick(View v) {
                if (isBackgroundSelected) {
                    imageView_else.setBackgroundResource(R.drawable.circle_background2);
                } else {
                    imageView_else.setBackgroundResource(R.drawable.circle_background);
                    clickedComponentFlag[0] = 4;
                }
                imageView_food.setBackgroundResource(R.drawable.circle_background2);
                imageView_shopping.setBackgroundResource(R.drawable.circle_background2);
                imageView_bus.setBackgroundResource(R.drawable.circle_background2);
                isBackgroundSelected = !isBackgroundSelected;
            }
        });

        amount_money = (EditText) rootView.findViewById(R.id.expend_money);


        String[] options = {"账户1", "账户2", "账户3"};
        ArrayList<String> account_List = new ArrayList<>(Arrays.asList(options));
//        //增加
//         account_List.add("新的账户");
//        //删除
//        account_List.remove("新的账户");

        ArrayAdapter adapter = new ArrayAdapter<>(this.getActivity(), R.layout.spinner_item, account_List);

        Spinner spinner = rootView.findViewById(R.id.expend_spinner);
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
        NumberPicker npYear = rootView.findViewById(R.id.expend_np_year);
        NumberPicker npMonth = rootView.findViewById(R.id.expend_np_month);
        NumberPicker npDay = rootView.findViewById(R.id.expend_np_day);

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

        Title = (EditText) rootView.findViewById(R.id.expend_title);
        Remake = (EditText) rootView.findViewById(R.id.expend_remark);

        if (clickedComponentFlag[0]==1){
            category = "餐饮";
        }
        else if(clickedComponentFlag[0]==2){
            category = "购物";
        }
        else if(clickedComponentFlag[0]==3){
            category = "公交";
        }
        else if(clickedComponentFlag[0]==4){
            category = "其他";
        }

        button = rootView.findViewById(R.id.expend_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rootView;
    }
}