package com.ywy.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ywy.demo.R;
import com.ywy.demo.base.BaseFragment;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.Bannar;
import com.ywy.demo.bean.ListData;
import com.ywy.demo.retrofit_okhttp.HttpHelperMethod;
import com.ywy.demo.retrofit_okhttp.LoadingObserver;

import java.util.List;

public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();
    private View mParentView;
    private TextView mUserText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mParentView = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        //getBannar();
        getListData();
        return mParentView;
    }

    private void initView() {

        mUserText = mParentView.findViewById(R.id.user_text);

    }

    private void getBannar() {
        HttpHelperMethod.getBannar(new LoadingObserver<List<Bannar>>(getContext()) {
            @Override
            public void onSuccess(List<Bannar> mBannars) {
                BaseLog.logE(TAG, "demo : " + mBannars.toString());
                mUserText.setText(mBannars.toString());
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                BaseLog.logE(TAG, "e : " + e + "errorMsg : " + errorMsg);
            }
        });
    }

    private void getListData() {
        HttpHelperMethod.getListData("294", new LoadingObserver<ListData>(getContext()) {
            @Override
            public void onSuccess(ListData mListData) {
                BaseLog.logE(TAG, "demo : " + mListData.toString());
                mUserText.setText(mListData.toString());
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                BaseLog.logE(TAG, "e : " + e + "errorMsg : " + errorMsg);
            }
        });
    }

}
