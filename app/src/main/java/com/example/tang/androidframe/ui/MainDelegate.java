package com.example.tang.androidframe.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.widget.BottomBarItem;
import com.example.tang.androidframe.baseui.widget.BottomBarLayout;
import com.example.tang.androidframe.baseui.widget.CustomViewPager;
import com.example.tang.androidframe.ui.adapter.MainTabAdapter;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 11:32
 */
public class MainDelegate extends AppDelegate implements BottomBarLayout.OnItemSelectedListener {

    private FragmentManager mFragmentManager;
    private CustomViewPager mCustomViewPager;
    private BottomBarLayout mBottomBarLayout;
    private List<Fragment> mFragments;
    private MainTabAdapter mAdapter;
    private int mLastPosition = -1;//记录上一次点击的position

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mFragments = new ArrayList<>();

        mCustomViewPager = get(R.id.vp_main_viewpager);
        mCustomViewPager.setScanScroll(false);//禁止ViewPager的滑动

        mBottomBarLayout = get(R.id.bl_main_bottomLayout);
        mBottomBarLayout.setOnItemSelectedListener(this);
    }

    /**
     * 要在 onCreate()方法的super之前调用此方法
     *
     * @param mFragmentManager
     */
    public void setFragmentManager(FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }

    /**
     * 添加Fragment
     *
     * @param fragmentList
     */
    public void addFragment(List<Fragment> fragmentList) {
        if (fragmentList != null && fragmentList.size() > 0) {
            mFragments.addAll(fragmentList);
        }

        mAdapter = new MainTabAdapter(mFragmentManager, mFragments);
        mCustomViewPager.setAdapter(mAdapter);
        setCurrentPage(0);
        //设置预加载Fragment的数量
        mCustomViewPager.setOffscreenPageLimit(mFragments.size());
        mBottomBarLayout.setViewPager(mCustomViewPager);
    }

    /**
     * 设置当前页面显示第几个
     *
     * @param position
     */
    public void setCurrentPage(int position) {
        if (position < 0 || position > mFragments.size() - 1) {
            return;
        }
        mCustomViewPager.setCurrentItem(position);
    }

    /**
     * 页面切换
     *
     * @param bottomBarItem 点击页面的BottomBarItem
     * @param position      点击页面的position
     */
    @Override
    public void onItemSelected(BottomBarItem bottomBarItem, int position) {
    }
}
