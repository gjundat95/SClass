package com.tinhngo.sclassandroid.Presenter.Login;

import android.content.Context;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Model.ResponseModel;
import com.tinhngo.sclassandroid.View.Activity.Login.ILoginView;

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
        checkToken();

    }

    private void checkToken(){
        ISClassApi.Factory.getInstance().checkToken().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    token = responseModel.getData().toString();
                    checkLogin();
                }else {
                    iLoginView.loginFail("Not check token");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iLoginView.loginFail(t.getLocalizedMessage());
            }
        });
    }

    private void checkLogin(){
        ISClassApi.Factory.getInstance().login(token,userName,password).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        ResponseModel responseModel = response.body();
                        if(responseModel.getStatus().equals("success")){
                            // save token
                            String tokenLogin = responseModel.getData().toString();
                            if(!tokenLogin.isEmpty()){
                                TiSharedPreferences.saveSharedPreferences(mContext,"Token_Login",tokenLogin);
                                iLoginView.loginSuccess();
                            }
                        }else {
                            iLoginView.loginFail(responseModel.getMessage().toString());
                        }
                    }else {
                        iLoginView.loginFail("Please check server");
                    }
                }else {
                    iLoginView.loginFail(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iLoginView.loginFail(t.getLocalizedMessage());
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
