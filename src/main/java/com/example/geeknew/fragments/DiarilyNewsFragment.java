package com.example.geeknew.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknew.CalActivity;
import com.example.geeknew.R;
import com.example.geeknew.adapter.RecDiailyNewsAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.presenter.DiailyP;
import com.example.geeknew.view.DiailyView;


import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

import static com.example.geeknew.util.CircularAnimUtil.PERFECT_MILLS;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiarilyNewsFragment extends BaseFragment<DiailyView, DiailyP> implements DiailyView {

    @BindView(R.id.fab_calender)
    FloatingActionButton floatButton;
    private RecDiailyNewsAdapter adapter;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private long durationMills = PERFECT_MILLS;


    public DiarilyNewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecDiailyNewsAdapter(getContext());
        rlv.setAdapter(adapter);
        initFloatingActionButton();
    }

    private void initFloatingActionButton() {
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startAnim();
                }
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diarily_news;
    }

    @Override
    protected DiailyP initPresenter() {
        return new DiailyP();
    }


    @Override
    public void setData(DiailyNewsBean bean) {
        adapter.addDailyNewsData(bean);
    }

    @Override
    public void onBeforeNewsData(BeforeNewsBean beforeNewsBean) {
        adapter.addBeforeNews(beforeNewsBean);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String date = data.getStringExtra("date");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
            String todayDate = df.format(new Date());

            if (date.equals(todayDate)) {
                mPresenter.getDailyNewsData();
            } else {
                //因为获取出来的数据是当前日期的前一天,所以要给他加1
                int intDate = Integer.valueOf(date);
                intDate += 1;
                mPresenter.getBeforeNewsData(String.valueOf(intDate));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnim() {
        int[] location = new int[2];
        floatButton.getLocationInWindow(location);
        final int cx = location[0] + floatButton.getWidth() / 2;
        final int cy = location[1] + floatButton.getHeight() / 2;
        final ImageView view = new ImageView(getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(R.color.colorAccent);
        final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
        int w = decorView.getWidth();
        int h = decorView.getHeight();
        decorView.addView(view, w, h);

        // 计算中心点至view边界的最大距离
        int maxW = Math.max(cx, w - cx);
        int maxH = Math.max(cy, h - cy);
        final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
        // 若使用默认时长，则需要根据水波扩散的距离来计算实际时间
        if (durationMills == PERFECT_MILLS) {
            // 算出实际边距与最大边距的比率
            double rate = 1d * finalRadius / maxRadius;
            // 水波扩散的距离与扩散时间成正比
            durationMills = (long) (PERFECT_MILLS * rate);
        }
        final long finalDuration = durationMills;
        anim.setDuration(finalDuration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
               Intent intent = new Intent(getContext(), CalActivity.class);
               startActivityForResult(intent, 100);
                // 默认渐隐过渡动画.
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // 默认显示返回至当前Activity的动画.
                floatButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                        anim.setDuration(finalDuration);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                try {
                                    decorView.removeView(view);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        anim.start();
                    }
                }, 1000);
            }
        });
        anim.start();
    }

}
