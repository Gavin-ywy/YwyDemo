package com.ywy.demo.alipay;

public class Alipay {

    private static final String APP_ID = "2021001158621020";
    private static final String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCrnXWuGnU1Y4hGsPvHav2nRPBXjxDI5vjs2GoQc4Ux1QlEKiH+wpxaypfac3pGRBT9MaLFoyHWovQyV4G/51+i8hF/iEvy2vxK7mg/JEJ6veDDcA2gBbWtkvivBbmqDRCy5zX9N3Pp0iSpAEYmGGmsxiv5nIBwerlG1GJ9dMzfN+pnG7KBZZqKAYrjsqyGKLGyNb+TBIRJ+uBMFngAr8+S+BV4WPGnHeZ1MVuVWQiVahL2xkTwqCxOABbtmqz64nkOjVC/bYGuVOrSPkWKl0k2nWufk2wF8egWLosNEEd1WKI5ZF2h9J2jt/0ELm/xwqRoG6OhLlx1GxqDbJuH7wjLAgMBAAECggEAYUXn/R5cDz3ycXdBzz/StKv7uvWJowZgVAc630ZLWfqRvQRQrpFqhH/O4PCRfyB58SXgSDv2Iy3MUniNP9QKvlaNGoeL3QTI+kP17MxFrkLQEpcB8hEqcE8dDD+nhD7Rl0Y8sOFsnz0QsIZKPK+bvmW5aUuO9bJqMD6T8N5yfZbJVL6atowDqHUHN9URblRnb1+9u/PckAGeiVjP2T8hn5Q/SoPqd6zNoXie9OBhjJIcLUdpqeh9Ag4dykOMInwUlx8dTa5WwWya5epJVk0rO/a8WCVV7UqOl9jnIt8IlmyYkWh0l9f9kLpk4r4hk+b4OLUrHtxLFUadJ/nJLICG8QKBgQDvnaYLTTlVHUEfuUwkFBTQ2nIvy/gPsMjnpXObEWenAMt2BxUHr+Nutd2aoBoKZZ4LJBRJFjSliAdU6AdGPNzWGMr130LJKnRPZvlqDs+GopDKwI/O990zC4H9pmaaHm1Pgk1GBk9l9c8ba154d8yTVjwIOVOml9a1kQqzuZ4TqQKBgQC3WX5B7Lksl2KGDOxmOfQ/bIC2hFkl2YbGW1DvYETPYfgIzUshJivzuy09qfJgclSL+rlg+kEyuGpKSRqI50X2MA6H9+kjS1Yx+ZIzCVX/bDyzn7QpEq5cm09rkm/KupHSn0QYDcr1qs8MKj6RGjTVfqPS+pYM5onS15Tm36MBUwKBgQDMnXbZV8yJnj2qdXVLqcpMvv2bSzY9T91/0o1x8d7tgYQ8+bc/pTc1px/hlq4mCrh3EwwvjULPx+Va3/i2Rere+LT4WPqTBinkVC7QHm2grtmuCdnEB21g1Ybfr2UWXr1BlCegkGrkyo9c/sbhYIvVlWIWM733GNpEmeLlrrAKYQKBgQCxAHBm7EUHy02N48QEHEazQ+guSy5U397Adz93GBTVr9pnhOjWo6cisLxIH1yhxz3ftLhp6q4ubOIPvOsk50AfHt2oXahwLjaUID8Hjie7YZ5oOfEPBb3WIQlW7BOIPvyvvJlcL1SUPcWMFOw54HbI9GzrLIbXBWhgZ0aUa6lNiQKBgQDD851DH1VOSK9eABb3eIM/am1M3awKfuZxJz8bPbJHD9LRBPzcxzvdO6erL8YG8bLvZthC4OG5vLqCpDn3H/r+DKPTvO0+3KK6MzplplL0pRG3j0De0DX6vDqwIpVTUC3bhJkhLABg9XmKfjV13p3PqXCrZX2eWQ5iIGm9QMZjKA==";
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq511rhp1NWOIRrD7x2r9p0TwV48QyOb47NhqEHOFMdUJRCoh/sKcWsqX2nN6RkQU/TGixaMh1qL0MleBv+dfovIRf4hL8tr8Su5oPyRCer3gw3ANoAW1rZL4rwW5qg0Qsuc1/Tdz6dIkqQBGJhhprMYr+ZyAcHq5RtRifXTM3zfqZxuygWWaigGK47KshiixsjW/kwSESfrgTBZ4AK/PkvgVeFjxpx3mdTFblVkIlWoS9sZE8KgsTgAW7Zqs+uJ5Do1Qv22BrlTq0j5FipdJNp1rn5NsBfHoFi6LDRBHdViiOWRdofSdo7f9BC5v8cKkaBujoS5cdRsag2ybh+8IywIDAQAB";
    private static final String ALIPAY_PUBLIC_RSA2 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqR28w9W9hw7z0GZMlS/OrtRZ6ACGdCtyL5DtWHdH8x2drcbPatXjpYr0SFG9v5GVLkXkdsNEiWjTJpVbxIoOnn+78bvFFWIMRsJgia+oN6UDkabggzV/AT+kHIxR2DJ6SykNrOYQrZZsDfHeehSwKJfkrtqaiL8HJ4h3rXcwyuWRk1eIuxRhIgVYcrMrKt+HKYWsAVYGX3b7ydDbilgxZzix6Mdc7v7P6zo8R/xFi8elM6rkraRosF6yNNUoZuUPf7TP+mVUUW37QpjVHA2Yq7LG0vMNIRoZ+7iDnzkeITsv4bU1T1KxqHWwaaLY//NRVGEBLkWLh5RGEJunab4cRQIDAQAB";

    public static String getAppId() {
        return APP_ID;
    }

    public static String getPrivateKey() {
        return PRIVATE_KEY;
    }

    public static String getPublicKey() {
        return PUBLIC_KEY;
    }


    public static void main(String[] args) {

        /** 开放平台应用APPID */
        String appid = "";

        /** 开放平台应用APPID对应私钥 */
        String privateKey = "";

        /** 开放平台应用APPID对应支付宝公钥 */
        String alipayPublicKey = "";

        /*// sdk初始化
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appid,privateKey, "json","utf-8",alipayPublicKey, "RSA2");

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("测试内容");
        model.setSubject("商品名称");
        model.setTotalAmount("0.01");
        model.setOutTradeNo("2020021500000001");
        request.setBizModel(model);
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功, 支付请求串:" + response.getBody());
            } else {
                System.out.println("调用失败:" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            System.out.println("接口调用异常:" + JSONObject.toJSONString(e));
        }*/
    }

}
