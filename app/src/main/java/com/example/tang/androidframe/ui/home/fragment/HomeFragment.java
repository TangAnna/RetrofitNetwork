package com.example.tang.androidframe.ui.home.fragment;

import com.example.tang.androidframe.baseui.fragment.BaseFragmentPresenter;
import com.example.tang.androidframe.ui.home.delegate.HomeDelegate;

/**
 * @author TangAnna
 * @description: 首页
 * @date :${DATA} 13:44
 */
public class HomeFragment extends BaseFragmentPresenter<HomeDelegate> {
    @Override
    protected Class<HomeDelegate> getDelegateClass() {
        return HomeDelegate.class;
    }
}
