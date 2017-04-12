package com.tinhngo.sclassandroid.Presenter.Login;

/**
 * Created by tinhngo on 3/25/17.
 */

public interface ILoginPresenter {

    void login(String userName, String password);
    void loginSuccess();
    void loginFail(String message);

}
