package com.tinhngo.sclassandroid.Presenter.Register;

import com.tinhngo.sclassandroid.Model.RegisterModel;

/**
 * Created by tinhngo on 3/29/17.
 */

public interface IRegisterPresenter {

    void register(RegisterModel registerModel);
    void success();
    void fail(String message);
}
