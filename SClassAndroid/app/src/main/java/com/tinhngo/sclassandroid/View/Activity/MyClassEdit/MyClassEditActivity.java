package com.tinhngo.sclassandroid.View.Activity.MyClassEdit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Common.ProgressDialogLoading;
import com.tinhngo.sclassandroid.Common.Util;
import com.tinhngo.sclassandroid.Model.MyClassModel;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Presenter.MyClassEdit.PMyClassEditPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.Main.MainActivity;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyClassEditActivity extends AppCompatActivity implements IMyClassEditView{

    private PMyClassEditPresenter pMyClassEditPresenter = new PMyClassEditPresenter(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_edit);
        ButterKnife.bind(this);
        Util.initActionBar(this);
        bindingUserInfo();
    }

    @BindView(R.id.first_name) EditText etFirstName;
    @BindView(R.id.last_name) EditText etLastName;
    @BindView(R.id.email) EditText etEmail;
    @BindView(R.id.sex) EditText etSex;
    @BindView(R.id.phone) EditText etPhone;
    @BindView(R.id.birthday) EditText etBirthday;
    @BindView(R.id.description) EditText etDescription;
    @BindView(R.id.address) EditText etAddress;
    @BindView(R.id.btn_save) Button btnSave;


    private ProgressDialogLoading loading = new ProgressDialogLoading(this,"");

    private String idUser, firstName, lastName, email ,password, sex, phone, birthday, description, address, avatar;

    @OnClick(R.id.btn_save)
    public void saveClick(){
        if(validate()){
            loading.showProgressDialog();
            pMyClassEditPresenter.save(new RegisterModel(
                    idUser,
                    firstName,
                    lastName,
                    email,
                    password = "",
                    sex,
                    phone,
                    birthday,
                    description,
                    address,
                    avatar = ""
            ));
        }
    }

    private void bindingUserInfo(){
        RegisterModel userInfo = getUserInfo();
        if(userInfo != null){
            idUser = userInfo.getUserId();
            etFirstName.setText(userInfo.getFirstName());
            etLastName.setText(userInfo.getLastName());
            etEmail.setText(userInfo.getEmail());
            etSex.setText(userInfo.getSex());
            etPhone.setText(userInfo.getPhoneNumber());
            etBirthday.setText(userInfo.getBirthday());
            etDescription.setText(userInfo.getDescription());
            etAddress.setText(userInfo.getAddress());
        }
    }

    private RegisterModel getUserInfo(){
        Bundle bundle = getIntent().getExtras();
        String userInfo = bundle.getString("User_Info");
        if(userInfo != null){
            Gson gson = new Gson();
            Type type = new TypeToken<RegisterModel>(){}.getType();
            RegisterModel groupUserModel = gson.fromJson(userInfo,type);
            return groupUserModel;
        }
        return null;
    }

    @Override
    public void success() {
        loading.dismissProgressDialog();
        Toast.makeText(
                MyClassEditActivity.this,
                "Update Success",
                Toast.LENGTH_LONG
        ).show();
        finish();
    }

    @Override
    public void fail(String message) {
        loading.dismissProgressDialog();
        Toast.makeText(
                MyClassEditActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

    private boolean validate(){

        boolean isValid = true;
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
