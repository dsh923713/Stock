package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.zmq.stock.R;
import com.zmq.stock.adapter.SuperiorRankAdapter;
import com.zmq.stock.base.BaseFragment;
import com.zmq.stock.bean.SuperiorRankBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/22.
 */

public class SuperiorRankFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,SuperSwipeRefreshLayout.OnPushLoadMoreListener{

    private static SuperiorRankFragment superiorRankFragment;

    @BindView(R.id.rv_surperior_rank)
    RecyclerView rvSurperiorRank;
    @BindView(R.id.ssrl_surperior_rank)
    SuperSwipeRefreshLayout ssrlSurperiorRank;

    private List<SuperiorRankBean> data;

    /**
     * 单例模式
     *
     * @return
     */
    public static SuperiorRankFragment getInstance() {
        if (superiorRankFragment == null) {
            superiorRankFragment = new SuperiorRankFragment();
        }
        return superiorRankFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_superior_rank, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        setSuperiorRankBean();
        rvSurperiorRank.setLayoutManager(new LinearLayoutManager(activity));
        rvSurperiorRank.setAdapter(new SuperiorRankAdapter(data));
        ssrlSurperiorRank.setOnPullRefreshListener(this);
        ssrlSurperiorRank.setOnPushLoadMoreListener(this);
    }

    private void setSuperiorRankBean() {
        data = new ArrayList<>();
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
    }

    /**
     * 上拉加载跟下拉刷新
     */
    @Override
    public void onRefresh() {
        ssrlSurperiorRank.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean b) {

    }

    @Override
    public void onLoadMore() {
        ssrlSurperiorRank.setLoadMore(false);
    }

    @Override
    public void onPushDistance(int i) {

    }

    @Override
    public void onPushEnable(boolean b) {

    }
}
