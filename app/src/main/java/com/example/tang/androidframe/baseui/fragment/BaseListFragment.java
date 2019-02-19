package com.example.tang.androidframe.baseui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.widget.EmptyView;
import com.example.tang.androidframe.baseui.widget.ErrorView;
import com.example.tang.androidframe.utils.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author TangAnna
 * @description: 列表页面的基类
 * @date :${DATA} 14:14
 */
public abstract class BaseListFragment<T> extends Fragment implements OnRefreshLoadMoreListener {

    private View mRootView;
    protected SmartRefreshLayout mSmartRefreshLayout;
    public RecyclerView mRecyclerView;
    public EmptyView mEmptyView;
    public ErrorView mErrorView;
    private List<T> mData = new ArrayList<>();
    /**
     * 动作  0： 刷新  1： 加载更多
     */
    public int mAction = 0;
    public int mCurrentPage = 1;
    public int mTotalPage = 1;
    /**
     * 一页的数量
     */
    public int mPageSize = 20;
    //不分页时使用的一个最大值
    public int mMaxPageSize = 200000;
    private CommonAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_base_list, null);
        LogUtils.dNormal("onCreateView", "onCreateView");
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        LogUtils.dNormal("onViewCreated", "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.dNormal("onActivityCreated", "onActivityCreated");
        loadData();
    }

    private void initView(View rootView) {

        mEmptyView = rootView.findViewById(R.id.emptyView);
        mErrorView = rootView.findViewById(R.id.errorView);
        mSmartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);

        mRecyclerView = rootView.findViewById(R.id.dataView);
        mRecyclerView.setLayoutManager(bindLayoutManager());
        mRecyclerView.setAdapter(mAdapter = new CommonAdapter<T>(getContext(), bindItemView(), mData) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                bindItemData(holder, t, position);
            }
        });
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mOnItemListener != null) {
                    mOnItemListener.itemClickListener(view, holder, position, mData.get(position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    /**
     * 最先走的方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.dNormal("setUserVisibleHint", "setUserVisibleHint");

    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        //刷新操作
        if (mAction == 0) {
            mData.clear();
        }
        if (data != null && data.size() > 0) {
            mData.addAll(data);
        }
        if (mData.size() < 1) {//没有数据
            setEmptyView();
        } else {
            setDataView();
            mAdapter.notifyDataSetChanged();
        }
        finishRefreshAndLoadMore();
    }

    /**
     * 刷新
     */
    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * itemView 设置数据
     *
     * @param holder
     * @param t
     * @param position
     */
    protected abstract void bindItemData(ViewHolder holder, T t, int position);


    /**
     * 列表itemView
     *
     * @return
     */
    public abstract int bindItemView();

    /**
     * 加载数据
     */
    public abstract void loadData();

    /**
     * 绑定 LayoutManager  （默认是线性布局）
     *
     * @return
     */
    public RecyclerView.LayoutManager bindLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    /**
     * 设置空页面
     *
     * @param b
     */
    public void setEmptyView(boolean b) {
        mEmptyView.setVisibility(b ? VISIBLE : GONE);
        mRecyclerView.setVisibility(b ? GONE : VISIBLE);
    }

    /**
     * 加载更多
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mAction = 1;
        if (mCurrentPage >= mTotalPage) {//已经是最后一页了没有更多数据
            mSmartRefreshLayout.setNoMoreData(true);//不在显示加载更多
            mSmartRefreshLayout.finishLoadMore();
        } else {
            mCurrentPage++;
            loadData();
        }
    }

    /**
     * 刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mSmartRefreshLayout.setNoMoreData(false);
        mAction = 0;
        mCurrentPage = 1;
        loadData();
    }

    /**
     * 关闭刷新和加载更多是动画
     */
    public void finishRefreshAndLoadMore() {
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }

    /**
     * 背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mRootView.setBackgroundColor(color);
    }

    private OnItemListener mOnItemListener;

    /**
     * 设置点击事件
     *
     * @param onItemListener
     */
    public void setOnItemListener(OnItemListener onItemListener) {
        mOnItemListener = onItemListener;
    }

    public interface OnItemListener<T> {
        void itemClickListener(View view, RecyclerView.ViewHolder holder, int position, T t);
    }

    /**
     * 设置是否可以加载更多
     *
     * @param b
     */
    public void setEnableLoadMore(boolean b) {
        mSmartRefreshLayout.setEnableLoadMore(b);
    }

    /**
     * 设置空页面
     */
    public void setEmptyView() {
        mEmptyView.setVisibility(VISIBLE);
        mRecyclerView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
    }

    /**
     * 设置错误页面
     */
    public void setErrorView() {
        mEmptyView.setVisibility(GONE);
        mRecyclerView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
    }

    /**
     * 设置数据页面
     */
    public void setDataView() {
        mEmptyView.setVisibility(GONE);
        mRecyclerView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
    }
}
