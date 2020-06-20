package com.ywy.demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ywy.demo.R;
import com.ywy.demo.adapter.InviteAdapter;
import com.ywy.demo.base.BaseFragment;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.YwyBean;

import java.util.ArrayList;
import java.util.List;

public class InviteFragment extends BaseFragment {
    private static final String TAG = "Invite";
    private View mParentView;
    private XRecyclerView mRecyclerView;
    private List<YwyBean> mYwyBeanList = new ArrayList<>();
    private InviteAdapter mInviteAdapter;
    private int mPageNum = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_invite, container, false);
        getData();
        initView();

        return mParentView;
    }

    private void initView() {

        mRecyclerView = mParentView.findViewById(R.id.invite_recycler);

        LinearLayoutManager mLinearManager = new LinearLayoutManager(getContext());
        mLinearManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearManager);

        mInviteAdapter = new InviteAdapter(getActivity(), mYwyBeanList);
        mRecyclerView.setAdapter(mInviteAdapter);

        //设置可下拉刷新
        mRecyclerView.setPullRefreshEnabled(true);
        //设置上拉加载
        mRecyclerView.setLoadingMoreEnabled(true);
        //设置上拉样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        //设置下拉样式
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mRecyclerView.setRefreshHeader(new YArrowRefreshHeader(getContext()));

        mRecyclerView.setArrowImageView(R.drawable.loading_05);

        //设置刷新是否显示事件
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        //设置刷新图片
        //mRecyclerView.setArrowImageView(R.drawable.loading_05);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mYwyBeanList.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        mRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPageNum < 2) {
                            loadData();
                        } else {
                            mRecyclerView.setFootViewText("亲爱的，我也是有底线的！！","拜拜喽，您嘞");
                            mRecyclerView.loadMoreComplete();
                        }
                    }
                }, 500);
            }
        });

    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            mYwyBeanList.add(new YwyBean("Ywy : " + i, " " + i));
            BaseLog.logE(TAG, "getData i : " + i);
        }
    }

    private void loadData() {
        mPageNum++;
        BaseLog.logE(TAG, "pageNum : " + mPageNum);
        List<YwyBean> mLoadList = new ArrayList<>();
        for (int i = (mPageNum - 1) * 10; i < mPageNum * 10; i++) {
            mLoadList.add(new YwyBean("Ywy : " + i, " " + i));
            BaseLog.logE(TAG, "loadData i : " + i);
        }
        mYwyBeanList.addAll(mLoadList);
        mInviteAdapter.notifyDataSetChanged();
        mRecyclerView.loadMoreComplete();
    }

}
