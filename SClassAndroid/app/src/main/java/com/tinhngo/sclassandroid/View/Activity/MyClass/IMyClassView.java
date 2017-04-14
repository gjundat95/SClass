package com.tinhngo.sclassandroid.View.Activity.MyClass;

import com.tinhngo.sclassandroid.Model.RegisterModel;

import java.util.List;

/**
 * Created by ittin on 01/04/2017.
 */

public interface IMyClassView {

    void getListUser(List<RegisterModel> registerModels);
    void fail(String message);
    void deleteSuccess();

}
