package com.ywy.demo.observable;

import android.util.Log;

import com.ywy.demo.fragment.HomeFragment;

import java.util.Observable;

public class YwyObservable extends Observable {
    private static final String TAG = YwyObservable.class.getSimpleName();
    private static YwyObservable mTestObservable = null;

    private YwyObservable() {
    }

    public static YwyObservable getInstance(){
        return YwyObservable.mTestObservable;
    }

    private static class TestGetObservable{
        private static final YwyObservable mTestObservable = new YwyObservable();
    }

    public void notifiTip(){
        setChanged();
        notifyObservers("ccccccc");
        Log.e(TAG,"click testobservable");
    }

}
