package com.ywy.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.ywy.demo.R;
import com.ywy.demo.utils.BaseLog;

public class YArrowRefreshHeader extends ArrowRefreshHeader {

    private static final String TAG = YArrowRefreshHeader.class.getSimpleName();
    private String mRefreshText[] = {"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡", "愿得一人心，白首不分离",
            "会当凌绝顶，一览众山小"};
    private Context mContext;

    public YArrowRefreshHeader(Context context) {
        super(context);
        this.mContext = context;
        BaseLog.logE(TAG, "1");
        initView();
    }

    public YArrowRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        BaseLog.logE(TAG, "2");
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.refresh_layout, null, false);
        TextView refreshText = view.findViewById(R.id.refresh_text);

        int index = (int) (0 + Math.random() * (mRefreshText.length - 0 + 1));
        BaseLog.logE(TAG, "index : " + index + "  text : " + mRefreshText[index]);
        refreshText.setText(mRefreshText[index]);

    }


}
