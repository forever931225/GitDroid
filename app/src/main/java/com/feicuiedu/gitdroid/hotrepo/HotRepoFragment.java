package com.feicuiedu.gitdroid.hotrepo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.feicuiedu.gitdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 16-8-2.
 */
public class HotRepoFragment extends Fragment{

    @BindView(R.id.viewPager)ViewPager viewPager;
    @BindView(R.id.tabLayout)TabLayout tabLayout;
    private HotRepoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot_repo,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        adapter = new HotRepoAdapter(getChildFragmentManager(),getContext());
//        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
