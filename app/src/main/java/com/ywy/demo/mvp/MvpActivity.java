package com.ywy.demo.mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ywy.demo.R;
import com.ywy.demo.adapter.BannarAdapter;
import com.ywy.demo.base.BaseActivity;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.Bannar;
import com.ywy.demo.mvp.presenter.BannarPresenter;
import com.ywy.demo.mvp.view.BannarView;
import com.ywy.demo.view.YArrowRefreshHeader;

import java.util.List;

public class MvpActivity extends BaseActivity implements BannarView {

    private static final String TAG = MvpActivity.class.getSimpleName();
    private XRecyclerView mXRecyclerView;
    private BannarAdapter mBannarAdapter;
    private BannarPresenter mBannarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mBannarPresenter = new BannarPresenter(this);
        initView();

    }

    private void initView() {
        mXRecyclerView = findViewById(R.id.mvp_recycler);
        LinearLayoutManager mLinearManager = new LinearLayoutManager(this);
        mLinearManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(mLinearManager);
        //自定义刷新头部View
        mXRecyclerView.setRefreshHeader(new YArrowRefreshHeader(MvpActivity.this));

        mBannarAdapter = new BannarAdapter(this);
        mXRecyclerView.setAdapter(mBannarAdapter);

        //设置可下拉刷新
        mXRecyclerView.setPullRefreshEnabled(true);
        //设置上拉加载
        mXRecyclerView.setLoadingMoreEnabled(true);
        //设置上拉样式
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        //设置下拉样式
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //设置刷新是否显示事件
        mXRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        //设置刷新图片
        //mXRecyclerView.setArrowImageView(android.R.drawable.ic_media_ff);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mBannarPresenter.getBannar(MvpActivity.this);
            }

            @Override
            public void onLoadMore() {

            }
        });
        mBannarAdapter.setItemOnClickListener(new BannarAdapter.ItemClickListener() {

            @Override
            public void onItemClick(int position, String msg) {
                Toast.makeText(MvpActivity.this, position + "--" + msg, Toast.LENGTH_SHORT).show();
            }
        });
        mBannarPresenter.getBannar(this);
    }

    @Override
    public void showPrograss() {

    }

    @Override
    public void hidePrograss() {

    }

    @Override
    public void getBannarSuccess(List<Bannar> mBannars) {
        mXRecyclerView.refreshComplete();
        BaseLog.logE(TAG, "List Bannar : " + mBannars.toString());
        mBannarAdapter.setData(mBannars);
    }

    @Override
    public void getBannarFail(Throwable e, String errorMsg) {
        BaseLog.logE("e : " + e + "  Msg : " + errorMsg);
        Toast.makeText(this, "e : " + e + "  Msg : " + errorMsg, Toast.LENGTH_SHORT).show();
    }

}
