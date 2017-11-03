package com.ekosantoso.visco.api;

import com.ekosantoso.visco.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Eko PS on 10/26/2017.
 */

public interface ApiRequest {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> InsertData(@Field("kd_cus") String kd_cus,
                                  @Field("nm_cus") String nm_cus,
                                  @Field("al_cus") String al_cus);

    @GET("read.php")
    Call<ResponsModel> GetData();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> UpdateData(@Field("kd_cus") String kd_cus,
                                  @Field("nm_cus") String nm_cus,
                                  @Field("al_cus") String al_cus);

    @GET("/desa.php")
    Call<ResponsModel> GetDesa(@Query("filter") String filter);
}
