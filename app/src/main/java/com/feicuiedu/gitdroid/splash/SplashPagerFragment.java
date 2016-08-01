package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.splash.pager.Pager2;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 16-7-29.
 */
public class SplashPagerFragment extends Fragment {

    @BindView(R.id.viewPager)ViewPager viewPager;
    private SplashPagerAdapter adapter;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.content)FrameLayout frameLayout;//当前页面layout
    @BindView(R.id.layoutPhone) FrameLayout layoutPhone;// 屏幕中央的"手机"
    @BindView(R.id.ivPhoneFont)
    ImageView ivPhoneFont;

    @BindColor(R.color.colorGreen)int colorGreen;

    @BindColor(R.color.colorRed)int colorRed;

    @BindColor(R.color.colorYellow)int colorYellow;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_pager,container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        //添加viewpager监听（为了动画）
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    //颜色背景渐变
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        //在Scroll滚动过程中进行出发
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 第一个页面到第二个页面之间
            if(position == 0){
                int color = (int) argbEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                frameLayout.setBackgroundColor(color);
            }
            // 第二个页面到第三个页面之间
            if(position == 1){
                int color = (int) argbEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                frameLayout.setBackgroundColor(color);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //手机动画效果处理（平移、缩放、透明度变化）
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position == 0){
                // 手机的缩放处理
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                // 手机的平移处理
                int scroll = (int) ((positionOffset - 1) * 360);
                layoutPhone.setTranslationX(scroll);
                // 手机字体的渐变
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            //第二到第三个页面
            if(position == 1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            // 当显示出最后一个pager时，播放它自己的动画
            if(position == 2) {
                Pager2 pager2View = (Pager2) adapter.getView(position);
                pager2View.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
