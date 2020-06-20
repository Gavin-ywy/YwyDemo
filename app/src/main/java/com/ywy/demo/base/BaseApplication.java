package com.ywy.demo.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ywy.demo.utils.BaseLog;
import com.ywy.greendao.gen.DaoMaster;
import com.ywy.greendao.gen.DaoSession;
import com.ywy.greendao.gen.UserBeanDao;
import com.ywy.greendao.utils.BaseOpenHelper;

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication mBaseApplication;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        BaseLog.logD("onCreate");
        initGreenDao();
    }

    private void initGreenDao() {
        BaseOpenHelper mHelper = new BaseOpenHelper(getApplicationContext(), UserBeanDao.TABLENAME);
        SQLiteDatabase mSQLiteDatabase = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mSQLiteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }

    public static Context getContext() {
        return mBaseApplication;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;

    }

}
