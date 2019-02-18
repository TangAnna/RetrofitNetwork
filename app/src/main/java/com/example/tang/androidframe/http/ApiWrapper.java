package com.example.tang.androidframe.http;

import com.example.tang.androidframe.BuildConfig;
import com.example.tang.androidframe.http.converter.CustomGsonConverterFactory;
import com.example.tang.androidframe.http.converter.NobodyConverterFactory;
import com.example.tang.androidframe.http.interceptor.LoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tang.androidframe.http.TrustSSLFactory.DO_NOT_VERIFY;


/**
 * 网络请求管理器
 */
public class ApiWrapper {

    private static ApiWrapper mInstance = new ApiWrapper();
    private static final int DEFAULT_TIMEOUT = 5;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private ApiService mApiService;

    private ApiWrapper() {
        createOkClient();
        createRetrofit();
        createApi();
    }

    protected void createOkClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//请求超时时间
                .addInterceptor(new LoggingInterceptor())//打印日志
//                .addInterceptor(new MoreBaseUrlInterceptor())//添加多个baseURL的切换拦截器
//                .addInterceptor(new HeaderInterceptor())//添加header
                .sslSocketFactory(TrustSSLFactory.getSocketFactory()) //忽略证书
                .hostnameVerifier(DO_NOT_VERIFY)
                .build();
    }

    protected void createRetrofit() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                //添加适配器
                .addConverterFactory(CustomGsonConverterFactory.create())  //设置json格式放宽时使用
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.SERVER_ADD)
                .build();
    }


    private void createApi() {
        mApiService = mRetrofit.create(ApiService.class);
    }

    //获取单例
    public static ApiWrapper getInstance() {
        return mInstance;
    }

    public static ApiService getApiService() {
        return getInstance().mApiService;
    }
}
