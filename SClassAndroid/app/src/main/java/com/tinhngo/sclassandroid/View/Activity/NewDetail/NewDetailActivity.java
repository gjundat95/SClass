package com.tinhngo.sclassandroid.View.Activity.NewDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Common.Util;
import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.R;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        ButterKnife.bind(this);
        bindingNewModel();
        Util.initActionBar(this);
    }

    @BindView(R.id.title) TextView title;
    @BindView(R.id.author) TextView author;
    @BindView(R.id.date_time) TextView dateTime;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.image_large) ImageView imageLarge;
    @BindView(R.id.content) TextView content;

    private void bindingNewModel(){
        NewModel model = getNewModel();
        if(model != null){
            title.setText(model.getTitle());
            description.setText(model.getDescription());
            content.setText(model.getContent());
        }

    }

    private NewModel getNewModel(){
        Bundle bundle = getIntent().getExtras();
        String newModels = bundle.getString("New_Detail");
        if(newModels != null){
            Gson gson = new Gson();
            Type type = new TypeToken<NewModel>(){}.getType();
            NewModel newModel = gson.fromJson(newModels,type);
            return newModel;
        }
        return null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
