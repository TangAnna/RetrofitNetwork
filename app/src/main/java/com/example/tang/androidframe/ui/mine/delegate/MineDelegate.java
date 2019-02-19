package com.example.tang.androidframe.ui.mine.delegate;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.delegate.ColorToolbarDelegate;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 13:45
 */
public class MineDelegate extends ColorToolbarDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitle("我的");
    }
}
