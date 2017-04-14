package com.tinhngo.sclassandroid.View.Activity.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tinhngo.sclassandroid.Common.ProgressDialogLoading;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Presenter.Register.IRegisterPresenter;
import com.tinhngo.sclassandroid.Presenter.Register.PRegisterPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.Login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    private IRegisterPresenter iRegisterPresenter = new PRegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //testData();
    }

    @BindView(R.id.first_name) EditText etFirstName;
    @BindView(R.id.last_name) EditText etLastName;
    @BindView(R.id.email) EditText etEmail;
    @BindView(R.id.password) EditText etPassword;
    @BindView(R.id.repassword) EditText etRePassword;
    @BindView(R.id.sex) EditText etSex;
    @BindView(R.id.phone) EditText etPhone;
    @BindView(R.id.birthday) EditText etBirthday;
    @BindView(R.id.description) EditText etDescription;
    @BindView(R.id.address) EditText etAddress;
    @BindView(R.id.btn_register) Button register;

    private ProgressDialogLoading loading = new ProgressDialogLoading(this,"");

    private String idUser, firstName, lastName, email, password, rePassword, sex, phone, birthday, description, address, avatar;

    @OnClick(R.id.btn_register)
    public void getTokenClick(){
        if(validate()){
            loading.showProgressDialog();
            iRegisterPresenter.register(new RegisterModel(
                    idUser = null,
                    firstName,
                    lastName,
                    email,
                    password,
                    sex,
                    phone,
                    birthday,
                    description,
                    address,
                    avatar = ""
            ));
        }
    }

    @OnClick(R.id.link_login)
    public void linkLoginClick(){
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void success() {
        loading.dismissProgressDialog();
        Toast.makeText(
                RegisterActivity.this,
                "Register success",
                Toast.LENGTH_LONG
        ).show();
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void fail(String message) {
        loading.dismissProgressDialog();
        Toast.makeText(
                RegisterActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

    private boolean validate(){
        boolean isValid = true;
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        rePassword = etRePassword.getText().toString();
        sex = etSex.getText().toString();
        phone = etPhone.getText().toString();
        birthday = etBirthday.getText().toString();
        description = etDescription.getText().toString();
        address = etAddress.getText().toString();


        if(firstName.isEmpty()){
            etFirstName.setError("Please, enter first name");
            isValid = false;
        }else {
            etFirstName.setError(null);
        }
        if(lastName.isEmpty()){
            etLastName.setError("Please, enter last name");
            isValid = false;
        }else {
            etLastName.setError(null);
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please, enter email");
            isValid = false;
        }else {
            etEmail.setError(null);
        }
        if(password.isEmpty()){
            etPassword.setError("Please, enter password");
            isValid = false;
        }else {
            etPassword.setError(null);
        }
        if(rePassword.isEmpty() || !rePassword.equals(password)){
            etRePassword.setError("Please, rePassword invalid");
            isValid = false;
        }else {
            etRePassword.setError(null);
        }
        if(sex.isEmpty()){
            etSex.setError("Please, enter sex");
            isValid = false;
        }else {
            etSex.setError(null);
        }
        if(phone.isEmpty()){
            etPhone.setError("Please, enter phone");
            isValid = false;
        }else {
            etPhone.setError(null);
        }
        if(birthday.isEmpty()){
            etBirthday.setError("Please, enter birthday");
            isValid = false;
        }else {
            etBirthday.setError(null);
        }
        if(description.isEmpty()){
            etDescription.setError("Please, enter description");
            isValid = false;
        }else {
            etDescription.setError(null);
        }
        if(address.isEmpty()){
            etAddress.setError("Please, enter address");
            isValid = false;
        }else {
            etAddress.setError(null);
        }

        return isValid;
    }

    private void testData(){
        etFirstName.setText("Admin");
        etLastName.setText("Adam");
        etEmail.setText("admin@gmail.com");
        etPassword.setText("123456");
        etRePassword.setText("123456");
        etSex.setText("0");
        etPhone.setText("09747682980");
        etBirthday.setText("1995/02/20");
        etDescription.setText("No Comment");
        etAddress.setText("Thai Nguyen");

    }

}
