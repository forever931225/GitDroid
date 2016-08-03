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
                .cacheInMemory(true) // 打开内存缓存
                .cacheOnDisk(true) // 打开硬盘缓存
                .resetViewBeforeLoading(true) // 在ImageView加载前清除它上面的图片
                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R.dimen.dp_10)))
                .build();

        // ImageLoader配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5 * 1024 * 1024) // 内存缓存
                .defaultDisplayImageOptions(options) // 设置显示选项
                .build();

        // 初始化ImageLoad
        ImageLoader.getInstance().init(config);
    }
}
