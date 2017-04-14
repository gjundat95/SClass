package com.tinhngo.sclassandroid.View.Activity.Main;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Common.TiSharedPreferences;
import com.tinhngo.sclassandroid.Common.Util;
import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.Presenter.Main.IMainPresenter;
import com.tinhngo.sclassandroid.Presenter.Main.PMainPresenter;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.Login.LoginActivity;
import com.tinhngo.sclassandroid.View.Activity.Main.Adapter.MainActivityAdapter;
import com.tinhngo.sclassandroid.View.Activity.MyClass.MyClassActivity;
import com.tinhngo.sclassandroid.View.Activity.NewDetail.NewDetailActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView, MainActivityAdapter.MainListener {

    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.drawer) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_news) RecyclerView recyclerViewNews;

    private IMainPresenter iMainPresenter = new PMainPresenter(this,this);
    List<NewModel> newModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavigationDrawer();
        iMainPresenter.getNews();
    }

    public void initNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){

                    case R.id.about:
                        Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.myclass:
                        Intent myClass = new Intent(MainActivity.this, MyClassActivity.class);
                        drawerLayout.closeDrawers();
                        startActivity(myClass);
                        break;
                    case R.id.logout:
                        TiSharedPreferences.removeSharedPreferences(MainActivity.this,"Token_Login");
                        Intent login = new Intent(MainActivity.this, LoginActivity.class);
                        drawerLayout.closeDrawers();
                        startActivity(login);
                        finish();
                        break;
                    case R.id.exit:
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;

            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("admin");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void getNews(List<NewModel> newModels) {
        this.newModels = newModels;
        initRecycler();
    }

    private void initRecycler(){
        recyclerViewNews.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerViewNews.setLayoutManager(layoutManager);

        MainActivityAdapter adapter = new MainActivityAdapter(this,newModels);
        recyclerViewNews.setAdapter(adapter);
    }

    @Override
    public void onClickItem(int i) {
        // Convert object to json
        Gson gson = new Gson();
        Type type = new TypeToken<NewModel>(){}.getType();
        String json = gson.toJson(newModels.get(i));

        Intent newDetail = new Intent(MainActivity.this, NewDetailActivity.class);
        newDetail.putExtra("New_Detail",json);
        startActivity(newDetail);

    }

    @Override
    public void success() {
        Toast.makeText(
                MainActivity.this,
                "Load news success",
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void fail(String message) {
        Toast.makeText(
                MainActivity.this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

}
