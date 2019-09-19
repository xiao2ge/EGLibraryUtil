package com.x2g.qq.library.util;

import android.os.CountDownTimer;
import android.widget.TextView;

public class EGCountDownTimer extends CountDownTimer {
    private TextView tv;

    /**
     * @param millisInFuture    总时长（毫秒）
     * @param countDownInterval 每次减的时长（毫秒）
     * @param tv                绑定的控件
     */
    public EGCountDownTimer(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    //计时过程
    @Override
    public void onTick(long l) {
        //防止计时过程中重复点击
        tv.setClickable(false);
        tv.setText(l / 1000 + "s");

    }

    //计时完毕的方法
    @Override
    public void onFinish() {
        cancel();
        //重新给Button设置文字
        tv.setText("重新获取");
        //设置可点击
        tv.setClickable(true);
    }
}
