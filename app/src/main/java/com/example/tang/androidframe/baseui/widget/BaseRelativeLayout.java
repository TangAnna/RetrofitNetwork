package com.example.tang.androidframe.baseui.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * 模块控件的基类
 *
 * @param <T> 数据模型
 */
public abstract class BaseRelativeLayout<T> extends RelativeLayout {
    public BaseRelativeLayout(Context context) {
        this(context, null, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, bindLayout(), this);
        initView(context, attrs, defStyleAttr);
    }

    protected abstract void initView(Context context, AttributeSet attrs, int defStyleAttr);

    public abstract void setData(T data);

    protected abstract @LayoutRes
    int bindLayout();

}
