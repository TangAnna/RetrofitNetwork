package com.example.tang.androidframe.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.tang.androidframe.FrameApplication;

/**
 * @author TangAnna
 * @description: UI 工具类
 * @date :${DATA} 17:56
 */
public class UIUtils {
    /**
     * 获取Resources对象
     */
    public static Resources getResources() {
        return FrameApplication.getContext().getResources();
    }

    /**
     * 获取资源文件中的String
     *
     * @param id
     * @return
     */
    public static String getIdString(int id) {
        return getResources().getString(id);
    }

    /**
     * 获取资源文件中的Drawable文件
     *
     * @param id
     * @return
     */
    public static Drawable getIdDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * 获取资源文件中的颜色值
     *
     * @param id
     * @return
     */
    public static int getIdColor(int id) {
        return getResources().getColor(id);
    }


    /**
     * dip-->px
     */
    public static int dip2Px(Context context, int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
