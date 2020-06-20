package com.ywy.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ywy.demo.R;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.bean.Bannar;

import java.util.ArrayList;
import java.util.List;

public class BannarAdapter extends RecyclerView.Adapter {
    private static final String TAG = "Invite";
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private List<Bannar> mBeanList = new ArrayList<>();

    public BannarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BannarAdapter(Context mContext, List<Bannar> mBeanList) {
        this.mContext = mContext;
        this.mBeanList = mBeanList;
    }

    public void setData(List<Bannar> mBeanList) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        BaseLog.logE(TAG, "onBindViewHolder position : " + mBeanList.size());
        InviteViewHodler mInviteHolder = (InviteViewHodler) holder;
        mInviteHolder.itemTextView.setText("Desc: " + mBeanList.get(position).getDesc()
                + " : Title: " + mBeanList.get(position).getTitle());
        mInviteHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position, "Desc: " + mBeanList.get(position).getDesc()
                            + " : Title: " + mBeanList.get(position).getTitle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        BaseLog.logE(TAG, "size : " + mBeanList.size());
        return mBeanList.size();
    }

    class InviteViewHodler extends RecyclerView.ViewHolder {
        RelativeLayout item;
        TextView itemTextView;

        public InviteViewHodler(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            itemTextView = itemView.findViewById(R.id.item_text);

        }
    }

    public void setItemOnClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        public abstract void onItemClick(int position, String msg);
    }

}
