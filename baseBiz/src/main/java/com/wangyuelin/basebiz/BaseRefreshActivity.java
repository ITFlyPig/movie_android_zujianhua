package com.wangyuelin.basebiz;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * author : yuelinwang
 * time   : 2020-01-17 10:58
 * desc   : 带下拉刷新和加载更多的activity，提供了刷新和加载更多回调
 * autoRefresh：自动刷新
 * enableLoadMore：决定加载更多是否可用
 */
public abstract class BaseRefreshActivity extends BaseActivity {
    FrameLayout flContent;
    SmartRefreshLayout refreshLayout;

    @Override
    protected View wrapperLayout(int layoutId) {
        View v = LayoutInflater.from(getApplication()).inflate(R.layout.base_refresh_activity, null);//具备刷新功能
        flContent = v.findViewById(R.id.fl_content);
        refreshLayout = v.findViewById(R.id.refreshLayout);
        View refreshContent = LayoutInflater.from(getApplication()).inflate(layoutId, flContent, false);
        flContent.addView(refreshContent);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                BaseRefreshActivity.this.onRefresh(refreshLayout);

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                BaseRefreshActivity.this.onLoadMore(refreshLayout);

            }
        });
        refreshLayout.setEnableLoadMore(enableLoadMore());//控制加载更多是否可用
        return v;
    }

    /**
     * 刷新
     *
     * @param refreshLayout
     */
    protected void onRefresh(RefreshLayout refreshLayout) {
    }

    /**
     * 加载更多
     *
     * @param refreshLayout
     */
    protected void onLoadMore(RefreshLayout refreshLayout) {
    }

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
     *
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
