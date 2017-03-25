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

public interface ISClassAPI {

    @GET("api/check")
    Call<ResponseModel> checkToken();

    @POST("register")
    @Headers({"Content-Type:application/json"})
    Call<ResponseModel> register(@Body RegisterModel registerModel);

    @POST("api/login")
    @FormUrlEncoded
    Call<ResponseModel> login(
            @Query("token") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    class Factory{
        public static ISClassAPI service;
        public static ISClassAPI getInstance(){
            if(service == null){
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIConfig.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                service = retrofit.create(ISClassAPI.class);
                return service;

            } else {
                return service;
            }
        }
    }
}
