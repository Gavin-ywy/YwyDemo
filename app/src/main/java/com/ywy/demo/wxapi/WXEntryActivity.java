package com.ywy.demo.wxapi;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.ywy.demo.R;
import com.ywy.demo.base.BaseActivity;
import com.ywy.demo.utils.BaseLog;
import com.ywy.demo.utils.ToastUtils;
import com.ywy.demo.wechat.WXUtils;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static String TAG = WXEntryActivity.class.getSimpleName();

    private IWXAPI api;
    /*登录*/
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    /*分享*/
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXUtils.getInstance();
        api.registerApp(WXUtils.getAppId());

        try {
            Boolean isRegister = api.handleIntent(getIntent(), this);
            BaseLog.logE(TAG, "isRegister : " + isRegister);
            if (!isRegister) {
                finish();
            }
        } catch (Exception exception) {
            BaseLog.logE(TAG, "exception : " + exception.getMessage());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX://好友
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX://朋友圈
                break;
            default:
                break;
        }
        BaseLog.logE(TAG, "onReq");
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        String result;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                BaseLog.logE(TAG, "onResp OK type : " + resp.getType());
                result = getResources().getString(R.string.code_success);
                ///< 用户换取access_token的code，仅在ErrCode为0时有效
                if (resp.getType() == RETURN_MSG_TYPE_LOGIN) {
                    ///< 这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
                    //getAccessToken(code);
                    BaseLog.logE(TAG, "onResp OK type Login ");
                    String code = ((SendAuth.Resp) resp).code;
                    BaseLog.logE(TAG, "wx code: " + code);
                } else if (resp.getType() == RETURN_MSG_TYPE_SHARE) {
                    ///< "微信分享成功"
                    BaseLog.logE(TAG, "onResp OK type shared");
                    ToastUtils.makeText(WXEntryActivity.this, "分享成功");
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = getResources().getString(R.string.code_cancel);
                if (resp.getType() == RETURN_MSG_TYPE_LOGIN) {
                    result = getResources().getString(R.string.code_login_cancel);
                } else if (resp.getType() == RETURN_MSG_TYPE_SHARE) {
                    result = getResources().getString(R.string.code_shared_cancel);
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = getResources().getString(R.string.code_deny);
                finish();
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = getResources().getString(R.string.code_unsupported);
                finish();
                break;
            default:
                result = getResources().getString(R.string.code_unknown);
                finish();
                break;
        }
        BaseLog.logE(TAG, result + ", type=" + resp.getType() + "   "
                + resp.errStr + "   " + resp.openId + "   " + resp.errCode + "   " + resp.transaction);
    }

    /**
     * 获取剪贴板的内容
     * ##!iwalk=563313!##
     */
    private String getClipContent() {
        String promote = "";
        ClipboardManager cm = (ClipboardManager) WXEntryActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        if (data != null && data.getItemAt(0) != null) {
            ClipData.Item item = data.getItemAt(0);
            String content = item.getText().toString();
            BaseLog.logE(TAG, "content : " + content);
        } else {
            BaseLog.logE(TAG, "null");
        }
        return promote;
    }
}
