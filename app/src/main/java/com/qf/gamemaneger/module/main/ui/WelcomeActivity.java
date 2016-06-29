package com.qf.gamemaneger.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.gamemaneger.R;
import com.qf.gamemaneger.base.BaseActivity;
import com.qf.gamemaneger.common.constant.Constant;
import com.se7en.utils.SystemUtil;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity {


    private ViewPager mvpWelcome;
    private ArrayList<ImageView> mlistPics;
    private TextView mbtnStart;
    private int miCurVersion;
    private int miLastVersion;
    private ImageView mivTextLogo;
    private ImageView mivPicLogo;

    @Override
    protected int setViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void findViews() {

        miCurVersion = SystemUtil.getSystemVersionCode();
        miLastVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING, -1);

        if (miLastVersion == -1 || miCurVersion > miLastVersion) {
            mvpWelcome = (ViewPager) findViewById(R.id.vp_welcome);
            mbtnStart = (TextView) findViewById(R.id.btn_start);
        }else {

            mivTextLogo = (ImageView) findViewById(R.id.iv_text_logo);
            mivPicLogo = (ImageView) findViewById(R.id.iv_pic_logo);
            showTextLogoAnim();
        }

    }

    private void showPicLogoAnim() {
        mivPicLogo.setVisibility(View.VISIBLE);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new BounceInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mivPicLogo.startAnimation(translateAnimation);
    }

    private void showTextLogoAnim() {

        mivTextLogo.setVisibility(View.VISIBLE);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.7f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);

        translateAnimation.setDuration(1000);
        translateAnimation.setStartOffset(1000);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showPicLogoAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mivTextLogo.startAnimation(translateAnimation);
    }

    private void showNextActivity() {

        boolean loginFlag = SystemUtil.getSharedBoolean(Constant.LOGIN_FLAG, false);
        Class c = null;
        if (loginFlag) {
            c = MainActivity.class;
        } else {
            c = RPActivity.class;
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
        finish();
    }


    @Override
    protected void init() {

        if (miLastVersion == -1 || miCurVersion > miLastVersion) {

            mlistPics = new ArrayList<>();
            addImageView(R.mipmap.bg_guide_01);
            addImageView(R.mipmap.bg_guide_02);
            addImageView(R.mipmap.bg_guide_03);
            addImageView(R.mipmap.bg_guide_04);

            //设置viewpager的adapter
            mvpWelcome.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return mlistPics.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                    container.addView(mlistPics.get(position));
                    return mlistPics.get(position);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                    container.removeView((View) object);
                }

            });
        }

    }

    private void addImageView(int iResId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(iResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mlistPics.add(imageView);

    }

    @Override
    protected void initEvent() {
        if (miLastVersion == -1 || miCurVersion > miLastVersion) {
            mvpWelcome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    if (position == 3) {
                        mbtnStart.setVisibility(View.VISIBLE);
                    } else {
                        mbtnStart.setVisibility(View.GONE);
                    }
                }
            });

            mbtnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SystemUtil.setSharedInt(Constant.VERSION_STRING, miCurVersion);
                    showNextActivity();
                }
            });
        }
    }

    @Override
    protected void loadData() {

    }
}
