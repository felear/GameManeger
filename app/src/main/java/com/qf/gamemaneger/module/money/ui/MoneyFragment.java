package com.qf.gamemaneger.module.money.ui;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseFragment;

/**
 * Created by Administrator on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment {

    private View mview;
    private PopupWindow mpwLoad;

    public void showLoadPw() {
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        mpwLoad.showAtLocation(getView(), Gravity.CENTER, 0, 0);
    }

    @Override
    protected int setViewId() {
        return R.layout.layout_fragment_money;
    }

    @Override
    protected void findViews(View view) {
        mview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pw_load, null);
    }

    @Override
    protected void init() {
        mpwLoad = new PopupWindow(mview,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mpwLoad.setOutsideTouchable(true);
        mpwLoad.setBackgroundDrawable(new ColorDrawable());
        mpwLoad.setAnimationStyle(android.support.v7.appcompat.R.anim.abc_popup_enter);
        mpwLoad.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }
}
