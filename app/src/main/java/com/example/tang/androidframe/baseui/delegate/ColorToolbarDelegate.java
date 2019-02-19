package com.example.tang.androidframe.baseui.delegate;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tang.androidframe.FrameApplication;
import com.example.tang.androidframe.R;
import com.example.tang.androidframe.utils.StatusBarUtil;
import com.kymjs.frame.presenter.ActivityPresenter;

/**
 * @author TangAnna
 * @description: toolbar背景带有颜色或者是固定的图片的Delegate
 * @date :${DATA} 17:11
 */
public abstract class ColorToolbarDelegate extends BaseViewDelegate {

    private Toolbar mToolbar;
    private View divider;//toolbar下面的横线

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
        LinearLayout toolbarRootView = new LinearLayout(getActivity());
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);//定义布局管理器的参数
        toolbarRootView.setOrientation(LinearLayout.VERTICAL);
        toolbarRootView.setLayoutParams(param);
        mToolbar = (Toolbar) View.inflate(getActivity(), R.layout.base_color_toolbar, null);

        //根据手机状态栏的高度设置toolbar的paddingTop值
        int height = StatusBarUtil.getStatusBarHeight(FrameApplication.getContext());
        mToolbar.setPadding(0, height, 0, 0);

        ((ActivityPresenter) getActivity()).setSupportActionBar(mToolbar);
        divider = new View(this.getActivity());
        divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divider.setBackgroundColor(getActivity().getResources().getColor(R.color.colorTitleLine));
        toolbarRootView.addView(mToolbar);
        toolbarRootView.addView(divider);
        toolbarRootView.addView(rootView);
        rootView = toolbarRootView;
    }

    @Override
    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            if (view == null)
                view = (T) getToolbar().findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setTitle(String title) {
        TextView tvTitle = mToolbar.findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(title);
    }

    public void setTitle(int color) {
        TextView tvTitle = mToolbar.findViewById(R.id.tv_toolbar_title);
        tvTitle.setTextColor(color);
    }

    /**
     * @param titleInterface title点击事件
     */
    public void setTitle(final ToolbarTitleInterface titleInterface) {
        TextView tvTitle = mToolbar.findViewById(R.id.tv_toolbar_title);
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleInterface != null) {
                    titleInterface.onClickTitleListener();
                } else {
                    getActivity().finish();
                }
            }
        });
    }

    /**
     * 设置左侧的图片
     *
     * @param drawable
     */
    public void setLeft(int drawable) {
        ImageView ivLeft = mToolbar.findViewById(R.id.iv_toolbar_left);
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(drawable);
    }

    /**
     * 设置左侧的点击事件
     *
     * @param leftInterface
     */
    public void setLeft(final ToolbarLeftInterface leftInterface) {
        ImageView ivLeft = mToolbar.findViewById(R.id.iv_toolbar_left);
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftInterface != null) {
                    leftInterface.onClickLeftListener();
                }
            }
        });
    }

    /**
     * 设置显示左侧的返回箭头
     */
    public void setShowLeft() {
        ImageView ivLeft = mToolbar.findViewById(R.id.iv_toolbar_left);
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(R.drawable.arrow_left_black);
    }

    public interface ToolbarTitleInterface {
        void onClickTitleListener();
    }

    public interface ToolbarLeftInterface {
        void onClickLeftListener();
    }
}
