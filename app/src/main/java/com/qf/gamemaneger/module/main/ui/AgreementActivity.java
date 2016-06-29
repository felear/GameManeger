package com.qf.gamemaneger.module.main.ui;

import android.view.View;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;

public class AgreementActivity extends BaseActivity {

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }
}
