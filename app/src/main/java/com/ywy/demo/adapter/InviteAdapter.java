package com.ywy.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ywy.demo.R;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.YwyBean;

import java.util.List;

public class InviteAdapter extends RecyclerView.Adapter {
    private static final String TAG = "Invite";
    private Context mContext;
    private List<YwyBean> mBeanList;

    public InviteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public InviteAdapter(Context mContext, List<YwyBean> mBeanList) {
        this.mContext = mContext;
        this.mBeanList = mBeanList;
    }

    public void setData(List<YwyBean> mBeanList){
        this.mBeanList = mBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = LayoutInflater.from(mContext).
                inflate(R.layout.item_layout, parent, false);

        return new InviteViewHodler(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseLog.logE(TAG,"onBindViewHolder position : "+mBeanList.size());
        InviteViewHodler mInviteHolder = (InviteViewHodler) holder;
        mInviteHolder.itemTextView.setText(mBeanList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        BaseLog.logE(TAG,"size : "+mBeanList.size());
        return mBeanList.size();
    }

    class InviteViewHodler extends RecyclerView.ViewHolder {

        TextView itemTextView;

        public InviteViewHodler(@NonNull View itemView) {
            super(itemView);

            itemTextView = itemView.findViewById(R.id.item_text);

        }
    }


}
