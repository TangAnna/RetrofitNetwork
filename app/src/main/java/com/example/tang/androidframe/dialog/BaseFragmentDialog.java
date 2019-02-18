package com.example.tang.androidframe.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.tang.androidframe.R;


/**
 * @author TangAnna
 * @description: DialogFragment基类  默认样式是中间显示
 * @date :${DATA} 9:39
 */
public abstract class BaseFragmentDialog extends DialogFragment {

    /**
     * onCreate方法中主要是是设置样式等操作
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(setDialogStyle(), setDialogThem());
    }

    /**
     * onCreateView方法主要是创建布局文件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindView(), null);
        Window window = getDialog().getWindow();
        window.setWindowAnimations(getAnimation(setAnimation()));
        return view;
    }

    /**
     * onViewCreated方法视图已经有了可以初始化View 设置数据
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(getArguments());
        initView(view);
        initData();
        setData();
        setListener(view);
    }

    /**
     * 获取传递的数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        loadData(getArguments());
    }

    /**
     * onStart方法中设置布局在屏幕中的属性
     */
    @Override
    public void onStart() {
        super.onStart();
        initLayoutParams();
    }

    /**
     * 设置布局参数
     */
    private void initLayoutParams() {
        //设置 LayoutParams.MATCH_PARENT去掉缝隙
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);

        //设置有关于背景的属性
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xff000000));
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //window外可以点击,不拦截窗口外的事件
        if (outCanOnTouch()) {
            getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        }
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();

        //是否设置背景透明（默认是不透明的）
        if (isTransparent()) {
            layoutParams.dimAmount = 0.0f;
        }

        // 设置宽
        layoutParams.width = setLayoutParamsWidth();

        // 设置高
        layoutParams.height = setLayoutParamsHeight();

        //设置位置
        layoutParams.gravity = setGravity();

        getDialog().getWindow().setAttributes(layoutParams);

        getDialog().setCanceledOnTouchOutside(setCanceledOnTouchOutside());
        //是否可以取消,会影响上面那条属性
        setCancelable(setCanCancel());
    }

    /**
     * 显示弹窗
     *
     * @param fragmentManager
     * @param tag             标记值
     */
    public void showDilaog(FragmentManager fragmentManager, String tag) {
        showDilaog(fragmentManager, tag, null);
    }

    /**
     * 显示弹窗
     *
     * @param fragmentManager
     * @param tag             标记值
     * @param bundle          传递数据使用
     */
    public void showDilaog(FragmentManager fragmentManager, String tag, Bundle bundle) {
        if (fragmentManager == null && isShowing()) {
            return;
        }
        FragmentTransaction mTransaction = fragmentManager.beginTransaction();
        Fragment mFragment = fragmentManager.findFragmentByTag(tag);
        if (mFragment != null) { //为了不重复显示dialog，在显示对话框之前移除正在显示的对话框
            mTransaction.remove(mFragment);
        }
        //set数据
        if (bundle != null) {
            setArguments(bundle);
        }
        if (!isShowing()) {
            show(fragmentManager, tag);
        }
    }

    /**
     * 是否显示
     *
     * @return false:isHidden  true:isShowing
     */
    protected boolean isShowing() {
        if (this.getDialog() != null) {
            return this.getDialog().isShowing();
        } else {
            return false;
        }
    }


    /**
     * 设置外部区域是否消费事件
     *
     * @return
     */
    public boolean outCanOnTouch() {
        return false;
    }

    /**
     * 设置dialog显示时手机返回按钮的事件
     */
    public void setOnkeyListener(DialogInterface.OnKeyListener onkeyListener) {
        getDialog().setOnKeyListener(onkeyListener);
    }

    /**
     * 点击外部区域是否隐藏dailog
     *
     * @return
     */
    protected boolean setCanCancel() {
        return true;
    }

    /**
     * @return
     */
    protected boolean setCanceledOnTouchOutside() {
        return true;
    }

    /**
     * 设置显示的位置（默认显示在中间）
     *
     * @return
     */
    protected int setGravity() {
        return Gravity.CENTER;
    }

    /**
     * 设置宽（默认MATCH_PARENT）
     *
     * @return
     */
    protected int setLayoutParamsWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    /**
     * 设置高（WRAP_CONTENT）
     *
     * @return
     */
    protected int setLayoutParamsHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 是否设置背景透明
     *
     * @return
     */
    protected boolean isTransparent() {
        return false;
    }

    /**
     * 获取传递过来是数据
     *
     * @param arguments
     */
    protected void loadData(Bundle arguments) {

    }

    /**
     * 设置监听事件
     */
    protected void setListener(View view) {

    }

    /**
     * 设置数据
     */
    protected void setData() {

    }

    /**
     * 初始换数据
     */
    protected void initData() {

    }

    /**
     * 初始化View
     *
     * @param view
     */
    protected void initView(View view) {

    }

    /**
     * 设置dialog的样式
     * 一个基础的,正常样式的dialog,  DialogFragment.STYLE_NORMAL;
     * <p>
     * 这个样式不包括标题区, DialogFragment.STYLE_NO_TITLE;
     * <p>
     * 这个样式无任何框架,  DialogFragment.STYLE_NO_FRAME;
     * <p>
     * 禁用所有输入对话框,用户不能触摸它，它的窗口将不能接收输入焦点 DialogFragment.STYLE_NO_INPUT
     */
    protected int setDialogStyle() {
        return DialogFragment.STYLE_NO_FRAME;
    }

    /**
     * 设置Dialog的主题
     *
     * @return
     */
    protected int setDialogThem() {
        return R.style.BaseDialog;
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    protected abstract int bindView();

    /**
     * 设置动画
     * 0 从下往上  1 从上往下  2中间
     *
     * @return
     */
    protected int setAnimation() {
        return 2;
    }

    /**
     * 获取动画属性
     *
     * @param animation 0 从下往上  1 从上往下  2中间
     */
    private int getAnimation(int animation) {
        switch (animation) {
            case 0:
                return R.style.DialogAnimationBottomToTop;
            case 1:
                return R.style.DialogAnimationTopToBottom;
            case 2:
                return R.style.DialogAnimationCenter;
        }
        return 2;
    }
}
