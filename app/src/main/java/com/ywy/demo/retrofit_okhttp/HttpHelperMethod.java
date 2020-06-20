package com.ywy.demo.retrofit_okhttp;

import androidx.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ywy.demo.bean.Bannar;
import com.ywy.demo.bean.ListData;
import com.ywy.demo.bean.YwyBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelperMethod extends ApiClient {

    private static ApiService mApiService;

    /**
     * 单例模式
     */
    public static ApiService getApiUrl() {
        if (mApiService == null) {
            synchronized (HttpHelperMethod.class) {
                if (mApiService == null) {
                    mApiService = new HttpHelperMethod().getRetrofit();
                }
            }
        }
        return mApiService;
    }

    private HttpHelperMethod() {
    }

    public ApiService getRetrofit() {
        // 初始化Retrofit
        ApiService apiUrl = initRetrofit(initOkHttp()).create(ApiService.class);
        return apiUrl;
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(HttpUtils.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(HttpUtils.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(HttpUtils.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(HttpUtils.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                //.addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
    }

    public static void login(Map<String, String> params, LoadingObserver<YwyBean> observer) {
        getApiUrl().login(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getBannar(LoadingObserver<List<Bannar>> observer) {
        getApiUrl().getBannar().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getListData(String cid, LoadingObserver<ListData> observer) {
        getApiUrl().getListData(cid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
