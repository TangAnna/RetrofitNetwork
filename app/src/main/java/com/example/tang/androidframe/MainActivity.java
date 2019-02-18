package com.example.tang.androidframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tang.androidframe.http.ApiWrapper;
import com.example.tang.androidframe.http.DefaultTransformer;
import com.example.tang.androidframe.http.LoadingSubscriber;
import com.example.tang.androidframe.utils.ToastUtils;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 测试登录接口是否能调用通
        ApiWrapper.getApiService().login("18201399961", "123122")
                .compose(new DefaultTransformer<ResponseBody>())
                .subscribe(new LoadingSubscriber<ResponseBody>(MainActivity.this, true) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        ToastUtils.showToast("登录成功");
                    }
                });
    }
}
