package com.ywy.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ywy.greendao.gen.DaoMaster;
import com.ywy.demo.utils.BaseLog;

public class BaseOpenHelper extends DaoMaster.DevOpenHelper {
    private static final String TAG = BaseOpenHelper.class.getSimpleName();

    public BaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public BaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //super.onUpgrade(db, oldVersion, newVersion);
        BaseLog.logE(TAG, "old : " + oldVersion + " new : " + newVersion);
        /*if (oldVersion < 2) {
            db.execSQL("ALTER TABLE "+DaoManager.TABLE_NAME+" RENAME TO "+UserInfoDataDao.TABLENAME);

            db.execSQL("ALTER TABLE "+UserInfoDataDao.TABLENAME+" ADD COLUMN " +
                    UserInfoDataDao.Properties.TodayFirstExchange.columnName + " INTEGER");
            db.execSQL("ALTER TABLE "+UserInfoDataDao.TABLENAME+" ADD COLUMN " +
                    UserInfoDataDao.Properties.EveryLuckTurn.columnName + " INTEGER");
            db.execSQL("ALTER TABLE "+UserInfoDataDao.TABLENAME+" ADD COLUMN " +
                    UserInfoDataDao.Properties.EveryExchange.columnName + " INTEGER");
            db.execSQL("ALTER TABLE "+UserInfoDataDao.TABLENAME+" ADD COLUMN " +
                    UserInfoDataDao.Properties.Last_lucky_time.columnName + " TEXT");
            db.execSQL("ALTER TABLE "+UserInfoDataDao.TABLENAME+" ADD COLUMN " +
                    UserInfoDataDao.Properties.TodayShareCoin.columnName + " INTEGER");

        }*/
    }
}
