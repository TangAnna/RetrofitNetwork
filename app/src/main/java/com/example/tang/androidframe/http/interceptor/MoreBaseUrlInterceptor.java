package com.example.tang.androidframe.http.interceptor;

import android.text.TextUtils;

import com.example.tang.androidframe.constan.ApiConstant;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author TangAnna
 * @description: 多个BaseUrl切换的拦截器  根据Headers切换baseUrl
 * @date :${DATA} 15:21
 */
public class MoreBaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求体
        Request request = chain.request();
        //获取旧的BaseUrl
        HttpUrl oldUrl = request.url();
        //获取request的创建者Builder
        Request.Builder builder = request.newBuilder();
        List<String> urlNameList = request.headers(ApiConstant.HEADER_NAME_URL);
        if (urlNameList != null && urlNameList.size() > 0) {
            //删除原有的header值
            builder.removeHeader(ApiConstant.HEADER_NAME_URL);
            //获取header中的value
            String headerValue = urlNameList.get(0);
            HttpUrl baseURL = null;
            if (TextUtils.equals(headerValue, ApiConstant.HEADER_NAME_URL_COMPANY)) {//公司的标记
                baseURL = HttpUrl.parse(ApiConstant.BASE_URL_COMPANY);
            } else if (TextUtils.equals(headerValue, ApiConstant.HEADER_NAME_URL_SANFANG)) {//三方的标记值
                baseURL = HttpUrl.parse(ApiConstant.BASE_URL_SANFANG);
            }
//            //重建新的HttpUrl，需要重新设置的url部分
            HttpUrl newHttpUrl = oldUrl.newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
                    .port(baseURL.port())//端口
                    .build();
            //获取处理后的新newRequest
            Request newRequest = builder.url(newHttpUrl).build();
            return chain.proceed(newRequest);
        } else {
            return chain.proceed(request);
        }
    }
}
