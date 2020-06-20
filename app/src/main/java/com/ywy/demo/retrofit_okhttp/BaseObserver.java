package com.ywy.demo.retrofit_okhttp;

import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.BaseResponse;

import io.reactivex.Observer;

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onNext(BaseResponse<T> response) {
        //在这边对 基础数据 进行统一处理  举个例子：
        BaseLog.logE(TAG, "onNext: "+response.toString());
        if (response.getErrorCode() == 0) {
            onSuccess(response.getData());
        } else {
            onFailure(null, response.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        BaseLog.logE(TAG, "Throwable: " + e);
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
        BaseLog.logE(TAG, "onComplete: ");
    }

    public abstract void onSuccess(T demo);

    public abstract void onFailure(Throwable e, String errorMsg);

}
