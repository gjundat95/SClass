package com.tinhngo.sclassandroid.Presenter.Register;

import android.util.Log;
import android.widget.Toast;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;
import com.tinhngo.sclassandroid.View.Activity.Register.IRegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tinhngo on 3/29/17.
 */

public class PRegisterPresenter implements IRegisterPresenter {

    private IRegisterView iRegisterView;
    private RegisterModel registerModel;
    private String token;

    public PRegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void register(RegisterModel registerModel) {
        this.registerModel = registerModel;
        registerServer();
    }


    private void registerServer(){
        ISClassApi.Factory.getInstance().register(
              registerModel

        ).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    if(responseModel.isSuccess()){
                        iRegisterView.success();
                    }else {
                        iRegisterView.fail("Error: "+responseModel.getMessage());
                    }
                }else {
                    iRegisterView.fail("Error: "+response.errorBody()+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iRegisterView.fail("Error: "+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String message) {

    }


}
