package com.example.tang.androidframe.ui.mine.fragment;

import com.example.tang.androidframe.baseui.fragment.BaseFragmentPresenter;
import com.example.tang.androidframe.ui.mine.delegate.MineDelegate;

/**
 * @author TangAnna
 * @description: 我的
 * @date :${DATA} 13:44
 */
public class MineFragment extends BaseFragmentPresenter<MineDelegate> {

    @Override
    protected Class<MineDelegate> getDelegateClass() {
        return MineDelegate.class;
    }
}
