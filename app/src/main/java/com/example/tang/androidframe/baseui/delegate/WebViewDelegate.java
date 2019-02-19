package com.example.tang.androidframe.baseui.delegate;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.widget.ErrorView;
import com.example.tang.androidframe.constan.ConfigConstant;
import com.example.tang.androidframe.h5.MyWebViewClient;
import com.example.tang.androidframe.h5.WebInteraction;


/**
 * @author TangAnna
 * @description: 页面只有WebView时使用
 * @date :${DATA} 14:36
 */
public class WebViewDelegate extends ToolbarDelegate implements ErrorView.OnErrorListener {
    public WebView mWebView;
    private ErrorView mErrorView;

    @Override
    public int getRootLayoutId() {
        return R.layout.base_webview_delegate;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mWebView = get(R.id.webView);
        mErrorView = get(R.id.errorView);
        mErrorView.setOnErrorListener(this);
        initWebView();
    }

    /**
     * 设置WebView 的公共参数
     */
    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        //设置两端可以交互
        settings.setJavaScriptEnabled(true);
        //设置WebView根据手机去适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);//打开DOM存储API
        settings.setAllowFileAccess(true);
        settings.setLoadsImagesAutomatically(true);//自动加载图片
        settings.setBuiltInZoomControls(false);
        settings.setTextZoom(100);

        //设置WebView不使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new MyWebViewClient(getActivity(), mErrorView));
    }

    /**
     * 设置与H5交互的回调接口
     *
     * @param o
     */
    @SuppressLint("JavascriptInterface")
    public void setWebViewInterface(WebInteraction o) {
        mWebView.addJavascriptInterface(o, ConfigConstant.H5_DELEGATE);
    }

    @Override
    public void errorListener() {
        mWebView.reload();
    }

    /**
     * 设置要加载的WebView的地址
     *
     * @param url
     */
    public void setWebViewLoadUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebView.loadUrl(url);
    }

    /**
     * 重新加载
     */
    public void reload() {
        mWebView.reload();
    }
}
