package com.ywy.demo.wechat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ywy.demo.base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class WXUtils {

    private static final String APP_ID = "";
    private static final int THUMB_SIZE = 150;
    /**
     * 好友
     */
    public static int WXSCENESESSION = SendMessageToWX.Req.WXSceneSession;
    /**
     * 朋友圈
     */
    public static int WXSCENETIMELINE = SendMessageToWX.Req.WXSceneTimeline;

    public static String getAppId() {
        return APP_ID;
    }

    private WXUtils() {
    }

    private static class IWXAPIHolder {
        private static final IWXAPI mIWXAPI =
                WXAPIFactory.createWXAPI(BaseApplication.getContext(), APP_ID, true);
    }

    public static IWXAPI getInstance() {
        return IWXAPIHolder.mIWXAPI;
    }

    /**
     * 微信登陆
     */
    public static void requestWeChatLogin() {
        IWXAPI mIWXAPI = getInstance();
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "iwalk_wx_login";
        mIWXAPI.sendReq(req);
    }

    /**
     * 判断是否安装微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 微信分享图片到好友和朋友圈
     *
     * @param bitmap    图片
     * @param shareType 类型
     */
    //sharePicture(changeColor(getCacheBitmapFromView(mSharedShotLinear)), WXSCENESESSION);
    private void sharePicture(Bitmap bitmap, int shareType) {
        WXImageObject imgObj = new WXImageObject(bitmap);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
        bitmap.recycle();
        //设置缩略图
        msg.thumbData = bmpToByteArray(thumbBitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("imgshare");
        req.message = msg;
        req.scene = shareType;
        getInstance().sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * bitmap中的透明色用白色替换
     *
     * @param bitmap
     * @return
     */
    public static Bitmap changeColor(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] colorArray = new int[w * h];
        int n = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int color = getMixtureWhite(bitmap.getPixel(j, i));
                colorArray[n++] = color;
            }
        }
        return Bitmap.createBitmap(colorArray, w, h, Bitmap.Config.ARGB_8888);
    }

    /**
     * 获取和白色混合颜色
     *
     * @return
     */
    private static int getMixtureWhite(int color) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.rgb(getSingleMixtureWhite(red, alpha), getSingleMixtureWhite

                        (green, alpha),
                getSingleMixtureWhite(blue, alpha));
    }

    /**
     * 获取单色的混合值
     *
     * @param color
     * @param alpha
     * @return
     */
    private static int getSingleMixtureWhite(int color, int alpha) {
        int newColor = color * alpha / 255 + 255 - alpha;
        return newColor > 255 ? 255 : newColor;
    }

    /**
     * 截取屏幕View
     *
     * @param view
     * @return
     */
    public static Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        //saveBitmap(bitmap);
        return bitmap;
    }

    private byte[] bmpToByteArray(Bitmap thumbBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        return datas;
    }

}
