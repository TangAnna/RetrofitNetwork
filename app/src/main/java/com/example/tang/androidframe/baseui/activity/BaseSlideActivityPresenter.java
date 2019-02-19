package com.example.tang.androidframe.baseui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.tang.androidframe.utils.SlideUtil;
import com.example.tang.androidframe.baseui.helper.SlideActivityHelper;
import com.example.tang.androidframe.baseui.widget.SwipeBackLayout;
import com.kymjs.frame.view.IDelegate;

/**
 * @author TangAnna
 * @description: 可以侧滑关闭的ActivityPresenter
 * @date :${DATA} 15:54
 */
public abstract class BaseSlideActivityPresenter<T extends IDelegate> extends BaseActivityPresenter<T> {
    private SlideActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SlideActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null) {
            v = mHelper.findViewById(id);
        }
        return v;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setSwipeBackEnable(enable);
    }

    public void scrollToFinishActivity() {
        SlideUtil.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
