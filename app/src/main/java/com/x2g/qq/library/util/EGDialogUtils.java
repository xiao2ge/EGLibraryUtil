package com.x2g.qq.library.util;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.kaopiz.kprogresshud.KProgressHUD;

public class EGDialogUtils {

    public static EGCustomDialog showDefaultDialog(Context context, String title, String msg,
                                                   String okText, final DialogInterface.OnClickListener okListener) {
        return showDefaultDialog(context, title, msg, okText, okListener, "取消", null);
    }

    public static EGCustomDialog showDefaultDialog(Context context, String title, String msg,
                                                   String okText, final DialogInterface.OnClickListener okListener,
                                                   String cancelText, final DialogInterface.OnClickListener cancelListener) {
        return showDefaultDialog(context, title, msg, false, "", okText, okListener, cancelText, cancelListener, true, false);
    }

    /**
     * 显示一个默认Dialog
     *
     * @param context                上下文
     * @param title                  标题
     * @param msg                    提示
     * @param showInput              显示输入框
     * @param inputHint              输入框hint
     * @param okText                 确定按钮文字
     * @param okListener             确定按钮点击监听
     * @param cancelText             取消按钮文字
     * @param cancelListener         取消按钮点击监听
     * @param cancelable             返回键消失
     * @param canceledOnTouchOutside 点击窗口外消失
     * @return
     */
    public static EGCustomDialog showDefaultDialog(Context context, String title, String msg, boolean showInput, String inputHint,
                                                   String okText, final DialogInterface.OnClickListener okListener,
                                                   String cancelText, final DialogInterface.OnClickListener cancelListener,
                                                   final boolean cancelable, boolean canceledOnTouchOutside) {
        final EGCustomDialog dialog = new EGCustomDialog(context);
        boolean single = false;
        if (TextUtils.isEmpty(okText) || TextUtils.isEmpty(cancelText)) {
            single = true;
        }
        dialog
                .setTitle(title)
                .setMessage(msg)
                .setShowInput(showInput)
                .setHint(inputHint)
                .setNegtive(cancelText)
                .setPositive(okText)
                .setSingle(single)
                .setOnClickBottomListener(new EGCustomDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        if (okListener != null) {
                            okListener.onClick(dialog, 1);
                        }
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                        if (cancelListener != null) {
                            cancelListener.onClick(dialog, 0);
                        }
                    }
                }).show();
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return dialog;
    }

    /**
     * 显示包含一个输入框的Dialog
     *
     * @param context        上下文
     * @param title          标题
     * @param msg            提示
     * @param hint           输入框1的hint
     * @param okText         确定按钮文字
     * @param okListener     确定按钮点击监听
     * @param cancelText     取消按钮文字
     * @param cancelListener 取消按钮点击监听
     * @return
     */
    public static EGCustomDialog showSingleInputDialog(Context context,
                                                       String title,
                                                       String msg,
                                                       String hint,
                                                       String okText, final DialogInterface.OnClickListener okListener,
                                                       String cancelText, final DialogInterface.OnClickListener cancelListener) {
        final EGCustomDialog dialog = new EGCustomDialog(context);
        boolean single = false;
        if (TextUtils.isEmpty(okText) || TextUtils.isEmpty(cancelText)) {
            single = true;
        }
        dialog
                .setTitle(title)
                .setMessage(msg)
                .setShowInput(true)
                .setHint(hint)
                .setNegtive(cancelText)
                .setPositive(okText)
                .setSingle(single)
                .setOnClickBottomListener(new EGCustomDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        if (okListener != null) {
                            okListener.onClick(dialog, 1);
                        }
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                        if (cancelListener != null) {
                            cancelListener.onClick(dialog, 0);
                        }
                    }
                }).show();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * 显示包含两个输入框的Dialog
     *
     * @param context        上下文
     * @param title          标题
     * @param msg            提示
     * @param hint           输入框1的hint
     * @param hint2          输入框2的hint
     * @param okText         确定按钮文字
     * @param okListener     确定按钮点击监听
     * @param cancelText     取消按钮文字
     * @param cancelListener 取消按钮点击监听
     * @return
     */
    public static EGCustomDialog showTwoInputDialog(Context context,
                                                    String title, String msg,
                                                    String hint, String hint2,
                                                    String okText, final DialogInterface.OnClickListener okListener,
                                                    String cancelText, final DialogInterface.OnClickListener cancelListener) {
        final EGCustomDialog dialog = new EGCustomDialog(context);
        boolean single = false;
        if (TextUtils.isEmpty(okText) || TextUtils.isEmpty(cancelText)) {
            single = true;
        }
        dialog
                .setTitle(title)
                .setMessage(msg)
                .setShowInput(true)
                .setHint(hint)
                .setShowInput2(true)
                .setHint2(hint2)
                .setNegtive(cancelText)
                .setPositive(okText)
                .setSingle(single)
                .setOnClickBottomListener(new EGCustomDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        if (okListener != null) {
                            okListener.onClick(dialog, 1);
                        }
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog.dismiss();
                        if (cancelListener != null) {
                            cancelListener.onClick(dialog, 0);
                        }
                    }
                }).show();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * 显示等待框
     *
     * @param context 上下文
     * @param msg     提示内容
     * @return
     */
    public static KProgressHUD showWaitDialog(Context context, String msg) {
        KProgressHUD hud = new KProgressHUD(context);
        hud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(msg)
                .setCancellable(false)
                .setAnimationSpeed(1)
                .setDimAmount(0.2f)
                .show();
        return hud;
    }

    /**
     * 显示进度
     *
     * @param context    上下文
     * @param msg        提示内容
     * @param percentage 百分比
     * @return
     */
    public static KProgressHUD showProgressDialog(Context context, String msg, int percentage) {
        KProgressHUD hud = new KProgressHUD(context);
        hud.setStyle(KProgressHUD.Style.PIE_DETERMINATE)
                .setLabel(msg)
                .setCancellable(false)
                .setMaxProgress(100)
                .show();
        hud.setProgress(percentage);
        return hud;
    }
}
