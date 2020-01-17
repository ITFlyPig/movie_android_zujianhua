package com.wangyuelin.adbizstandalone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wangyuelin.basebiz.BaseRefreshActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : yuelinwang
 * time   : 2020-01-17 11:36
 * desc   :
 */
public class ListActivity extends BaseRefreshActivity {
    @BindView(R.id.listview)
    RecyclerView recyclerView;

    ListAdapter listAdapter;
    List<String> mNames;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected boolean isUseTitleView() {
        return false;
    }

    @Override
    protected void initData() {
        super.initData();
        mNames = new ArrayList<>();
        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        makeTestData();
        listAdapter.setData(mNames);

    }

    private void makeTestData() {
        int curCount = mNames.size();
        for (int i = 0; i < 20; i++) {
            mNames.add("name: " + (i + curCount));
        }
    }

    @Override
    protected void onRefresh(RefreshLayout refreshLayout) {
        super.onRefresh(refreshLayout);
        Log.d("wyl", "onRefresh");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mNames.clear();
                makeTestData();
                listAdapter.setData(mNames);
                finishRefresh();
            }
        }, 3000);
    }

    @Override
    protected void onLoadMore(RefreshLayout refreshLayout) {
        super.onLoadMore(refreshLayout);
        Log.d("wyl", "onLoadMore");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mNames.size() >= 40) {
                    finishLoadMoreWithNoMoreData();
                } else {
                    makeTestData();
                    listAdapter.setData(mNames);
                    finishLoadMore();
                }
            }
        }, 3000);

    }

    @Override
    protected void initView() {
        super.initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public static void start(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }
}
