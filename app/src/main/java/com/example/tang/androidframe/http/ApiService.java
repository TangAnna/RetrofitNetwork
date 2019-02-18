package com.example.tang.androidframe.http;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 11:54
 */
public interface ApiService {

    /**
     * 登录
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
//    @Headers(HEADER_COMPANY)
    @FormUrlEncoded
    @POST(ApiRoute.LOGIN)
    Observable<ResponseBody> login(
            @Field("phone") String phone,
            @Field("code") String code
    );
}
