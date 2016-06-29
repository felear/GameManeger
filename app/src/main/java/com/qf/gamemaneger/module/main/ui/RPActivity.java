package com.qf.gamemaneger.module.main.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;

public class RPActivity extends BaseActivity {

    private PopupWindow rpWindow;
    private View mpopupView;
    private boolean mflag;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (rpWindow == null) {
            setAlpha(0.5f);
            rpWindow = new PopupWindow(mpopupView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    false);
            rpWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }


    }

    @Override
    protected void onDestroy() {
        if(rpWindow != null && rpWindow.isShowing()){
            rpWindow.dismiss();
        }
        super.onDestroy();
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_rp;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {
        mpopupView = LayoutInflater.from(this).inflate(R.layout.layout_pw_rb, null, false);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }

    public void setAlpha(float alpha) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = alpha;
        getWindow().setAttributes(params);
    }

    public void onClick(View view) {
        Class c = null;
        if (view.getId() == R.id.btn_login) {
            // 跳转至登录
            c = LoginActivity.class;
            mflag = true;
        }
        if (view.getId() == R.id.btn_logon) {
            // 跳转至注册
            c = LogonActivity.class;
        }

        if (c != null) {
            Intent intent = new Intent(this, c);
            startActivity(intent);
            if (mflag) {
                finish();
            }
        }
    }
}
