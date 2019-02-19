package com.example.tang.androidframe.baseui.delegate;

/**
 * @author TangAnna
 * @description: Toolbar左侧带有返回箭头的 TabViewPager
 * @date :${DATA} 15:53
 */
public class BackToolbarTabToolbarDelegate extends ToolbarTabViewPageDelegate {
    @Override
    public void initWidget() {
        super.initWidget();
        setShowLeft();
    }
}
