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
        checkToken();

    }

    private void checkToken(){
        ISClassApi.Factory.getInstance().checkToken().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    token = responseModel.getData().toString();
                    registerServer();
                }else {
                    iRegisterView.fail("Not check token");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iRegisterView.fail(t.getLocalizedMessage());
            }
        });
    }

    private void registerServer(){
        ISClassApi.Factory.getInstance().register(
                token,
                registerModel.getFirstName(),
                registerModel.getLastName(),
                registerModel.getEmail(),
                registerModel.getPassword(),
                registerModel.getPassword(),
                registerModel.getSex(),
                registerModel.getPhone(),
                registerModel.getBirthday(),
                registerModel.getDescription(),
                registerModel.getAddress(),
                registerModel.getCompany(),
                registerModel.getRelationships(),
                registerModel.getPhoneParent(),
                token

        ).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    if(responseModel.getStatus().equals("success")){
                        Log.d("PRegister","Register thanhh cong");
                    }else {
                        iRegisterView.fail("Register Error");
                    }
                }else {
                    iRegisterView.fail("Error: "+response.errorBody()+response.message());
                    Log.d("PRegister",""+response.raw()+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

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
