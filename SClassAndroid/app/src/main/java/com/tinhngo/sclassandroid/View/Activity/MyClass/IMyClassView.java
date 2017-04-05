package com.tinhngo.sclassandroid.View.Activity.MyClass;

import com.tinhngo.sclassandroid.Model.MyClassModel;
import com.tinhngo.sclassandroid.Model.Register2Model;

import java.util.List;

/**
 * Created by ittin on 01/04/2017.
 */

public interface IMyClassView {

    void getListUser(List<Register2Model.Data.Users.Datum> myClassModels);
    void getListUserFail(String message);

}
