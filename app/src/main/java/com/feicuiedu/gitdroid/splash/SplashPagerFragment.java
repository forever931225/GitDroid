package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @BindView(R.id.content)FrameLayout frameLayout;//��ǰҳ��layout
    @BindView(R.id.layoutPhone) FrameLayout layoutPhone;// ��Ļ�����"�ֻ�"
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

        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        //���viewpager����Ϊ�˶�����
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    //��ɫ��������
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        //��Scroll��������н��г���
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // ��һ��ҳ�浽�ڶ���ҳ��֮��
            if(position == 0){
                int color = (int) argbEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                frameLayout.setBackgroundColor(color);
            }
            // �ڶ���ҳ�浽�����ҳ��֮��
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

    //�ֻ�Ч���?ƽ�ơ����š�͸���ȱ仯��
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position == 0){
                // �ֻ�����Ŵ���
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                // �ֻ��ƽ�ƴ���
                int scroll = (int) ((positionOffset - 1) * 360);
                layoutPhone.setTranslationX(scroll);
                // �ֻ�����Ľ���
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            //�ڶ��������ҳ��
            if(position == 1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            // ����ʾ�����һ��pagerʱ���������Լ��Ķ���
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
