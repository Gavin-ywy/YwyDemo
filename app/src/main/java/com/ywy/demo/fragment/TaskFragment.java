package com.ywy.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ywy.greendao.gen.DaoSession;
import com.ywy.greendao.gen.UserBeanDao;
import com.ywy.demo.R;
import com.ywy.demo.base.BaseApplication;
import com.ywy.demo.base.BaseFragment;
import com.ywy.demo.bean.UserBean;

public class TaskFragment extends BaseFragment {

    private View mParentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_task, container, false);

        selectData();

        return mParentView;
    }

    /**
     * 增
     *
     * @param mUser
     */
    private void insertData(UserBean mUser) {
        BaseApplication.getInstance().getDaoSession().getUserBeanDao().insertOrReplace(mUser);
    }

    /**
     * 删
     *
     * @param mUser
     */
    private void deleteData(UserBean mUser) {
        BaseApplication.getInstance().getDaoSession().getUserBeanDao().delete(mUser);
    }

    /**
     * 改
     *
     * @param mUser
     */
    private void updataData(UserBean mUser) {
        BaseApplication.getInstance().getDaoSession().getUserBeanDao().update(mUser);
    }

    /**
     * 查
     */
    private void selectData() {
        DaoSession mDaoSession = BaseApplication.getInstance().getDaoSession();
        if (mDaoSession != null) {
            UserBeanDao mUserBeanDao = mDaoSession.getUserBeanDao();
            if (mUserBeanDao != null) {
                mUserBeanDao.loadAll();
            }
        }
    }

    private void selectData(String key) {
        BaseApplication.getInstance().getDaoSession().getUserBeanDao().load(key);
    }

}
