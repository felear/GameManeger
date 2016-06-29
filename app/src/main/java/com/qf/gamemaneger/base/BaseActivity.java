package com.qf.gamemaneger.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/6/27.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());

        findViews();
        init();
        initEvent();
        loadData();

    }

    /**
     * 加载布局
     */
    protected abstract int setViewId();

    /**
     * 查找控件
     */
    protected abstract void findViews();

    /**
     * 数据初始化
     */
    protected  abstract void init();

    /**
     * 初始化各种事件监听
     */
    protected abstract void initEvent();

    /**
     * 加载数据
     */
    protected abstract void loadData();

}
