package com.example.geeknew.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.RecV2exItemAdapter;
import com.example.geeknew.bean.V2exItemBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
//马佳雪
public class V2exItemFragment extends Fragment {
    private String cid;
    private RecyclerView recyclerview;
    private String mUrl = "https://www.v2ex.com/";
    private static final String TAG = "V2exFragment";
    private ArrayList<V2exItemBean> list=new ArrayList<>();
    private RecV2exItemAdapter adapter;
    private String auther;
    private String name;
    private String src;
    private String text;

    public V2exItemFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public V2exItemFragment(String s) {
        this.cid = s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_v2ex_item, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements items = doc.select("div.cell.item");
                    for (Element item : items) {
                        Element image = item.select("table tr td a > img.avatar").first();
                        src = image.attr("src");
                        Log.d(TAG, "src: " + src);
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null) {
                            String href = comment.attr("href");
                            String text = comment.text();
                        }
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        text = title.text();
                        Log.d(TAG, "标题:" + text);//重要字段

                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secodaryTab = topic.select("a.node").first();
                        String secTab = secodaryTab.text();
                        Log.d(TAG, "secTab" + secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText:" + topicText);
                        Elements people = topic.select("strong>a");

                        if (people.size() > 0) {
                            Element element = people.get(0);
                            auther = element.text();
                            Log.d(TAG, "作者：" + auther);//重要字段
                        }

                        if (people.size() > 1) {
                            Element element = people.get(1);
                            name = element.text();
                            Log.d(TAG, "最后的评论者:" + element.text());//重要字段
                        }
                        ArrayList<V2exItemBean> kk=new ArrayList<>();
                        kk.add(new V2exItemBean(src, auther, text, name));
                        list.addAll(kk);

                        //final V2exItemBean itemList = new V2exItemBean(src, auther, text, name);
                       // Log.d(TAG, itemList + "");
                        Log.d(TAG,list+"");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setData(list);
                                adapter = new RecV2exItemAdapter(getContext());
                                recyclerview.setAdapter(adapter);
                            }
                        });



                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView(View inflate) {
        recyclerview = (RecyclerView) inflate.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);

    }
}
