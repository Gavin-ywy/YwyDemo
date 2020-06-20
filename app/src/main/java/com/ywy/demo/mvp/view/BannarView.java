package com.ywy.demo.mvp.view;

import com.ywy.demo.bean.Bannar;

import java.util.List;

public interface BannarView {

    public abstract void showPrograss();
    public abstract void hidePrograss();
    public abstract void getBannarSuccess(List<Bannar> mBannars);
    public abstract void getBannarFail(Throwable e, String errorMsg);
}
