package com.example.geeknew.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknew.R;
import com.example.geeknew.bean.WChatBean;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecChatAdapter extends RecyclerView.Adapter<RecChatAdapter.ViewHolder> {

    private ArrayList<WChatBean.NewslistBean> list;
    private Context context;
    private String text;
    public void setText(String text){
        this.text=text;
    }

    public void setList(ArrayList<WChatBean.NewslistBean> list) {
        this.list = list;
    }

    public RecChatAdapter(ArrayList<WChatBean.NewslistBean> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wechat_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(list.get(i).getDescription());
        viewHolder.tvTitle.setText(list.get(i).getTitle());
        viewHolder.tvTime.setText(list.get(i).getCtime());
        Glide.with(context).load(list.get(i).getPicUrl()).into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(WChatBean bean) {
        list.addAll(bean.getNewslist());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
