package com.ywy.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ywy.demo.R;
import com.ywy.demo.activity.LanguageSwitchActivity;
import com.ywy.demo.mvp.MvpActivity;
import com.ywy.demo.activity.TextureViewActivity;
import com.ywy.demo.base.BaseFragment;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private View mParentView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mSwipeLinear;
    private Button mTextureView;
    private Button mTextMvp;
    private Button mSwitchLanguage;
    //private XRecyclerView recycler_view

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        return mParentView;
    }

    private void initView() {
        mSwipeRefreshLayout = mParentView.findViewById(R.id.swipe_refresh_view);
        mSwipeLinear = mParentView.findViewById(R.id.swipe_linear);

        //刷新的view中的颜色
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_color2));
        //mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_color1);

        //刷新View的背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                getResources().getColor(R.color.refresh_color1));

        //刷新View滑动的是否设置放大缩小动画和最大距离
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 150);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        mTextureView = mParentView.findViewById(R.id.home_textureView);
        mTextureView.setOnClickListener(this);
        mTextMvp = mParentView.findViewById(R.id.home_mvp);
        mTextMvp.setOnClickListener(this);
        mSwitchLanguage = mParentView.findViewById(R.id.home_switch_language);
        mSwitchLanguage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_textureView:
                startActivity(new Intent(getActivity(), TextureViewActivity.class));
                break;
            case R.id.home_mvp:
                startActivity(new Intent(getActivity(), MvpActivity.class));
                break;
            case R.id.home_switch_language:
                startActivity(new Intent(getActivity(), LanguageSwitchActivity.class));
                break;
            default:
                break;
        }
    }
}
