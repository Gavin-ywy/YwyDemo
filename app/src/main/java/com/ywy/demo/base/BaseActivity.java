package com.ywy.demo.base;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ywy.demo.utils.BaseLog;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);
        BaseLog.logV("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BaseLog.logV("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseLog.logV("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseLog.logV("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        BaseLog.logV("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BaseLog.logV("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseLog.logV("onDestroy");
    }

    private void setFullScreen(){
        //无title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
