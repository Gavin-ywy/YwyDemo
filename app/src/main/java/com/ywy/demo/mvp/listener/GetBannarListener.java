package com.ywy.demo.mvp.listener;

import com.ywy.demo.bean.Bannar;

import java.util.List;

public interface GetBannarListener {
    public abstract void getSuccess(List<Bannar> mBannars);

    public abstract void getFail(Throwable e, String errorMsg);
}
