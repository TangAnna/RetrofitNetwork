package com.example.tang.androidframe.http;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tang.androidframe.R;

/**
 * @author TangAnna
 * @description: 加载loading
 * @date :${DATA} 17:06
 */
public class LoadingDialog extends Dialog {

    private Context mContext;
    private ImageView mIvImg;
    private TextView mTvText;

    public LoadingDialog(Context context) {
        super(context);
        mContext = context;

        setContentView(R.layout.dialog_loading);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        setCanceledOnTouchOutside(false);

        mIvImg = findViewById(R.id.iv_dialog_img);
        mTvText = findViewById(R.id.tv_dialog_text);
        //设置加载动画
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        animation.setInterpolator(new LinearInterpolator());
        mIvImg.startAnimation(animation);
    }

    /**
     * 设置加载中的提示语
     *
     * @param text
     */
    public void setHintText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTvText.setText(text);
        }
    }

    private DialogStopInterface mDialogStopInterface;

    public void setDialogStopInterface(DialogStopInterface dialogStopInterface) {
        mDialogStopInterface = dialogStopInterface;
    }

    /**
     * 当dialog消失的时候调用
     */
    public interface DialogStopInterface {
        void onStopListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDialogStopInterface != null) {
            mDialogStopInterface.onStopListener();
        }
    }
}
