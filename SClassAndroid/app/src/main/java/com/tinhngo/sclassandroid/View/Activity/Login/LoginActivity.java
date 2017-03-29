package com.tinhngo.sclassandroid.View.Activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.tinhngo.sclassandroid.Presenter.Login.PLoginPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.Main.MainActivity;

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

    @BindView(R.id.input_user_name)
    EditText userName;
    @BindView(R.id.input_password)
    EditText password;

    @OnClick(R.id.btn_login)
    public void loginClick(){
        if(!userName.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            pLoginPresenter.login(
                    userName.getText().toString(),
                    password.getText().toString()
            );
        }else {
            Toast.makeText(
                    LoginActivity.this,
                    "Please, enter username and password",
                    Toast.LENGTH_LONG
                    ).show();
        }
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

    public void testData(){
        userName.setText("admin@gmail.com");
        password.setText("123456");
    }

}
