package com.tinhngo.sclassandroid.View.Activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        testData();
    }

    private PLoginPresenter pLoginPresenter = new PLoginPresenter(this,this);

    @BindView(R.id.input_user_name) EditText teUserName;
    @BindView(R.id.input_password) EditText tePassword;
    @BindView(R.id.link_register) TextView tvRegister;

    private String userName,password;

    @OnClick(R.id.btn_login)
    public void loginClick(){
        if(validate()){
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
        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(
                LoginActivity.this,
                "Login Fail: "+message,
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
        teUserName.setText("admin@gmail.com");
        tePassword.setText("123456");
    }

}
