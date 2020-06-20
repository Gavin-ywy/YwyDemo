package com.ywy.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ywy.demo.R;
import com.ywy.demo.language.Clock;

import java.util.Locale;

public class LanguageSwitchActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout mSwitchParent;
    private Clock mClock;
    private TextView mLanguageText;
    private Button mSwitchBtn;
    private Locale ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_switch);
        ll = getResources().getConfiguration().locale;
        initParent();
        initSwitch();
        initView();

    }

    private void initParent() {
        mSwitchParent = findViewById(R.id.switch_parent);
    }

    private void initSwitch() {
        mSwitchBtn = findViewById(R.id.switch_language);
        mSwitchBtn.setOnClickListener(this);
    }

    private void initView() {
        mClock = findViewById(R.id.language_clock);
        mLanguageText = findViewById(R.id.language_text);
    }


    private void setLanguage(Locale ll) {
        //获取res对象
        Resources resources = getResources();
        //获得设置对象
        Configuration config = resources.getConfiguration();
        //获取屏幕参数 主要是分辨率,像素等
        DisplayMetrics dm = resources.getDisplayMetrics();
        //语言
        config.locale = ll;
        resources.updateConfiguration(config, dm);
        Toast.makeText(LanguageSwitchActivity.this,
                "setLanguage : " + getResources().getConfiguration().locale, Toast.LENGTH_SHORT).show();
        mLanguageText.setText(getResources().getString(R.string.language_switch));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_language:
                Toast.makeText(LanguageSwitchActivity.this,
                        "Language", Toast.LENGTH_SHORT).show();
                if (ll.equals(Locale.CHINESE)) {
                    setLanguage(Locale.ENGLISH);
                } else {
                    setLanguage(Locale.CHINESE);
                }
                break;

            default:
                break;
        }
    }
}