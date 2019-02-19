package com.example.tang.androidframe.baseui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tang.androidframe.R;


/**
 * @author TangAnna
 * @description: 加载出错页面
 * @date :${DATA} 18:58
 */
public class ErrorView extends RelativeLayout implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mTvRefresh;

    public ErrorView(Context context) {
        super(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.baseui_error_view, this);
        mImageView = findViewById(R.id.iv_error_img);
        mTextView = findViewById(R.id.tv_error_text);
        mTvRefresh = findViewById(R.id.tv_error_refresh);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ErrorView);
        //获取提示文字
        String hintText = typedArray.getString(R.styleable.ErrorView_hintErrorText);
        //获取图片资源
        int imgResourceId = typedArray.getResourceId(R.styleable.ErrorView_emptyErrorImg, R.drawable.default_error_icon);
        if (!TextUtils.isEmpty(hintText)) {
            mTextView.setText(hintText);
        }
        mImageView.setImageResource(imgResourceId);
        typedArray.recycle();
        mTvRefresh.setOnClickListener(this);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * 设置提示语
     *
     * @param data
     */
    public void setHintErrorText(String data) {
        if (TextUtils.isEmpty(data)) {
            return;
        }
        mTextView.setText(data);
    }

    /**
     * 设置图片资源
     *
     * @param imgId
     */
    public void setImageErrorView(int imgId) {
        mImageView.setImageResource(imgId);
    }

    private OnErrorListener mOnErrorListener;

    /**
     * 设置刷新的操作
     *
     * @param onErrorListener
     */
    public void setOnErrorListener(OnErrorListener onErrorListener) {
        mOnErrorListener = onErrorListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_error_refresh:
                if (mOnErrorListener != null) {
                    mOnErrorListener.errorListener();
                }
                break;
        }

    }

    public interface OnErrorListener {
        void errorListener();
    }
}
