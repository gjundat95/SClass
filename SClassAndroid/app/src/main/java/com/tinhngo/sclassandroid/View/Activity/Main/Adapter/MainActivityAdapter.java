package com.tinhngo.sclassandroid.View.Activity.Main.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinhngo.sclassandroid.Model.NewModel;
import com.tinhngo.sclassandroid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tinhngo on 14/04/2017.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<NewModel> newModels;
    private MainListener mainListener;

    public MainActivityAdapter(Context mContext, List<NewModel> newModels) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.newModels = newModels;
        this.mainListener = (MainListener)mContext;
    }

    @Override
    public MainActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news_recycler,parent,false);
        return new MainActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainActivityViewHolder holder, int position) {
        NewModel newModel = newModels.get(position);
        holder.title.setText(newModel.getTitle());
        holder.author.setText("Tinh Ngo");
        holder.dateTime.setText(newModel.getUpdateTime());
    }

    @Override
    public int getItemCount() {
        return newModels.size();
    }

    class MainActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_large) ImageView imageLarge;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.date_time) TextView dateTime;

        @OnClick(R.id.card_view)
        public void cardViewClick(){
            mainListener.onClickItem(getPosition());
        }

        public MainActivityViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public interface MainListener {
        void onClickItem(int i);
    }
}

