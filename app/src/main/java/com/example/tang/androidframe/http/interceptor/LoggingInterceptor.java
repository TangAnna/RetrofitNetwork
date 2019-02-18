package com.example.tang.androidframe.http.interceptor;

import com.example.tang.androidframe.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import static okhttp3.internal.Util.UTF_8;

/**
 * @author TangAnna
 * @description: 网络请求日志输出
 * @date :${DATA} 11:38
 */

public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtils.dNetworkRequestLog("===========请求开始===========");
        LogUtils.dNetworkRequestLog("请求地址：| " + request.toString());
        if (request.body() != null) {
            printParams(request.body());
        }
        LogUtils.dNetworkRequestLog("httpCode：| Response:" + response.code());
        if (response.code() != 200) {
            LogUtils.dNormal("异常数据 | Response:", content);
        } else {
            LogUtils.dNetworkRequestLog("请求体返回：| Response:" + content);
        }
        LogUtils.dNetworkRequestLog("----------请求耗时:" + duration + "毫秒----------");
        LogUtils.dNetworkRequestLog("============请求结束==========");
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

    private void printParams(RequestBody body) {
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF_8);
            }
            String params = buffer.readString(charset);
            LogUtils.dNetworkRequestLog("请求参数： | " + params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
