package com.example.tang.androidframe.utils;

import android.util.Log;

import com.example.tang.androidframe.BuildConfig;

/**
 * @author TangAnna
 * @description: 日志工具
 * @date :${DATA} 11:38
 */
public class LogUtils {
    public static boolean debug = BuildConfig.DEBUG;
    private static final String FILTER = "TTTTT==========";

    public static void eNormal(String tag, String msg) {
        if (debug) {
            Log.e(FILTER + tag, "info: " + msg);
        }
    }

    public static void dNormal(String tag, String msg) {
        if (debug) {
            Log.d(FILTER + tag, "info: " + msg);
        }
    }

    public static void dNormal(String tag, Object msg) {
        if (debug) {
            Log.d(FILTER + tag, "info: " + msg.toString());
        }
    }

    public static void dNormal(String tag, String msg, Exception e) {
        if (debug) {
            Log.d(FILTER + tag, "异常==" + e.toString() + msg);
        }
    }

    public static void dNormal(String msg) {
        if (debug) {
            Log.d(FILTER, "info: " + msg);
        }
    }

    /**
     * 打印网络请求的日志
     *
     * @param msg
     */
    public static void dNetworkRequestLog(String msg) {
        if (debug) {
            Log.d(FILTER, "info: " + msg);
        }
    }

    /**
     * 打印网络请求的日志
     *
     * @param msg
     */
    public static void dNetworkRequestLog(String tag, String msg) {
        if (debug) {
            Log.d(tag, "info: " + msg);
        }
    }
}
