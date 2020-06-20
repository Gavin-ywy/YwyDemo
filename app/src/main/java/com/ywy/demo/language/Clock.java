/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ywy.demo.language;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Digital clock for the status bar.
 */
public class Clock extends androidx.appcompat.widget.AppCompatTextView implements Observer {

    private static final String LOGTAG = "Clock";

    private ClockObservable mClockObservable;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Clock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mClockObservable = ClockObservable.getInstance(context.getApplicationContext());
        mClockObservable.addObserver(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateClock(mClockObservable.getTime());
    }

    public void destroy() {
        mClockObservable.deleteObserver(this);
    }

    public void updateClock(String time) {
        Log.d(LOGTAG, "updateClock time = " + System.currentTimeMillis() + " : " + time);
        setText(time);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            return;
        }
        String time = (String) arg;
        updateClock(time);
    }
}

