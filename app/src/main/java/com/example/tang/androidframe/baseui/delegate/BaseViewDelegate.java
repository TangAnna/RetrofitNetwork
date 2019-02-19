package com.example.tang.androidframe.baseui.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.widget.EmptyView;

/**
 * @author TangAnna
 * @description: 封装了EmptyView和ErrorView的Delegate
 * @date :${DATA} 16:58
 */
public abstract class BaseViewDelegate extends BaseDelegate {

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.base_delegate_view, container, false);
        FrameLayout frameLayout = get(R.id.dataView_base_delegate);
        frameLayout.addView(inflater.inflate(getRootLayoutId(), container, false));
    }

    /**
     * 设置空页面
     */
    public void setEmptyView() {
        get(R.id.emptyView_base_delegate).setVisibility(View.VISIBLE);
        get(R.id.errorView_base_delegate).setVisibility(View.GONE);
        get(R.id.dataView_base_delegate).setVisibility(View.GONE);
    }

    /**
     * 设置ErrorView
     *
     * @param throwable null时显示空页面
     */
    public void setErrorView(Throwable throwable) {
        if (throwable != null) {
            get(R.id.emptyView_base_delegate).setVisibility(View.GONE);
            get(R.id.errorView_base_delegate).setVisibility(View.VISIBLE);
            get(R.id.dataView_base_delegate).setVisibility(View.GONE);
        } else {//异常时需要显示空页面使用
            setEmptyView();
        }
    }

    /**
     * 设置数据页面
     */
    public void setDataView() {
        get(R.id.emptyView_base_delegate).setVisibility(View.GONE);
        get(R.id.errorView_base_delegate).setVisibility(View.GONE);
        get(R.id.dataView_base_delegate).setVisibility(View.VISIBLE);
    }

    /**
     * 设置加载中的样式  进入页面就有网络请求并且显示loading的参数设置为true才可使用
     */
    public void setLoadingView() {
        get(R.id.emptyView_base_delegate).setVisibility(View.GONE);
        get(R.id.errorView_base_delegate).setVisibility(View.GONE);
        get(R.id.dataView_base_delegate).setVisibility(View.GONE);
    }

    /**
     * 设置空页面显示的文案和图标
     *
     * @param text 空页面的提示文字
     * @param id   图片的ID
     */
    public void setEmptyViewUI(String text, int id) {
        EmptyView emptyView = get(R.id.emptyView_base_delegate);
        emptyView.setHintText(text);
        emptyView.setImageView(id);
    }
}
