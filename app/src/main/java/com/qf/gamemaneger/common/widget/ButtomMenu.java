package com.qf.gamemaneger.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.gamemaneger.R;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ButtomMenu extends LinearLayout {

    private int mnormalPicId;
    private TextView mtvBottom;
    private ImageView mivBottom;
    private int mpressPicId;
    private TranslateAnimation mselectTranslateAnimation;
    private ScaleAnimation mselectScaleAnimation;
    private boolean mselectFlag;
    private ScaleAnimation munSelectScaleAnimation;
    private TranslateAnimation munSelectTranslateAnimation;

    public ButtomMenu(Context context) {
        super(context);
    }

    public ButtomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
        initData(context, attrs);

    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.buttom_menu);

        CharSequence text = array.getText(R.styleable.buttom_menu_text);
        if (text != null) {
            mtvBottom.setText(text);
        }

        mnormalPicId = array.getResourceId(R.styleable.buttom_menu_normalPic, -1);
        mpressPicId = array.getResourceId(R.styleable.buttom_menu_pressPic, -1);
        // 使用完毕需要回收
        array.recycle();

        if (mnormalPicId != -1) {
            mivBottom.setImageResource(mnormalPicId);
        }

        mselectScaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        mselectScaleAnimation.setDuration(300);
        mselectScaleAnimation.setFillAfter(true);

        mselectTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1.5f);

        mselectTranslateAnimation.setDuration(300);
        mselectTranslateAnimation.setFillAfter(true);

        munSelectScaleAnimation = new ScaleAnimation(1.5f, 1f, 1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
        munSelectScaleAnimation.setDuration(300);

        munSelectTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1.5f,
                Animation.RELATIVE_TO_SELF, 0);
        munSelectTranslateAnimation.setDuration(300);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.acticity_bottom_menu, this, true);

        mtvBottom = (TextView) view.findViewById(R.id.tv_bottom);
        mivBottom = (ImageView) view.findViewById(R.id.iv_bottom);
    }

    public void onSelect() {

        mivBottom.setImageResource(mpressPicId);

        if (!mselectFlag) {
            mtvBottom.startAnimation(mselectTranslateAnimation);
            mselectFlag = true;
        }

        mivBottom.startAnimation(mselectScaleAnimation);
    }

    public void unSelect() {
        mselectFlag = false;

        mivBottom.setImageResource(mnormalPicId);
        mivBottom.startAnimation(munSelectScaleAnimation);

        // 开启动画
        mtvBottom.startAnimation(munSelectTranslateAnimation);
    }
}
