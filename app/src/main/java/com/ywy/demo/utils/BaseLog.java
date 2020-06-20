package com.ywy.demo.utils;

import android.util.Log;

public class BaseLog {
    private static final String TAG = BaseLog.class.getSimpleName();

    public static void logV(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void logD(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void logI(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void logW(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void logE(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void logV(String msg) {
        Log.v(TAG, msg);
    }

    public static void logD(String msg) {
        Log.d(TAG, msg);
    }

    public static void logI(String msg) {
        Log.i(TAG, msg);
    }

    public static void logW(String msg) {
        Log.w(TAG, msg);
    }

    public static void logE(String msg) {
        Log.e(TAG, msg);
    }

}
