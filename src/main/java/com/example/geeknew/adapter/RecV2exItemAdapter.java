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
import com.example.geeknew.R;
import com.example.geeknew.bean.V2exItemBean;

import java.util.ArrayList;

public class RecV2exItemAdapter extends RecyclerView.Adapter<RecV2exItemAdapter.ViewHolder> {
    private String url="https:";
    private ArrayList<V2exItemBean> list=new ArrayList<>();
    private Context context;

    public RecV2exItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.v2ex_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_title.setText(list.get(i).title);
        viewHolder.tv_name.setText(list.get(i).lastName);
        viewHolder.tv_job.setText(list.get(i).job);
      //  Glide.with(context).load(url+list.get(i).img).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<V2exItemBean> list) {
        list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv_title;
        private TextView tv_name;
        private TextView tv_job;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_job = itemView.findViewById(R.id.tv_job);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
