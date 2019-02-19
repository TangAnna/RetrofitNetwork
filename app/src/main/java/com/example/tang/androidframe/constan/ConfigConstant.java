package com.example.tang.androidframe.constan;

import com.example.tang.androidframe.BuildConfig;

/**
 * @author TangAnna
 * @description: 配置资源
 * @date :${DATA} 17:41
 */
public interface ConfigConstant {
    /**
     * provider的值
     */
    String PROVIDER_STR = BuildConfig.APPLICATION_ID + ".fileprovider";

    /**
     * 与H5 交互时使用的代理
     */
    String H5_DELEGATE = "gotoAndroidApp";
}
