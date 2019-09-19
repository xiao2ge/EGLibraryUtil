package com.x2g.qq.library.util;

import android.util.Log;

public class EGLogUtils {

    public static void i(String tag, String msg) {
        if (EGUtilManager.isDebug()) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (EGUtilManager.isDebug()) {
            Log.e(tag, msg);
        }
    }

}
