package com.ywy.demo.mvp.presenter;

import android.content.Context;

import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.Bannar;
import com.ywy.demo.mvp.listener.GetBannarListener;
import com.ywy.demo.mvp.model.BannarModel;
import com.ywy.demo.mvp.view.BannarView;

import java.util.List;

public class BannarPresenter {
    private static final String TAG = BannarPresenter.class.getSimpleName();
    private BannarModel mBannarModel;
    private BannarView mBannarView;

    public BannarPresenter(BannarView mBannarView) {
        this.mBannarView = mBannarView;
        mBannarModel = new BannarModel();
    }

    public void getBannar(Context mContext) {
        mBannarView.showPrograss();
        mBannarModel.getBannar(mContext, new GetBannarListener() {
            @Override
            public void getSuccess(List<Bannar> mBannars) {
                BaseLog.logE(TAG, "Bannars : " + mBannars.toString());
                mBannarView.getBannarSuccess(mBannars);
            }

            @Override
            public void getFail(Throwable e, String errorMsg) {
                BaseLog.logE(TAG, "Throwable : " + e + " Msg : " + errorMsg);
                mBannarView.getBannarFail(e, errorMsg);
            }
        });
    }

}
