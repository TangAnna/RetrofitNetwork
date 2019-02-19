package com.example.tang.androidframe.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.tang.androidframe.baseui.activity.BaseActivityPresenter;
import com.example.tang.androidframe.ui.home.fragment.HomeFragment;
import com.example.tang.androidframe.ui.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivityPresenter<MainDelegate> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewDelegate.setFragmentManager(getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initFragment();
    }

    /**
     * 初始化数据
     */
    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MineFragment());
        viewDelegate.addFragment(fragmentList);
    }
}
