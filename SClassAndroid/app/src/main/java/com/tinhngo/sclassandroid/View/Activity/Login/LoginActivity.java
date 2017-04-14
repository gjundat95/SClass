package com.tinhngo.sclassandroid.View.Activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tinhngo.sclassandroid.Common.ProgressDialogLoading;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Presenter.Login.PLoginPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.Main.MainActivity;
import com.tinhngo.sclassandroid.View.Activity.Register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        checkLogin();
        //testData();
    }

    private PLoginPresenter pLoginPresenter = new PLoginPresenter(this,this);

    @BindView(R.id.input_user_name) EditText teUserName;
    @BindView(R.id.input_password) EditText tePassword;
    @BindView(R.id.link_register) TextView tvRegister;

    private String userName,password;
    private ProgressDialogLoading loading = new ProgressDialogLoading(this,"");

    private void checkLogin(){
        String token = TiSharedPreferences.getSharedPreferences(LoginActivity.this,"Token_Login");
        if(token != null){
            Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    }

    @OnClick(R.id.btn_login)
    public void loginClick(){
        if(validate()){
            loading.showProgressDialog();
            pLoginPresenter.login(
                    userName,
                    password
            );
        }
    }

    @OnClick(R.id.link_register)
    public void linkRegisterClick(){
        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register);
        finish();
    }

    @Override
    public void loginSuccess() {
        loading.dismissProgressDialog();
        Toast.makeText(
                LoginActivity.this,
                "Login success",
                Toast.LENGTH_LONG
        ).show();
        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @Override
    public void loginFail(String message) {
        loading.dismissProgressDialog();
        Toast.makeText(
                LoginActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

    private boolean validate(){
        boolean isValid = true;
        userName = teUserName.getText().toString();
        password = tePassword.getText().toString();

        if(userName.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            teUserName.setError("Please, enter email");
            isValid = false;
        }else {
            teUserName.setError(null);
        }
        if(password.isEmpty()){
            tePassword.setError("Please, enter password");
            isValid = false;
        }else {
            tePassword.setError(null);
        }

        return isValid;
    }

    public void testData(){
        teUserName.setText("gjundat@gmail.com");
        tePassword.setText("123456");
    }

}
