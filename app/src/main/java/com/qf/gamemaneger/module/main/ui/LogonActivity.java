package com.qf.gamemaneger.module.main.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;

public class LogonActivity extends BaseActivity {

    private CheckBox mcheckBox;
    private TextView btn_code;
    private int miCnt = 120;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_code:
                btn_code.setClickable(false);
                new CountDownTimer(miCnt * 1000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        btn_code.setText("" + miCnt--);
                    }

                    @Override
                    public void onFinish() {
                        btn_code.setClickable(true);
                        miCnt = 120;
                        btn_code.setText("重新获取验证码");
                    }
                }.start();
                break;
        }
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_logon;
    }

    @Override
    protected void findViews() {
        mcheckBox = (CheckBox) findViewById(R.id.checkBox);
        mcheckBox.setMovementMethod(new LinkMovementMethod());

        String strAgree = "我已阅读并接收注册协议";
        SpannableString spannableString = new SpannableString(strAgree);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LogonActivity.this, AgreementActivity.class);
                startActivity(intent);
            }
        },7,strAgree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mcheckBox.setText(spannableString);

        btn_code = (TextView) findViewById(R.id.btn_code);
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
