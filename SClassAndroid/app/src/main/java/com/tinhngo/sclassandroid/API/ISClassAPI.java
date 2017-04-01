package com.tinhngo.sclassandroid.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tinhngo on 3/25/17.
 */

public interface ISClassApi {

    @GET("api/v2.0/check")
    Call<ResponseModel> checkToken();

    @POST("api/v2.0/register")
    @FormUrlEncoded
    Call<ResponseModel> register(
            @Query("token") String token,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("repassword") String repassword,
            @Field("sex") String sex,
            @Field("phone") String phone,
            @Field("birthday") String birthday,
            @Field("description") String description,
            @Field("address") String address,
            @Field("company") String company,
            @Field("relationships") String relationships,
            @Field("phone_parent") String phoneParent,
            @Field("token") String tokenNew

    );

    @POST("api/v2.0/login")
    @FormUrlEncoded
    Call<ResponseModel> login(
            @Query("token") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    class Factory{
        public static ISClassApi service;
        public static ISClassApi getInstance(){
            if(service == null){
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ConfigApi.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                service = retrofit.create(ISClassApi.class);
                return service;

            } else {
                return service;
            }
        }
    }
}
