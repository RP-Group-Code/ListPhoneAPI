package com.yoga.listphone2022.API;

import com.yoga.listphone2022.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

//    @GET("retrieveIphone.php")
//    Call<ResponModelIphone> ardRetrieveDataIphone();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("sub_deskripsi") String sub_deskripsis,
            @Field("foto") String foto
    );

//    @FormUrlEncoded
//    @POST("create_Iphone.php")
//    Call<ResponModelIphone> ardCreateDataIphone(
//            @Field("nama") String nama,
//            @Field("deskripsi") String deskripsi,
//            @Field("sub_deskripsi") String sub_deskripsi
//    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

//    @FormUrlEncoded
//    @POST("deleteIphone.php")
//    Call<ResponModelIphone> ardDeleteDataIphone(
//            @Field("id") int id
//    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

//    @FormUrlEncoded
//    @POST("getIphone.php")
//    Call<ResponModelIphone> ardGetDataIphone(
//            @Field("id") int id
//    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("sub_deskripsi") String sub_deskripsi,
            @Field("foto") String foto
    );

//    @FormUrlEncoded
//    @POST("updateIphone.php")
//    Call<ResponModelIphone> ardUpdateDataIphone(
//            @Field("id") int id,
//            @Field("nama") String nama,
//            @Field("deskripsi") String deskripsi,
//            @Field("sub_deskripsi") String sub_deskripsi
//    );
}