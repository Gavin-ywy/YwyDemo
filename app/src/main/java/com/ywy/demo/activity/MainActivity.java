package com.ywy.demo.activity;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.ywy.demo.R;
import com.ywy.demo.base.BaseActivity;
import com.ywy.demo.base.BaseApplication;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.fragment.HomeFragment;
import com.ywy.demo.fragment.InviteFragment;
import com.ywy.demo.fragment.TaskFragment;
import com.ywy.demo.fragment.UserFragment;
import com.ywy.demo.utils.GetLocation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String[] permission = {Manifest.permission_group.STORAGE};
    private static final int HOME = 0;
    private static final int INVITE = 1;
    private static final int TASK = 2;
    private static final int USER = 3;

    private ViewPager mViewPager;
    private LinearLayout mHomeLinear, mInviteLinear, mTaskLinear, mUserLinear;
    private TextView mHomeText, mInviteText, mTaskText, mUserText;
    private ImageView mHomeImage, mInviteImage, mTaskImage, mUserImage;

    /*private FragmentTabHost mFragmentTabHost;
    private Class fragmentArray[] = {HomeFragment.class, InviteFragment.class,
            TaskFragment.class, UserFragment.class};
    private int imageViewArray[] = {R.drawable.main_tab_home_selector, R.drawable.main_tab_invite_selector,
            R.drawable.main_tab_task_selector, R.drawable.main_tab_user_selector};
    private String textViewArray[] = {"首页", "邀请", "任务", "我的"};*/


    private ViewPager.OnPageChangeListener mOnPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            BaseLog.logD("onPageScrolled   position : " + position + "  positionOffset : " +
                    positionOffset + "  positionOffsetPixels : " + positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            BaseLog.logD("onPageSelected  position : " + position);
            setTabState(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            BaseLog.logD("onPageScrollStateChanged  state : " + state);
        }
    };

    private TabHost.OnTabChangeListener mTabChangeListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            BaseLog.logD(tabId);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        initView();

    }

    private void initView() {
        initTab();
        initViewPager();
    }

    private void initTab() {

        mHomeLinear = findViewById(R.id.home);
        mHomeLinear.setOnClickListener(this);
        mHomeText = findViewById(R.id.home_txt);
        mHomeImage = findViewById(R.id.home_img);

        mInviteLinear = findViewById(R.id.invite);
        mInviteLinear.setOnClickListener(this);
        mInviteText = findViewById(R.id.invite_txt);
        mInviteImage = findViewById(R.id.invite_img);


        mTaskLinear = findViewById(R.id.task);
        mTaskLinear.setOnClickListener(this);
        mTaskText = findViewById(R.id.task_txt);
        mTaskImage = findViewById(R.id.task_img);


        mUserLinear = findViewById(R.id.user);
        mUserLinear.setOnClickListener(this);
        mUserText = findViewById(R.id.user_txt);
        mUserImage = findViewById(R.id.user_img);

    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(0);
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new InviteFragment());
        mFragments.add(new TaskFragment());
        mFragments.add(new UserFragment());

        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.addOnPageChangeListener(mOnPagerChangerListener);
        mViewPager.setAdapter(mPagerAdapter);

        setTabState(HOME);
        /*mFragmentTabHost = findViewById(R.id.fragment_tab_host);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.viewPager);
        mFragmentTabHost.setOnTabChangedListener(mTabChangeListener);*/

        /*int count = textViewArray.length;
        for(int i = 0;i<count;i++){
            TabHost.TabSpec mTabSpec = mFragmentTabHost.newTabSpec(textViewArray[i]).
                    setIndicator();
        }*/
    }

    private void setTabState(int position) {
        switch (position) {
            case INVITE:
                mHomeText.setSelected(false);
                mHomeImage.setSelected(false);
                mInviteImage.setSelected(true);
                mInviteText.setSelected(true);
                mTaskText.setSelected(false);
                mTaskImage.setSelected(false);
                mUserText.setSelected(false);
                mUserImage.setSelected(false);
                mViewPager.setCurrentItem(INVITE);
                break;
            case TASK:
                mHomeText.setSelected(false);
                mHomeImage.setSelected(false);
                mInviteImage.setSelected(false);
                mInviteText.setSelected(false);
                mTaskText.setSelected(true);
                mTaskImage.setSelected(true);
                mUserText.setSelected(false);
                mUserImage.setSelected(false);
                mViewPager.setCurrentItem(TASK);
                break;
            case USER:
                mHomeText.setSelected(false);
                mHomeImage.setSelected(false);
                mInviteImage.setSelected(false);
                mInviteText.setSelected(false);
                mTaskText.setSelected(false);
                mTaskImage.setSelected(false);
                mUserText.setSelected(true);
                mUserImage.setSelected(true);
                mViewPager.setCurrentItem(USER);
                break;
            case HOME:
            default:
                mHomeText.setSelected(true);
                mHomeImage.setSelected(true);
                mInviteImage.setSelected(false);
                mInviteText.setSelected(false);
                mTaskText.setSelected(false);
                mTaskImage.setSelected(false);
                mUserText.setSelected(false);
                mUserImage.setSelected(false);
                mViewPager.setCurrentItem(HOME);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                setTabState(HOME);
                break;
            case R.id.invite:
                setTabState(INVITE);
                break;
            case R.id.task:
                setTabState(TASK);
                break;
            case R.id.user:
                setTabState(USER);
                break;
            default:
                break;
        }
    }

    static class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public PagerAdapter(FragmentManager fm, List<Fragment> mFragment) {
            super(fm);
            this.fragments = mFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, 100);
            }
            return;
        } else {
            initLocation();
        }
    }

    private void initLocation() {
        final GetLocation mMyLocationOff = GetLocation.getInstance();
        final Location locationOff = mMyLocationOff.getLocation(BaseApplication.getContext());
        if (locationOff != null) {
            mMyLocationOff.setGetLocationListener(new GetLocation.GetLocationListener() {
                @Override
                public void getLocationData(Location location) {
                    Log.d(TAG,"location : " + location.toString());
                    mMyLocationOff.setGetLocationListener(null);
                }
            });
        } else {
            Log.e(TAG, "locationOff is null");
        }
    }

    /*private View getTabItemView(int i) {
        //将xml布局转换为view对象
        View view = LayoutInflater.from(this).inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }*/

}
