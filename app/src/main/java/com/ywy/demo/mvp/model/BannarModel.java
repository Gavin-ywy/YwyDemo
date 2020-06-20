package com.ywy.demo.mvp.model;

import android.content.Context;

import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.Bannar;
import com.ywy.demo.retrofit_okhttp.HttpHelperMethod;
import com.ywy.demo.retrofit_okhttp.LoadingObserver;
import com.ywy.demo.mvp.listener.GetBannarListener;

import java.util.List;

public class BannarModel {

    private static final String TAG = BannarModel.class.getSimpleName();

    public void getBannar(Context mContext, final GetBannarListener mGetBannarListener) {
        HttpHelperMethod.getBannar(new LoadingObserver<List<Bannar>>(mContext) {
            @Override
            public void onSuccess(List<Bannar> mBannars) {
                BaseLog.logE(TAG, "demo : " + mBannars.toString());
                mGetBannarListener.getSuccess(mBannars);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                BaseLog.logE(TAG, "e : " + e + "errorMsg : " + errorMsg);
                mGetBannarListener.getFail(e, errorMsg);
            }
        });
    }

}
