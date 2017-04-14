package com.tinhngo.sclassandroid.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinhngo.sclassandroid.Model.LoginModel;
import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ittin on 01/04/2017.
 */

public interface ISClassApi {

    @POST("register")
    @Headers("Content-Type:application/json")
    Call<ResponseModel> register(
            @Body RegisterModel registerModel
    );

    @POST("login")
    @Headers("Content-Type:application/json")
    Call<ResponseModel> login(
            @Body LoginModel loginModel
    );

    @GET("get-users")
    Call<List<RegisterModel>> getListUser();

    @PUT("update-user")
    @Headers("Content-Type:application/json")
    Call<ResponseModel> updateUser(
            @Body RegisterModel registerModel
    );

    @DELETE("delete-user/{id}")
    Call<ResponseModel> deleteUser(@Path("id") String id);

    @POST("create-new")
    @Headers("Content-Type:application/json")
    Call<ResponseModel> createNew(@Body NewModel newModel);

    @GET("get-news")
    Call<List<NewModel>> getNews();

    @PUT("edit-new")
    @Headers("Content-Type:application/json")
    Call<ResponseModel> editNew(@Body NewModel newModel);

    @DELETE("delete-new/{id}")
    Call<ResponseModel> deleteNew(@Path("id") String id);

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
