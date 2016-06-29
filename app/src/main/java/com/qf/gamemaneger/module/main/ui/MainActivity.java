package com.qf.gamemaneger.module.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;
import com.qf.gamemaneger.common.widget.ButtomMenu;
import com.qf.gamemaneger.module.guess.ui.GuessFragment;
import com.qf.gamemaneger.module.home.ui.HomeFragment;
import com.qf.gamemaneger.module.mall.ui.MallFragment;
import com.qf.gamemaneger.module.me.ui.MeFragment;
import com.qf.gamemaneger.module.money.ui.MoneyFragment;

public class MainActivity extends BaseActivity {

    private ButtomMenu mlastSelMenu;
    private FragmentTransaction transaction;
    private HomeFragment mhomeFragment;
    private MeFragment mmeFragment;
    private MoneyFragment mmoneyFragment;
    private MallFragment mmallFragment;
    private GuessFragment mguessFragment;
    private Fragment fragment;
    private Fragment mlastFragmet;

    public void onChoice(View view) {
        ButtomMenu menu = (ButtomMenu) view;
        menu.onSelect();
        if (mlastSelMenu != menu) {
            mlastSelMenu.unSelect();
            mlastSelMenu = menu;
            Fragment fragment = null;
            switch (menu.getId()) {
                case R.id.btm_home:
                    fragment = mhomeFragment;
                    break;
                case R.id.btm_money:
                    fragment = mmoneyFragment;
                    mmoneyFragment.showLoadPw();
                    break;
                case R.id.btm_guess:
                    fragment = mguessFragment;
                    break;
                case R.id.btm_mall:
                    fragment = mmallFragment;
                    break;
                case R.id.btm_me:
                    fragment = mmeFragment;
                    break;
            }
            if (fragment != null) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(mlastFragmet);
                transaction.show(fragment);
                mlastFragmet = fragment;
                transaction.commit();
            }
        }

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mlastSelMenu = (ButtomMenu) findViewById(R.id.btm_home);
        mlastSelMenu.onSelect();
    }

    @Override
    protected void init() {
        transaction = getSupportFragmentManager().beginTransaction();

        mhomeFragment = new HomeFragment();
        mmeFragment = new MeFragment();
        mmoneyFragment = new MoneyFragment();
        mmallFragment = new MallFragment();
        mguessFragment = new GuessFragment();

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

        transaction.add(R.id.frame_layout, mguessFragment);
        transaction.hide(mguessFragment);
        transaction.add(R.id.frame_layout, mmoneyFragment);
        transaction.hide(mmoneyFragment);
        transaction.add(R.id.frame_layout, mmeFragment);
        transaction.hide(mmeFragment);
        transaction.add(R.id.frame_layout, mmallFragment);
        transaction.hide(mmallFragment);

        transaction.add(R.id.frame_layout, mhomeFragment);
        mlastFragmet = mhomeFragment;
        transaction.commit();
    }
}
