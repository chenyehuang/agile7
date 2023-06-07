package cn.edu.jnu.agile7.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import cn.edu.jnu.agile7.R;
import cn.edu.jnu.agile7.databinding.FragmentDashboardBinding;

/**
 * @author Administrator
 */
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ViewPager2 viewPager2Main = root.findViewById(R.id.viewpager2_main);
        viewPager2Main.setAdapter(new PageViewFragmentAdapter(getChildFragmentManager(), getLifecycle()));
        TabLayout tabLayout=root.findViewById(R.id.tablayout_header);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2Main, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0:
                        tab.setText(R.string.tab_caption_1_item);
                        break;
                    case 1:
                        tab.setText(R.string.tab_caption_2_map);
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
        return root;
    }

    public static class PageViewFragmentAdapter extends FragmentStateAdapter {
        public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position)
            {
                case 0:
                    return IncomeFragment.newInstance();
                case 1:
                    return ExpenditureFragment.newInstance();
            }
            return IncomeFragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}