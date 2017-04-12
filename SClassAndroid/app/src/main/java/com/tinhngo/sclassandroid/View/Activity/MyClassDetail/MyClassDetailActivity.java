package com.tinhngo.sclassandroid.View.Activity.MyClassDetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Common.BlurBuilder;
import com.tinhngo.sclassandroid.Common.Util;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.R;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyClassDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_detail);
        ButterKnife.bind(this);
        bindingUserInfo();
        Util.initActionBar(this);
        setBackGround();
    }

    @BindView(R.id.header) LinearLayout header;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.full_name) TextView fullName;
    @BindView(R.id.phone_number) TextView phoneNumber;
    @BindView(R.id.sex) TextView sex;
    @BindView(R.id.birthday) TextView birthday;
    @BindView(R.id.description) TextView description;


    private void bindingUserInfo(){
        RegisterModel registerModel = getUserInfo();
        if(registerModel != null){

            email.setText(registerModel.getEmail());
            fullName.setText("FullName: "+registerModel.getFirstName() + " " +registerModel.getLastName());
            phoneNumber.setText("PhoneNumber: "+registerModel.getPhoneNumber());
            if(registerModel.isMale()){
                sex.setText("Sex: "+"Male");
                avatar.setImageResource(R.drawable.album2);
            }else {
                sex.setText("Sex: Female");
                avatar.setImageResource(R.drawable.album11);
            }
            birthday.setText("Birthday: "+registerModel.getBirthday());
            description.setText("Description: "+registerModel.getDescription());
        }
    }

    private RegisterModel getUserInfo(){
        Bundle bundle = getIntent().getExtras();
        String userInfo = bundle.getString("My-Class");
        if(userInfo != null){
            Gson gson = new Gson();
            Type type = new TypeToken<RegisterModel>(){}.getType();
            RegisterModel groupUserModel = gson.fromJson(userInfo,type);
            return groupUserModel;
        }
        return null;
    }

    private void setBackGround(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cover);
        Bitmap blurredBitmap = BlurBuilder.blur( this, bitmap );
        header.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
