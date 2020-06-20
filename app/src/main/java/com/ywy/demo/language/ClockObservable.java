package com.ywy.demo.language;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

import com.ywy.demo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.TimeZone;

public class ClockObservable extends Observable {

    private final static String TAG = "ClockObservable";
    private static ClockObservable sClockObservable;
    private Context mContext;
    private Calendar mCalendar;
    private String mTimeStr = "";
    private volatile boolean mIs24Hour;

    private final static int MSG_GET_CLOCK = 101;
    private final static int MSG_UPDATE_CLOCK = 102;
    private UIHandler mUIHandler = new UIHandler() {
        @Override
        public void handleMessage(Message m) {
            switch (m.what) {
                case MSG_UPDATE_CLOCK:
                    mTimeStr = (String) m.obj;
                    notifyStatus();
                    break;
            }
        }
    };


    private ClockObservable(Context context){
        this.mContext = context;
        mCalendar = Calendar.getInstance(TimeZone.getDefault());
        mIs24Hour = is24Hour();
        registerReceiver();
    }

    public static ClockObservable getInstance(Context context){
        if(sClockObservable == null) {
            synchronized (ClockObservable.class){
                if(sClockObservable == null){
                    sClockObservable = new ClockObservable(context);
                }
            }
        }
        return sClockObservable;
    }

    public String getTime() {
        return mTimeStr;
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.mContext.registerReceiver(mIntentReceiver, filter, 0);
        }
    }

    private boolean is24Hour() {
        String timeType = Settings.System.getString(mContext.getContentResolver(), Settings.System.TIME_12_24);
        return "24".equals(timeType);
    }

    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive " + intent.getAction());
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
                String tz = intent.getStringExtra("time-zone");
               mCalendar = Calendar.getInstance(TimeZone.getTimeZone(tz));
            } else if (Intent.ACTION_TIME_CHANGED.equals(action)) {
                mIs24Hour = is24Hour();
            }
            mCalendar.setTimeInMillis(System.currentTimeMillis());
            String time = getSmallTime();
            if (time == null || time.equals(mTimeStr)) {
                return;
            }
            Message.obtain(mUIHandler, MSG_UPDATE_CLOCK, time).sendToTarget();
        }
    };


    public void notifyStatus() {
        setChanged();
        notifyObservers(mTimeStr);
    }

    @SuppressLint({"SimpleDateFormat", "WrongConstant"})
    private final String getSmallTime() {
        SimpleDateFormat sdf = mIs24Hour ? new SimpleDateFormat("HH:mm", Locale.getDefault()) :
                new SimpleDateFormat("hh:mm", Locale.getDefault());

        Date d = mCalendar.getTime();
        String result = sdf.format(d);

        String ampmValues = "";
        int hour = d.getHours();

        Log.d(TAG, "updateClock d.getTime() = " + d.getTime() + "; d.toString = " + d.toString()
                +"; result = " + result + "; mCalendar.get(Calendar.AM_PM) = " + mCalendar.get(Calendar.AM_PM) + "; hour =" + hour);

        if (hour < 12) {
            ampmValues = mContext.getString(R.string.am_time);
        } else {
            ampmValues = mContext.getString(R.string.pm_time);
        }

        if (!mIs24Hour) {
            result = ampmValues + " " + result;
        }

        Log.d(TAG,"updateClock is24 = " + mIs24Hour + "; result = " + result);

        return result;
    }
}
