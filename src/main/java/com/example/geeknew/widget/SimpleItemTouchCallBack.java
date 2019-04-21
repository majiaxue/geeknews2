package com.example.geeknew.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import okhttp3.Callback;

public class SimpleItemTouchCallBack extends ItemTouchHelper.Callback {

    private TouchCallBack mCallBack;
    private boolean mSwipeEnable=true;

    public SimpleItemTouchCallBack(TouchCallBack callBack){
        this.mCallBack=callBack;
    }
    /**
     *返回可以滑动的方向,一般使用makeMovementFlags(int,int)
     * 或makeFlag(int, int)来构造我们的返回值
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag=ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许向左滑动
        int swipe=ItemTouchHelper.LEFT;
        //设置
        return makeMovementFlags(drag,swipe);
    }
    /**
     * 上下拖动item时回调,可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，
     * 表示被拖动的ViewHolder已经移动到了目的位置
     * @param recyclerView
     * @param viewHolder
     * @param
     * @return
     */

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        mCallBack.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }

    public void setSwipeEnable(boolean enable) {
        mSwipeEnable = enable;
    }
}
