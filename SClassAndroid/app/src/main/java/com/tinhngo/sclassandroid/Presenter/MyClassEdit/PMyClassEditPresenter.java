package com.tinhngo.sclassandroid.Presenter.MyClassEdit;

import android.content.Context;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;
import com.tinhngo.sclassandroid.View.Activity.MyClassEdit.IMyClassEditView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ittin on 11/04/2017.
 */

public class PMyClassEditPresenter implements IMyClassEditPresenter {

    private IMyClassEditView iMyClassEditView;
    private Context mContext;

    public PMyClassEditPresenter(IMyClassEditView iMyClassEditView, Context mContext) {
        this.iMyClassEditView = iMyClassEditView;
        this.mContext = mContext;
    }

    @Override
    public void save(final RegisterModel registerModel) {
        ISClassApi.Factory.getInstance().updateUser(registerModel).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    ResponseModel responseModel = response.body();
                    if(responseModel.isSuccess()){
                        iMyClassEditView.success();
                    }else {
                        iMyClassEditView.fail("Error: "+responseModel.getMessage());
                    }
                }else {
                    iMyClassEditView.fail("Error: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                iMyClassEditView.fail("Error: "+t.getLocalizedMessage());
            }
        });
    }

}
