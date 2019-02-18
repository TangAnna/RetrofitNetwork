package com.example.tang.androidframe.constan;


/**
 * @author TangAnna
 * @description: Api有关的配置信息
 * @date :${DATA} 11:33
 */
public interface ApiConstant {
    /**
     * 第三方BASE_URL
     */
    String BASE_URL_SANFANG = "http://restfultest.sz8.cn/";
    /**
     * 公司的BASE_URL
     */
    String BASE_URL_COMPANY = "http://api.hongcanting.com/";

    /**
     * 网络请求的headerName  Key
     */
    String HEADER_NAME_URL = "urlName";

    /**
     * 网络请求的headerName  value  公司的
     */
    String HEADER_NAME_URL_COMPANY = "company";

    /**
     * 网络请求的headerName  value  三方的
     */
    String HEADER_NAME_URL_SANFANG = "sanfang";
}
