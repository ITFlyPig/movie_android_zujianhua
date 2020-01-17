package com.wangyuelin.basebiz;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : yuelinwang
 * time   : 2020-01-17 15:50
 * desc   : 具有刷新的和加载更多的fragment
 */
public abstract class BaseRefreshFragment extends BaseFagment {
    @BindView(R2.id.fl_content)
    FrameLayout flContent;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected View wrapperLayout(int layoutId) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.base_refresh_fragment, null);//具备刷新功能
        ButterKnife.bind(this, v);
        flContent.addView(LayoutInflater.from(getActivity()).inflate(layoutId, null));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                BaseRefreshFragment.this.onRefresh(refreshLayout);

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                BaseRefreshFragment.this.onLoadMore(refreshLayout);

            }
        });
        refreshLayout.setEnableLoadMore(enableLoadMore());//控制加载更多是否可用
        return v;
    }

    /**
     * 刷新
     * @param refreshLayout
     */
    protected void onRefresh(RefreshLayout refreshLayout) {}

    /**
     * 加载更多
     * @param refreshLayout
     */
    protected void onLoadMore(RefreshLayout refreshLayout){}

    /**
     * 刷新完成
     */
    protected void finishRefresh() {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 加载完成
     */
    protected void finishLoadMore() {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
        }
    }

    /**
     * 加载更多是否可用
     * @return
     */
    protected boolean enableLoadMore() {
        return true;
    }

    /**
     * 自动刷新
     */
    protected void autoRefresh() {
        if (refreshLayout != null) {
            refreshLayout.autoRefresh();
        }
    }

    /**
     * 没有更多
     */
    protected void finishLoadMoreWithNoMoreData() {
        if (refreshLayout != null) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

}
