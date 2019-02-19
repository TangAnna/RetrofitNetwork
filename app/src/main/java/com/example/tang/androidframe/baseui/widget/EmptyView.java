package com.example.tang.androidframe.baseui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tang.androidframe.R;


/**
 * @author TangAnna
 * @description: 空页面
 * @date :${DATA} 18:58
 */
public class EmptyView extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
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
        inflate(context, R.layout.baseui_empty_view, this);
        mImageView = findViewById(R.id.iv_empty_img);
        mTextView = findViewById(R.id.tv_empty_text);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView);
        //获取提示文字
        String hintText = typedArray.getString(R.styleable.EmptyView_hintText);
        //获取图片资源
        int imgResourceId = typedArray.getResourceId(R.styleable.EmptyView_emptyImg, R.drawable.default_empty_icon);
        if (!TextUtils.isEmpty(hintText)) {
            mTextView.setText(hintText);
        }
        mImageView.setImageResource(imgResourceId);
        typedArray.recycle();
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * 设置提示语
     *
     * @param data
     */
    public void setHintText(String data) {
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
    public void setImageView(int imgId) {
        mImageView.setImageResource(imgId);
    }
}
