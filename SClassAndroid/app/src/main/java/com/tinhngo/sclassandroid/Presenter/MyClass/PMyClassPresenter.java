package com.tinhngo.sclassandroid.Presenter.MyClass;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Model.MyClassModel;
import com.tinhngo.sclassandroid.Model.Register2Model;
import com.tinhngo.sclassandroid.Model.ResponseModel;
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
       getListUserImp();
    }

    private void getListUserImp(){
        if(TiSharedPreferences.getSharedPreferences(mContext,"Token_Login") != null){
            token = TiSharedPreferences.getSharedPreferences(mContext,"Token_Login");

            ISClassApi.Factory.getInstance().getListUser(token).enqueue(new Callback<Register2Model>() {
                @Override
                public void onResponse(Call<Register2Model> call, Response<Register2Model> response) {
                    if(response.isSuccessful()){
                        Register2Model register2Model = response.body();
                        if(register2Model.getStatus().equals("success")){
                            Register2Model.Data data = register2Model.getData();
                            Register2Model.Data.Users users = data.getUsers();
                            iMyClassView.getListUser(users.getData());
                        }else {
                            iMyClassView.getListUserFail("error:"+register2Model.getMessage().toString());
                        }
                    }else {
                        iMyClassView.getListUserFail("error:"+response.message());
                    }
                }

                @Override
                public void onFailure(Call<Register2Model> call, Throwable t) {
                    iMyClassView.getListUserFail(t.getLocalizedMessage());
                }
            });
        }else {
            iMyClassView.getListUserFail("Please login");
        }

    }

}
