package com.ywy.demo.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.ywy.demo.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, back;
    private String mChannelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        back = findViewById(R.id.notification_back);
        btn1 = findViewById(R.id.notification_1);
        btn2 = findViewById(R.id.notification_2);
        btn3 = findViewById(R.id.notification_3);
        back.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification_back:
                finish();
                break;
            case R.id.notification_1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    initNotification();
                }
                break;
            case R.id.notification_2:
                bundleNotification();
                break;
            case R.id.notification_3:
                customNotification();
                break;
            default:
                break;
        }
    }

    private void setChannelID(String channelID) {
        mChannelId = channelID;
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(mChannelId, mChannelId, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(mChannel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotification() {
        setChannelID("123456");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationActivity.this, mChannelId);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("通知")
                .setContentText("这是我自己的通知")
                .setAutoCancel(true);

        Intent intent = new Intent();
        intent.setClass(NotificationActivity.this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
        mBuilder.setContentIntent(mPendingIntent);
        Notification notification = mBuilder.build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void bundleNotification() {
        setChannelID("456789");
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, mChannelId)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        Notification mNotification = builder.build();
        Intent intent = new Intent("android.intent.action.userguideactivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotification.contentIntent = pendingIntent;
        Bundle bundle = new Bundle();
        bundle.putString(Notification.EXTRA_TITLE, "Bundle Notification");
        bundle.putString(Notification.EXTRA_TEXT, "Bundle Notification Text");
        mNotification.extras = bundle;
        notifyManager.notify((int) System.currentTimeMillis(), mNotification);
    }


    @SuppressLint("RestrictedApi")
    private void customNotification() {
        setChannelID("7890");
        //自定义的布局视图
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification_layout);
        //按钮点击事件：
        PendingIntent homeIntent = PendingIntent.getActivity(
                this, 1,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //点击的id，点击事件
        //views.setOnClickPendingIntent(R.id.layout_notification, homeIntent);
        //创建通知：
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this, mChannelId);
        //设置布局
        mBuilder.setContent(views)
                //.setOngoing(true)//设置是否常驻,true为常驻
                .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                .setTicker("通知来了")//设置提示
                //.setPriority(Notification.PRIORITY_MAX)//设置优先级
                .setShowWhen(true)
                //.setWhen(System.currentTimeMillis())//设置展示时间
                .setContentIntent(homeIntent)
                .setAutoCancel(true);//设置视图点击事件
        views.setLong(R.id.data_time, "setTime", mBuilder.getWhenIfShowing());

        NotificationManager mNotificationManager
                = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //显示通知
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }


}