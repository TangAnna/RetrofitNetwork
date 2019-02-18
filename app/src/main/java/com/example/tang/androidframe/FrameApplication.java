package com.example.tang.androidframe;

import android.app.Application;
import android.content.Context;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 17:19
 */
public class FrameApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
