package com.tinhngo.sclassandroid.Presenter.Main;

import android.content.Context;

import com.tinhngo.sclassandroid.API.ISClassApi;
import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.Model.ResponseModel;
import com.tinhngo.sclassandroid.View.Activity.Main.IMainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tinhngo on 14/04/2017.
 */

public class PMainPresenter implements IMainPresenter {

    private IMainView iMainView;
    private Context mContext;

    public PMainPresenter(IMainView iMainView, Context mContext) {
        this.iMainView = iMainView;
        this.mContext = mContext;
    }

    @Override
    public void getNews() {
        ISClassApi.Factory.getInstance().getNews().enqueue(new Callback<List<NewModel>>() {
            @Override
            public void onResponse(Call<List<NewModel>> call, Response<List<NewModel>> response) {
                if(response.isSuccessful()){
                    List<NewModel> newModels = response.body();
                    if(newModels.size() > 0){
                        iMainView.getNews(newModels);
                        iMainView.success();
                    }else {
                        iMainView.fail("News empty");
                    }
                }else {
                    iMainView.fail("Error: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<NewModel>> call, Throwable t) {
                iMainView.fail("Error: "+t.getLocalizedMessage());
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
