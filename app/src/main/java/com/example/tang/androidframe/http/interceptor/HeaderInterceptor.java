package com.example.tang.androidframe.http.interceptor;

import com.example.tang.androidframe.cache.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author TangAnna
 * @description: 添加header的拦截器
 * @date :${DATA} 14:50
 */
public class HeaderInterceptor implements Interceptor {
    private static final String TOKEN = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();

        //添加token
        Observable.just(SpUtils.getToken())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String token) {
                        builder.addHeader(TOKEN, token);
                    }
                });

        //添加...

        return chain.proceed(builder.build());
    }
}
