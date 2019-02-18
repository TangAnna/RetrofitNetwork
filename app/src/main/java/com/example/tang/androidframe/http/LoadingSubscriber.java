package com.example.tang.androidframe.http;

import android.content.Context;
import android.net.ParseException;

import com.example.tang.androidframe.model.BaseMode;
import com.example.tang.androidframe.utils.LogUtils;
import com.example.tang.androidframe.utils.ToastUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * 带有loading的Subscriber
 *
 * @param <T>
 */
public abstract class LoadingSubscriber<T> extends Subscriber<T> implements LoadingDialog.DialogStopInterface {

    private static final String TAG = LoadingSubscriber.class.getSimpleName();
    private LoadingDialog mLoadingDialog;

    private Context mContext;
    private String mText;
    protected boolean showDialog = false;

    public LoadingSubscriber(Context context) {
        this(context, false);
    }

    public LoadingSubscriber(Context context, boolean show) {
        this(context, show, "");
    }

    public LoadingSubscriber(Context context, boolean show, String text) {
        mContext = context;
        this.showDialog = show;
        this.mText = text;

        mLoadingDialog = new LoadingDialog(context);
        mLoadingDialog.setDialogStopInterface(this);
    }

    private void showLoading() {
        if (mLoadingDialog != null && showDialog) {
            if (mText != null) {
                mLoadingDialog.setHintText(mText);
            }
            mLoadingDialog.show();
        }

    }

    public void dismissLoading() {
        if (mLoadingDialog != null && showDialog)
            mLoadingDialog.dismiss();
    }

    /**
     * 订阅开始时调用
     * 显示loading
     */
    @Override
    public void onStart() {
        if (showDialog) {
            showLoading();
        }
    }

    /**
     * 完成，隐藏loading
     */
    @Override
    public void onCompleted() {
        dismissLoading();
    }

    /**
     * 统一处理code !=200的情况
     *
     * @param t
     */
    @Override
    public void onNext(T t) {
        if (t instanceof BaseMode) {
            BaseMode baseMode = (BaseMode) t;
            if (baseMode.code != 200) {//后台定好的业务逻辑code值

            }
        }
    }

    /**
     * 对错误进行统一处理 httpCode !=200 通信没有成功
     * 隐藏loading
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissLoading();
        if (e instanceof ConnectException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof SocketException) {
            ToastUtils.showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ApiException) {
            // 需要后台解决的错误
            ApiException apiException = (ApiException) e;
            if (apiException.errorCode == 403) {
                ToastUtils.showToast("权限不足");
            } else {
                ToastUtils.showToast("网络错误");
            }
            LogUtils.dNormal(TAG + ":onError: ", "errorCode== " + apiException.errorCode + "errorMsg== " + apiException.errorMsg);
        } else {
            if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
                LogUtils.dNormal(TAG + ":onError:解析错误 ", e.getMessage());
            } else {
                LogUtils.dNormal(TAG + ":onError: 未知错误", e.getMessage());
            }
        }
    }

    /**
     * 取消loading的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onStopListener() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}