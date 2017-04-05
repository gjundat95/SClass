package com.tinhngo.sclassandroid.View.Activity.MyClass.Adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinhngo.sclassandroid.Model.MyClassModel;
import com.tinhngo.sclassandroid.Model.Register2Model;
import com.tinhngo.sclassandroid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ittin on 01/04/2017.
 */

public class MyClassAdapter extends RecyclerView.Adapter<MyClassAdapter.MyClassViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Register2Model.Data.Users.Datum> myClassModels;

    public MyClassAdapter(Context context, List<Register2Model.Data.Users.Datum> myClassModels) {
        this.context = context;
        this.myClassModels = myClassModels;

        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public MyClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_my_class_recycler, parent, false);
        return new MyClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClassViewHolder holder, int position) {
        Register2Model.Data.Users.Datum datum = myClassModels.get(position);
        holder.tvName.setText(datum.getFirstName() + " " +datum.getLastName());
        holder.tvDescription.setText(datum.getEmail());
    }

    @Override
    public int getItemCount() {
        return myClassModels.size();
    }

    class MyClassViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView tvName;
        @BindView(R.id.description) TextView tvDescription;

        public MyClassViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
