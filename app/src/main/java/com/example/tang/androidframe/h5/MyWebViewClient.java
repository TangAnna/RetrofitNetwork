package com.example.tang.androidframe.h5;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tang.androidframe.baseui.widget.ErrorView;
import com.example.tang.androidframe.http.LoadingDialog;

/**
 * @author TangAnna
 * @description: 自定义的WebViewClient
 * @date :${DATA} 11:26
 */
public class MyWebViewClient extends WebViewClient {
    private ErrorView mErrorView;
    private Context mContext;
    private LoadingDialog mLoadingDialog;

    public MyWebViewClient(Context context, ErrorView errorView) {
        mErrorView = errorView;
        mContext = context;
        mLoadingDialog = new LoadingDialog(mContext);
    }

    /**
     * 页面开始加载时的回调
     *
     * @param view
     * @param url
     * @param favicon
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
//        showProgressDialog();
        setErrorView(false, view);
    }

    /**
     * 页面加载完成的回调
     *
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        dismissProgressDialog();

    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        dismissProgressDialog();
        setErrorView(true, view);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        dismissProgressDialog();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return;
        }
        setErrorView(true, view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        dismissProgressDialog();
        WebView.HitTestResult hitTestResult = view.getHitTestResult();
        //hitTestResult==null解决重定向问题(刷新后不能退出的bug)
        if (!TextUtils.isEmpty(request.getUrl().toString()) && hitTestResult == null) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        dismissProgressDialog();
        WebView.HitTestResult hitTestResult = view.getHitTestResult();
        //hitTestResult==null解决重定向问题(刷新后不能退出的bug)
        if (!TextUtils.isEmpty(url) && hitTestResult == null) {
            return true;
        }
        view.loadUrl(url);
        return true;
//        return super.shouldOverrideUrlLoading(view, url);
    }

    /**
     * 设置error页面
     *
     * @param b       true错误页面   false 正常页面
     * @param webView
     */
    public void setErrorView(boolean b, WebView webView) {
        webView.setVisibility(b ? View.GONE : View.VISIBLE);
        mErrorView.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    /**
     * 显示loading
     */
    private void showProgressDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏loading
     */
    public void dismissProgressDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


}
