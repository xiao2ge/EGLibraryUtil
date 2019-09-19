package com.x2g.qq.library.util;

import android.preference.PreferenceManager;


public class EGConfigUtils {

    public static void saveString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().putString(key, value).apply();
    }

    public static void saveBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().putBoolean(key, value).apply();
    }

    public static void saveFloat(String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().putFloat(key, value).apply();
    }

    public static void saveInt(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().putInt(key, value).apply();
    }

    public static void saveLong(String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().putLong(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .getString(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .getInt(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .getLong(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .getFloat(key, defaultValue);
    }

    public static void remove(String key) {
        PreferenceManager.getDefaultSharedPreferences(EGUtilManager.getApplication())
                .edit().remove(key).apply();
    }

}
