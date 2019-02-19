package com.example.tang.androidframe.baseui.delegate;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.fragment.BaseFragmentPresenter;

import java.util.ArrayList;

/**
 * @author TangAnna
 * @description: TabLayout + ViewPager
 * @date :${DATA} 15:48
 */
public class TabViewPageDelegate extends BaseViewDelegate {

    private FragmentManager mFragmentManager;
    private ArrayList<String> mTitleList = new ArrayList<String>();
    private ArrayList<BaseFragmentPresenter> arrayListFragment = new ArrayList<BaseFragmentPresenter>();
    private FragmentStatePagerAdapter mFragmentStatePagerAdapter;
    public TabLayout tabLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.tab_viewpager_delegate;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initViewPageTab();
    }

    public void addViewPageTab(String title, BaseFragmentPresenter fragment) {
        mTitleList.add(title);
        arrayListFragment.add(fragment);
        mFragmentStatePagerAdapter.notifyDataSetChanged();
    }

    public void setFragmentManager(FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }

    private void initViewPageTab() {
        tabLayout = get(R.id.tab_layout);
        final ViewPager viewPager = get(R.id.tab_viewPager);

        tabLayout.setupWithViewPager(viewPager);

        mFragmentStatePagerAdapter = new FragmentStatePagerAdapter(mFragmentManager) {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public Fragment getItem(int index)//直接创建fragment对象并返回
            {
                return arrayListFragment.get(index);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return mTitleList == null ? super.getPageTitle(position) : mTitleList.get(position);
            }
        };

        viewPager.setAdapter(mFragmentStatePagerAdapter);
    }
}
