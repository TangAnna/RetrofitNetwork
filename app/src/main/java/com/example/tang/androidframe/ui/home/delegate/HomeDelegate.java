package com.example.tang.androidframe.ui.home.delegate;

import com.example.tang.androidframe.R;
import com.example.tang.androidframe.baseui.delegate.ToolbarDelegate;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 13:45
 */
public class HomeDelegate extends ToolbarDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitle("首页");
    }
}
