package com.tinhngo.sclassandroid.Presenter.MyClass;

import android.content.Context;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.View.Activity.MyClass.IMyClassView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ittin on 01/04/2017.
 */

public class PMyClassPresenter implements IMyClassPresenter {

    private IMyClassView iMyClassView;
    private String token;
    private Context mContext;

    public PMyClassPresenter(Context mContext, IMyClassView iMyClassView) {
        this.mContext = mContext;
        this.iMyClassView = iMyClassView;
    }

    @Override
    public void getListUser() {
        ISClassApi.Factory.getInstance().getListUser().enqueue(new Callback<List<RegisterModel>>() {
            @Override
            public void onResponse(Call<List<RegisterModel>> call, Response<List<RegisterModel>> response) {
                if(response.isSuccessful()){
                    List<RegisterModel> registerModels = response.body();
                    if(registerModels.size() > 0){
                        iMyClassView.getListUser(registerModels);
                    }else {
                        iMyClassView.fail("List Empty");
                    }
                }else {
                    iMyClassView.fail("Error: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<RegisterModel>> call, Throwable t) {
                iMyClassView.fail("Error: "+t.getLocalizedMessage());
            }
        });
    }

}
