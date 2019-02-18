package com.example.tang.androidframe.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 线程切换 子线程切换到主线程
 */
public class DefaultTransformer<T> implements Observable.Transformer<T, T> {

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())//指定网络请求在io后台线程中进行
                .observeOn(AndroidSchedulers.mainThread());//指定observer回调在UI主线程中进行
    }

}
