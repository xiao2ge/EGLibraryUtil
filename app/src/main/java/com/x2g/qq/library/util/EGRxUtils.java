package com.x2g.qq.library.util;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EGRxUtils {

    private static HashMap<IRxNext, Disposable> map = new HashMap<>();

    /**
     * 每隔milliseconds毫秒后执行next操作
     */
    public static void interval(long milliseconds, IRxNext iRxNext) {
        Disposable subscribe = Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (iRxNext != null) {
                        iRxNext.doNext(aLong);
                    }
                });
        map.put(iRxNext, subscribe);
    }

    /**
     * milliseconds毫秒后执行next操作一次
     */
    public static void timer(long milliseconds, IRxNext iRxNext) {
        Disposable subscribe = Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (iRxNext != null) {
                        iRxNext.doNext(aLong);
                    }
                    cancel(iRxNext);
                });
        map.put(iRxNext, subscribe);
    }

    /**
     * 取消订阅
     */
    public static void cancel(IRxNext next) {
        if (map.containsKey(next)) {
            Disposable sub = map.get(next);
            if (sub != null && !sub.isDisposed()) {
                sub.dispose();
            }
            map.remove(next);
        }
    }

    public interface IRxNext {
        void doNext(long number);
    }
}
