package com.tinhngo.sclassandroid.Presenter.Login;

import android.content.Context;
import android.content.Intent;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Model.LoginModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;
import com.tinhngo.sclassandroid.View.Activity.Login.ILoginView;
import com.tinhngo.sclassandroid.View.Activity.Main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tinhngo on 3/25/17.
 */

public class PLoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private Context mContext;

    private String token;
    private String userName;
    private String password;

    public PLoginPresenter(ILoginView iLoginView, Context mContext) {
        this.iLoginView = iLoginView;
        this.mContext = mContext;
    }

    @Override
    public void login(String userName, String password) {
        this.userName = userName;
        this.password = password;
        loginServer();
    }

    private void loginServer(){
        ISClassApi.Factory.getInstance().login(new LoginModel(userName,password)).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        ResponseModel responseModel = response.body();
                        if(responseModel.isSuccess()){
                            // save token
                            String tokenLogin = responseModel.getData().toString();
                            if(!tokenLogin.isEmpty()){
                                TiSharedPreferences.saveSharedPreferences(mContext,"Token_Login",tokenLogin);
                                iLoginView.loginSuccess();
                            }
                        }else {
                            iLoginView.loginFail("Error: "+responseModel.getMessage().toString());
                        }
                    }else {
                        iLoginView.loginFail("Error: "+response.errorBody());
                    }
                }else {
                    iLoginView.loginFail("Error: "+response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iLoginView.loginFail("Error: "+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFail(String message) {

    }

}
