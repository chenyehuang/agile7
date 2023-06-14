package cn.edu.jnu.agile7;

import android.os.Bundle;
import android.util.Log;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


;
import java.util.Objects;

import cn.edu.jnu.agile7.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// 使用视图绑定来设置活动的布局
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //去掉标题栏
        Objects.requireNonNull(this.getSupportActionBar()).hide();


// 获取底部导航视图
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 创建顶级目标配置，用于指定顶级目标的ID
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_bill,R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        // 获取导航控制器，并将其与活动的导航宿主Fragment关联
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // 使用导航控制器设置ActionBar和顶部导航视图
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}
