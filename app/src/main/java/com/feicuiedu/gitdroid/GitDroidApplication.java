package com.feicuiedu.gitdroid;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


public class GitDroidApplication extends Application{

    @Override public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_avatar)
                .showImageOnFail(R.drawable.ic_avatar)
                .showImageForEmptyUri(R.drawable.ic_avatar)
                .cacheInMemory(true) // ���ڴ滺��
                .cacheOnDisk(true) // ��Ӳ�̻���
                .resetViewBeforeLoading(true) // ��ImageView����ǰ����������ͼƬ
                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R.dimen.dp_10)))
                .build();

        // ImageLoader����
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5 * 1024 * 1024) // �ڴ滺��
                .defaultDisplayImageOptions(options) // ������ʾѡ��
                .build();

        // ��ʼ��ImageLoad
        ImageLoader.getInstance().init(config);
    }
}
