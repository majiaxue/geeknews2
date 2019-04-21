package com.example.geeknew.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknew.R;
import com.example.geeknew.bean.SectionsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
//马佳雪
public class RecSectionsAdapter extends RecyclerView.Adapter<RecSectionsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SectionsBean.DataBean> list;

    public void setContext(Context context) {
        this.context = context;
    }

    public RecSectionsAdapter(Context context, ArrayList<SectionsBean.DataBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sections_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //viewHolder.tv_title.setText(list.get(i).getDescription());
        RoundedCorners roundedCorners=new RoundedCorners(20);
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(500, 500);
        Glide.with(context).load(list.get(i).getThumbnail()).apply(override).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(SectionsBean bean) {
        list.addAll(bean.getData());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
