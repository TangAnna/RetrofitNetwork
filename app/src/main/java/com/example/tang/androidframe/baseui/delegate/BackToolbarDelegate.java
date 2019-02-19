package com.example.tang.androidframe.baseui.delegate;

/**
 * @author TangAnna
 * @description: 带有返回箭头的布局
 * @date :${DATA} 10:06
 */
public abstract class BackToolbarDelegate extends ToolbarDelegate {

    @Override
    public void initWidget() {
        super.initWidget();
        setShowLeft();
    }
}
