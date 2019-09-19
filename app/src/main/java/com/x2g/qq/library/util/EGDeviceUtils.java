package com.x2g.qq.library.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;

public class EGDeviceUtils {
    private static final String TAG = "DeviceUtils";

    /**
     * 读取网络类型。<br>
     * 0为移动网络、1为WIFI、-1为无网络
     */
    public static int getNetworkType(Application application) {
        try {
            ConnectivityManager manager = (ConnectivityManager) application
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE
                        || netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    EGLogUtils.i(TAG, "网络类型:" + (netInfo.getType() == 0 ? "移动网络" : "WIFI"));
                    return netInfo.getType();
                } else {
                    EGLogUtils.i(TAG, "无网络");
                    return -1;
                }
            } else {
                EGLogUtils.i(TAG, "无网络");
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 读取网络类型。<br>
     * 0为移动网络、1为WIFI、-1为无网络
     */
    public static String getNetworkName(Application application) {
        int type = getNetworkType(application);
        if (type == 0) {
            return "MOBILE";
        } else if (type == 1) {
            return "WIFI";
        } else {
            return "None";
        }
    }

    /**
     * 获取IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getImei(Application application) {
        try {
            TelephonyManager manager = (TelephonyManager) application
                    .getSystemService(Context.TELEPHONY_SERVICE);
            EGLogUtils.i(TAG, "获取IMEI:" + manager.getDeviceId());
            return manager.getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getImsi(Application application) {
        try {
            TelephonyManager manager = (TelephonyManager) application
                    .getSystemService(Context.TELEPHONY_SERVICE);
            EGLogUtils.i(TAG, "获取IMsi:" + manager.getSubscriberId());
            return manager.getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回当前程序版本名称
     */
    public static String getAppVersionName() {
        String versionName = "";
        try {
            PackageManager pm = EGUtilManager.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(EGUtilManager.getApplication().getPackageName(), 0);
            versionName = pi.versionName;
            EGLogUtils.i(TAG, "获取当前程序版本名称:versionName=" + versionName);
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            EGLogUtils.e("获取当前程序版本名称", e.getMessage());
        }
        return versionName;
    }

    /**
     * 返回当前程序版本号
     */
    public static int getAppVersionCode() {
        int versionCode = -1;
        try {
            PackageManager pm = EGUtilManager.getApplication().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(EGUtilManager.getApplication().getPackageName(), 0);
            versionCode = pi.versionCode;
            EGLogUtils.i(TAG, "返回当前程序版本号:versionCode=" + versionCode);
        } catch (Exception e) {
            EGLogUtils.e("返回当前程序版本号", e.getMessage());
        }
        return versionCode;
    }

    /**
     * 安装APK
     *
     * @param apk
     */
    public static void installAPK(Activity activity, File apk) {
        try {
            EGLogUtils.i(TAG, "安装APK:path=" + apk.getAbsolutePath());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(EGUtilManager.getApplication(), EGUtilManager.getFileProvider(), apk);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     * @param requestCode result requestCode
     */
    public static void startActionCapture(Activity activity, File file, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, file));
        activity.startActivityForResult(intent, requestCode);
    }

    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), EGUtilManager.getFileProvider(), file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 目标手机号
     * @param message     消息内容
     */
    public static void sendSMS(String phoneNumber, String message) {
        EGLogUtils.i(TAG, "sendSMS:" + phoneNumber + "----" + message);
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

    /**
     * 打电话
     *
     * @param phoneNumber
     */
    public static void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        EGUtilManager.getApplication().startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneNumber(Application application) {
        try {
            TelephonyManager manager = (TelephonyManager) application
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (manager == null) {
                return "";
            } else {
                return manager.getLine1Number();
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机品牌
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机系统版本
     */
    public static String getVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机SDK版本
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机屏幕宽
     */
    public static int getScreenWidth(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * 获取手机屏高宽
     */
    public static int getScreenHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    @SuppressLint("MissingPermission")
    public static int getOperators(Context mActivity) {
        try {
            TelephonyManager telManager = (TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = telManager.getSubscriberId();
            if (imsi != null) {
                if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                    // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                    return 1;// 中国移动
                } else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
                    return 2;// 中国联通
                } else if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
                    return 4;// 中国电信
                }
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getOperatorsName(Context activity) {
        int type = getOperators(activity);
        if (type == 1) {
            return "中国移动";
        } else if (type == 2) {
            return "中国联通";
        } else if (type == 4) {
            return "中国电信";
        } else {
            return "";
        }
    }

    public static void hideKeyboard(View view) {
        try {
            InputMethodManager im = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
