package com.x2g.qq.library.util;

import android.widget.Toast;

public class EGToastUtils {

    private static Toast mToast = null;

    public static void showLong(String info) {
        if (mToast == null) {
            mToast = Toast.makeText(EGUtilManager.getApplication(), info, Toast.LENGTH_LONG);
        } else {
            mToast.setText(info);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
        EGLogUtils.i("Toast", info);
    }

    public static void showShort(String info) {
        if (mToast == null) {
            mToast = Toast.makeText(EGUtilManager.getApplication(), info, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(info);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
        EGLogUtils.i("Toast", info);
    }

}
