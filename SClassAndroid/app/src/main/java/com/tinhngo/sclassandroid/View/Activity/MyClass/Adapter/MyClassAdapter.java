package com.tinhngo.sclassandroid.View.Activity.MyClass.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Model.RegisterModel;
import com.tinhngo.sclassandroid.R;
import com.tinhngo.sclassandroid.View.Activity.MyClassDetail.MyClassDetailActivity;
import com.tinhngo.sclassandroid.View.Activity.MyClassEdit.MyClassEditActivity;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ittin on 01/04/2017.
 */

public class MyClassAdapter extends RecyclerView.Adapter<MyClassAdapter.MyClassViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private MyClassListener myClassListener;

    private List<RegisterModel> myClasses;

    public MyClassAdapter(Context context, List<RegisterModel> myClasses) {
        this.mContext = context;
        this.myClasses = myClasses;
        this.myClassListener = (MyClassListener) mContext;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public MyClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_my_class_recycler, parent, false);
        return new MyClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyClassViewHolder holder, final int position) {
        final RegisterModel myClass = myClasses.get(position);
        holder.name.setText(myClass.getFirstName() + " " + myClass.getLastName());
        holder.email.setText(myClass.getEmail());
        if(myClass.isMale()){
            holder.avatar.setImageResource(R.drawable.album2);
        }else {
            holder.avatar.setImageResource(R.drawable.album11);
        }

        // loading album cover using Glide library
//        Glide.with(mContext).load(datum.getId()).into(holder.avatar);

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.detail, position);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                Type type = new TypeToken<RegisterModel>(){}.getType();
                String userInfo  = gson.toJson(myClasses.get(position),type);

                Intent myClassDetail = new Intent(mContext, MyClassDetailActivity.class);
                myClassDetail.putExtra("My-Class",userInfo);
                mContext.startActivity(myClassDetail);

            }
        });

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private int postition;

        public MyMenuItemClickListener() {
        }

        public MyMenuItemClickListener(int postition) {
            this.postition = postition;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.delete:

                    myClassListener.onListenerDelete(postition);
                    return true;
                case R.id.edit:

                    Gson gson = new Gson();
                    Type type = new TypeToken<RegisterModel>(){}.getType();
                    String userInfo  = gson.toJson(myClasses.get(postition),type);

                    Intent myClassEdit = new Intent(mContext, MyClassEditActivity.class);
                    myClassEdit.putExtra("User_Info",userInfo);
                    mContext.startActivity(myClassEdit);

                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return myClasses.size();
    }

    class MyClassViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView name;
        @BindView(R.id.email) TextView email;
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.detail) ImageView detail;
        @BindView(R.id.card_view) CardView cardView;

        public MyClassViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    public interface MyClassListener {
        void onListenerDelete(int i);
    }
}
