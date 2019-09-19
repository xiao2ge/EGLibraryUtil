package com.x2g.qq.library.util;

public class EGUIUtils {

    public static int px2dp(float pxValue) {
        final float scale = EGUtilManager.getApplication().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        float scale = EGUtilManager.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
