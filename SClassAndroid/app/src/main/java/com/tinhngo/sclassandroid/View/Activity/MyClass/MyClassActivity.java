package com.tinhngo.sclassandroid.View.Activity.MyClass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tinhngo.sclassandroid.Model.MyClassModel;
import com.tinhngo.sclassandroid.Model.Register2Model;
import com.tinhngo.sclassandroid.Presenter.MyClass.PMyClassPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.MyClass.Adpater.MyClassAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyClassActivity extends AppCompatActivity implements IMyClassView {

    private PMyClassPresenter pMyClassPresenter = new PMyClassPresenter(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        ButterKnife.bind(this);
        pMyClassPresenter.getListUser();
    }

    @BindView(R.id.rvMyClass) RecyclerView recyclerView;

    @Override
    public void getListUser(List<Register2Model.Data.Users.Datum> myClassModels) {
        initRecyclerView(myClassModels);
    }

    private void initRecyclerView(List<Register2Model.Data.Users.Datum> myClassModels){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyClassAdapter adapter = new MyClassAdapter(this,myClassModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getListUserFail(String message) {
        Toast.makeText(
                MyClassActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}
