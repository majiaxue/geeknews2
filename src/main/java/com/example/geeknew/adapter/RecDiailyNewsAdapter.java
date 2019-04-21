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
import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.StoriesBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecDiailyNewsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<DiailyNewsBean.TopStoriesBean> mTopStoriesBeans = new ArrayList<>();
    private ArrayList<StoriesBean> mStoriesBeans = new ArrayList<>();
    private String mDate = "今日要闻";
    private final int TYPE_BANNER = 1;
    private final int TYPE_TIME = 2;
    private final int TYPE_NEWS = 3;
    private ArrayList<String> mStrings;

    public RecDiailyNewsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER){
            //banner
            return new BannerViewHolder(View.inflate(mContext,R.layout.banner_item,null));
        }else if (viewType == TYPE_TIME){
            //时间
            return new TimeViewHolder(View.inflate(mContext,R.layout.zhihu_diaily_text,null));
        }else {
            //新闻
            return new NewsViewHolder(View.inflate(mContext,R.layout.zhihu_diaily_item,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_BANNER){
            //banner
            final BannerViewHolder holder = (BannerViewHolder) viewHolder;
            holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            holder.banner.setImages(mTopStoriesBeans);
            holder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DiailyNewsBean.TopStoriesBean bannerPath = (DiailyNewsBean.TopStoriesBean) path;
                    Glide.with(context)
                            .load(bannerPath.getImage())
                            .into(imageView);
                }
            });

            mStrings = new ArrayList<>();
            for (DiailyNewsBean.TopStoriesBean topStoriesBean : mTopStoriesBeans) {
                mStrings.add(topStoriesBean.getTitle());
            }
            holder.banner.setBannerTitles(mStrings);
            holder.banner.start();

        }else if (type == TYPE_TIME){
            //时间
            TimeViewHolder holder = (TimeViewHolder) viewHolder;
            holder.date.setText(mDate);

        }else {
            //新闻
            int newPositon = position-1;
            if (mTopStoriesBeans.size() > 0){
                newPositon -= 1;
            }
            NewsViewHolder holder = (NewsViewHolder) viewHolder;
            StoriesBean bean = mStoriesBeans.get(newPositon);

            Glide.with(mContext).load(bean.getImages().get(0)).into(holder.images);
            holder.title.setText(bean.getTitle());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mTopStoriesBeans.size()>0){
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mTopStoriesBeans.size()>0 ){
            return mStoriesBeans.size()+1+1;
        }else {
            return mStoriesBeans.size()+1;
        }
    }

    public void addDailyNewsData(DiailyNewsBean bean) {
        mDate = bean.getDate();

        mStoriesBeans.clear();
        if (bean.getStories() != null && bean.getStories().size() > 0){
            mStoriesBeans.addAll(bean.getStories());
        }

        mTopStoriesBeans.clear();
        if (bean.getTop_stories() != null && bean.getTop_stories().size() > 0){
            mTopStoriesBeans.addAll(bean.getTop_stories());
        }
        notifyDataSetChanged();
    }

    public void addBeforeNews(BeforeNewsBean bean) {
        mDate = bean.getDate();

        mStoriesBeans.clear();
        mTopStoriesBeans.clear();
        if (bean.getStories() != null && bean.getStories().size() > 0){
            mStoriesBeans.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class TimeViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        public TimeViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.tv_txt);
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView images;
        private TextView title;
        public NewsViewHolder(View itemView) {
            super(itemView);

            images = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
