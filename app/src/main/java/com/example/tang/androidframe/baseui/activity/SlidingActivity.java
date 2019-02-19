package com.example.tang.androidframe.baseui.activity;

import android.os.Bundle;

import com.example.tang.androidframe.baseui.widget.SlidingLayout;
import com.kymjs.frame.view.IDelegate;

/**
 * @param <T>
 */
public abstract class SlidingActivity<T extends IDelegate> extends BaseActivityPresenter<T> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }

}
