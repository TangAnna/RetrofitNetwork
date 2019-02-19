package com.example.tang.androidframe.h5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.example.tang.androidframe.interfaces.InterfaceH5Close;

/**
 * @author TangAnna
 * @description: 与Js进行交互使用到的回调类
 * @date :${DATA} 11:24
 */
public class WebInteraction {

    private Activity mActivity;

    private InterfaceH5Close mInterfaceH5Close;


    public WebInteraction(Activity context) {
        mActivity = context;
    }

    /**
     * 暴露给H5调用的关闭方法
     */
    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    public void close() {
        if (mInterfaceH5Close != null) {
            mInterfaceH5Close.onClose();
        } else {
            mActivity.finish();
        }
    }

    /**
     * 关闭事件的回调
     *
     * @param interfaceH5Close
     */
    public void setInterfaceH5Close(InterfaceH5Close interfaceH5Close) {
        mInterfaceH5Close = interfaceH5Close;
    }


}
