package com.tinhngo.sclassandroid.View.Activity.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

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
        testData();
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
    @BindView(R.id.company) EditText etCompany;
    @BindView(R.id.relationships) EditText etRelationships;
    @BindView(R.id.phoneparent) EditText etPhoneParent;


    private String firstName, lastName, email, password, rePassword, sex, phone, birthday, description, address, company, relationships, phoneParent;

    @OnClick(R.id.btn_register)
    public void getTokenClick(){
        if(validate()){
            iRegisterPresenter.register(new RegisterModel(
                    firstName,
                    lastName,
                    email,
                    sex,
                    phone,
                    birthday,
                    description,
                    address,
                    company,
                    relationships,
                    phoneParent,
                    password
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
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void fail(String message) {
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
        company = etCompany.getText().toString();
        relationships = etRelationships.getText().toString();
        phoneParent = etPhoneParent.getText().toString();

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
        if(company.isEmpty()){
            etCompany.setError("Please, enter company");
            isValid = false;
        }else {
            etCompany.setError(null);
        }
        if(relationships.isEmpty()){
            etRelationships.setError("Please, enter relationships");
            isValid = false;
        }else {
            etRelationships.setError(null);
        }
        return isValid;
    }

    private void testData(){
        etFirstName.setText("Bach");
        etLastName.setText("Tuyet");
        etEmail.setText("dogdog@gmail.com");
        etPassword.setText("123456");
        etRePassword.setText("123456");
        etSex.setText("1");
        etPhone.setText("09747682980");
        etBirthday.setText("01011995");
        etDescription.setText("No Comment");
        etAddress.setText("Thai Nguyen");
        etCompany.setText("Thai Nguyen Entertainment");
        etRelationships.setText("0");
        etPhoneParent.setText("03023842882");

    }

}
