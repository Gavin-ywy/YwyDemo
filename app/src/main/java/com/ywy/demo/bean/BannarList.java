package com.ywy.demo.bean;

import java.util.List;

public class BannarList {

    private List<Bannar> mBannarList;

    public List<Bannar> getmBannarList() {
        return mBannarList;
    }

    public void setmBannarList(List<Bannar> mBannarList) {
        this.mBannarList = mBannarList;
    }

    @Override
    public String toString() {
        return "BannarList{" +
                "mBannarList=" + mBannarList +
                '}';
    }
}
