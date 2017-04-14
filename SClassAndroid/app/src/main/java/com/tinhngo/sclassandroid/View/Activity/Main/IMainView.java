package com.tinhngo.sclassandroid.View.Activity.Main;

import com.tinhngo.sclassandroid.Model.NewModel;

import java.util.List;

/**
 * Created by Jundat95 on 3/28/2017.
 */

public interface IMainView {

    void success();
    void fail(String message);
    void getNews(List<NewModel> newModels);
}
