package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.view_page);
        tabLayout = findViewById(R.id.tab_layout);
        initPager();
    }

    private void initPager() {
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {  //fragment与页面的序号
                switch (position){
                    case 0: return new chatFragment();

                    case 1: return new CommunicationFragment();

                    case 2: return new FindFragment();

                    default: return new MeFragment();
                }
            }

            @Override
            public int getItemCount() { //有几个页面
                return 4;
            }
        });

        viewPager2.setOffscreenPageLimit(4);  //设置预加载的页面数量


        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("微信聊天");
                            break;
                    case 1: tab.setText("通讯录");
                            break;
                    case 2: tab.setText("发现");
                            break;
                    default: {
                        tab.setText("我");
                        tab.setIcon(R.drawable.ic_baseline);
                    }
                }
            }
        }).attach();


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //当页面正在滚动时调用此方法。position 是当前页面的位置，positionOffset 是当前页面的偏移量（0.0 到 1.0），positionOffsetPixels 是当前页面的像素偏移量。
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //当新页面被选中时调用此方法。position 是新页面的位置。
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                /**
                 * 当页面滚动状态发生变化时调用此方法。state 可以是以下三个值之一：
                 * ViewPager2.SCROLL_STATE_IDLE：页面处于空闲状态，没有滚动。
                 * ViewPager2.SCROLL_STATE_DRAGGING：用户正在拖动页面。
                 * ViewPager2.SCROLL_STATE_SETTLING：页面正在自动滚动到最终位置。
                 */


            }
        });



//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(BlankFragment.newInstance("微信聊天"));
//        fragments.add(BlankFragment.newInstance("通讯录"));
//        fragments.add(BlankFragment.newInstance("发现"));
//        fragments.add(BlankFragment.newInstance("我"));
//
//        MyFragmentViewPager myFragmentViewPager = new MyFragmentViewPager(getSupportFragmentManager(),getLifecycle(),fragments);
//        viewPager2.setAdapter(myFragmentViewPager);
    }
}