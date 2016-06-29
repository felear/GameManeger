package com.qf.gamemaneger.module.main.ui;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;
import com.qf.gamemaneger.base.NetCallback;
import com.qf.gamemaneger.common.constant.Constant;
import com.qf.gamemaneger.common.net.HttpNet;
import com.qf.gamemaneger.module.main.bean.LoginInfo;
import com.se7en.utils.SystemUtil;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    private long mend;
    private long mstart;
    private EditText medtTel;
    private EditText medtPwd;
    private Gson gson;
    private CheckBox mcheckBox;

    @Override
    protected void onResume() {
        super.onResume();
        mstart = 0;
    }

    public void onClick(View view) {
        Class c = null;
        switch (view.getId()) {
            case R.id.btn_forget:
                c = ForgetActivity.class;
                break;
            case R.id.btn_goto_logon:
                c = LogonActivity.class;
                break;
            case R.id.btn_login:

                String strPwd = medtPwd.getText().toString();
                String strTel = medtTel.getText().toString();

                if (strPwd == null || strTel == null) {
                    Toast.makeText(this,"用户名及密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                strPwd = new String(Base64.encode(strPwd.getBytes(), Base64.DEFAULT));

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", strTel);
                hashMap.put("password",strPwd);

                HttpNet.doHttpRequest("POST", Constant.LOGIN_URL, hashMap, new NetCallback() {
                    @Override
                    public void success(String strResult) {

                        doLoginInfo(strResult);
                    }

                    @Override
                    public void fail(String strResult) {
                        Log.d("print", "访问失败" + strResult);
                    }
                });

                break;
        }

        if (c != null) {
            Intent intent = new Intent(this, c);
            startActivity(intent);
        }
    }

    private void doLoginInfo(String strResult) {
        LoginInfo loginInfo = gson.fromJson(strResult, LoginInfo.class);
        String state = loginInfo.getState();
        if ("success".equals(state)) {
            // 登录成功
            if (mcheckBox.isChecked()) {
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, true);
            } else {
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, false);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, false);
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        mend = System.currentTimeMillis();

        if (mend - mstart < 3000) {
            finish();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mstart = mend;
        }

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews() {

        medtTel = (EditText) findViewById(R.id.edit_tel);
        medtPwd = (EditText) findViewById(R.id.edit_pwd);
        mcheckBox = (CheckBox) findViewById(R.id.checkBox);
    }

    @Override
    protected void init() {
        gson = new Gson();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }
}
