package com.wy.slidingpanelayoutdemo;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CustomSliding mSlidingPaneLayout;
    private LinearLayout mMenu;
    private ViewPager mViewPager;
    private String[] str = {"a", "b", "c"};
    private List<TextView> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidingPaneLayout = (CustomSliding) findViewById(R.id.id_sliding);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        for (int i=0;i<str.length;i++) {
            TextView textView = new TextView(this);
            textView.setText(str[i]);
            data.add(textView);
        }
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                    container.addView(data.get(position));

                return data.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(data.get(position));
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
        mMenu = (LinearLayout) findViewById(R.id.id_ll_menu);
        //设置滑动视差
        mSlidingPaneLayout.setParallaxDistance(200);
        //菜单滑动的颜色渐变设置
        mSlidingPaneLayout.setCoveredFadeColor(getResources().getColor(R.color.colorAccent));
        //主视图滑动的的颜色渐变设置
            mSlidingPaneLayout.setSliderFadeColor(Color.parseColor("#fff000"));
    //滑动监听
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e(TAG, "onPanelSlide: "+slideOffset );
//                mMenu.setScaleX(slideOffset/2+0.5f);
//                mMain.setScaleY(1-slideOffset/5);
            }

            @Override
            public void onPanelOpened(View panel) {
                Log.e(TAG, "onPanelOpened: " );
            }

            @Override
            public void onPanelClosed(View panel) {
                Log.e(TAG, "onPanelClosed: " );
            }
        });
    }
}
