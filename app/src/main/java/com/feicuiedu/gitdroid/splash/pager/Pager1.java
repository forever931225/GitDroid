package com.feicuiedu.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

public class Pager1 extends FrameLayout {


    public Pager1(Context context) {
        this(context, null);
    }

    public Pager1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1, this, true);
    }


}
