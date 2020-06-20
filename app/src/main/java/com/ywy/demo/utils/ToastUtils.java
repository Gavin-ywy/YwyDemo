package com.ywy.demo.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    public static void makeText(Context mContext, String content) {
        Toast mToast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

}
