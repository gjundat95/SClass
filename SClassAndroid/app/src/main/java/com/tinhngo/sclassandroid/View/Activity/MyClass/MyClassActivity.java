package com.tinhngo.sclassandroid.View.Activity.MyClass;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tinhngo.sclassandroid.Common.Util;
import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.Presenter.MyClass.PMyClassPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.MyClass.Adapter.MyClassAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyClassActivity extends AppCompatActivity implements IMyClassView, MyClassAdapter.MyClassListener {

    private PMyClassPresenter pMyClassPresenter = new PMyClassPresenter(this,this);
    private List<RegisterModel> registerModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        ButterKnife.bind(this);

        // Toolbar
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        Util.initActionBar(this);

        pMyClassPresenter.getListUser();

    }

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public void getListUser(List<RegisterModel> registerModels) {
        this.registerModels = registerModels;
        initRecyclerView();
    }

    private void initRecyclerView(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        MyClassAdapter adapter = new MyClassAdapter(this,registerModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void fail(String message) {
        Toast.makeText(
                MyClassActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void deleteSuccess() {
        Toast.makeText(
                MyClassActivity.this,
                "Delete Success",
                Toast.LENGTH_SHORT
        ).show();
        pMyClassPresenter.getListUser();
    }

    @Override
    public void onListenerDelete(int i) {

        RegisterModel registerModel = registerModels.get(i);
        pMyClassPresenter.delete(registerModel.getUserId());

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        pMyClassPresenter.getListUser();
    }
}
