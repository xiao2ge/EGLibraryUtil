package com.x2g.qq.library.util;

import android.app.Application;

public class EGUtilManager {

    private static Application application;

    private static boolean debug;

    private static String fileProvider;

    public static void init(Application application, String fileProvider, boolean debug) {
        EGUtilManager.application = application;
        EGUtilManager.fileProvider = fileProvider;
        EGUtilManager.debug = debug;
    }

    public static Application getApplication() {
        return application;
    }

    public static String getFileProvider() {
        return fileProvider;
    }

    public static boolean isDebug() {
        return debug;
    }
}
