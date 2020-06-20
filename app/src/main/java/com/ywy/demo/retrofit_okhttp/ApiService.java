package com.ywy.demo.retrofit_okhttp;

import com.ywy.demo.bean.Bannar;
import com.ywy.demo.bean.BaseResponse;
import com.ywy.demo.bean.ListData;
import com.ywy.demo.bean.YwyBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author AYL
 */
public interface ApiService {

    /**
     * 文章列表
     */
    @GET(HttpUtils.GET_LIST)
    Call<ResponseBody> getList(@Path("username") String pageNum);

    /**
     * username,password,repassword
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(HttpUtils.REGISTER)
    Observable<YwyBean> register(@FieldMap Map<String, String> params);

    /**
     * username，password
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(HttpUtils.LOGIN)
    Observable<BaseResponse<YwyBean>> login(@FieldMap Map<String, String> params);

    @GET(HttpUtils.LOGOUT)
    Observable<BaseResponse<YwyBean>> logout();

    @GET(HttpUtils.BANNAR)
    Observable<BaseResponse<List<Bannar>>> getBannar();

    @GET(HttpUtils.LIST)
    Observable<BaseResponse<ListData>> getListData(@Query("cid") String cid);
}
