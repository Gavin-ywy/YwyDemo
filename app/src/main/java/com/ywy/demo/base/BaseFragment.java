package com.ywy.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ywy.demo.utils.BaseLog;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    public Boolean isViewInitFinished = false;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BaseLog.logV("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseLog.logV("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseLog.logV("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseLog.logV("onActivityCreated");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onStart() {
        super.onStart();
        BaseLog.logV("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseLog.logV("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        BaseLog.logV("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseLog.logV("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseLog.logV("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BaseLog.logV("onDetach");
    }
}
