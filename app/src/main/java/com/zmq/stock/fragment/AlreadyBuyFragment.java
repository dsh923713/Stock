package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.zmq.stock.R;
import com.zmq.stock.activity.PersonInformationActivity;
import com.zmq.stock.adapter.SuperiorRankAdapter;
import com.zmq.stock.base.BaseFragment;
import com.zmq.stock.bean.SuperiorRankBean;
import com.zmq.stock.utils.HeadAndFootView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/22.
 */

public class AlreadyBuyFragment extends BaseFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener,
        SuperSwipeRefreshLayout.OnPushLoadMoreListener {
    private static AlreadyBuyFragment alreadyBuyFragment;

    @BindView(R.id.rv_surperior_rank)
    RecyclerView rvSurperiorRank;
    @BindView(R.id.ssrl_surperior_rank)
    SuperSwipeRefreshLayout ssrlSurperiorRank;

    private List<SuperiorRankBean> data; //模拟数据

    public static final int ALREADYBUYID = 2; //代表已购买
    private SuperiorRankAdapter rankAdapter; //适配器
    private HeadAndFootView headAndFootView; //封装的头尾部局

    /**
     * 单例模式
     *
     * @return
     */
    public static AlreadyBuyFragment getInstance() {
        if (alreadyBuyFragment == null) {
            alreadyBuyFragment = new AlreadyBuyFragment();
        }
        return alreadyBuyFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_superior_rank, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        headAndFootView = new HeadAndFootView();
        ssrlSurperiorRank.setHeaderView(headAndFootView.getHeadView(activity));
        ssrlSurperiorRank.setFooterView(headAndFootView.getFootView(activity));
        setSuperiorRankBean();
        rvSurperiorRank.setLayoutManager(new LinearLayoutManager(activity));
        rankAdapter = new SuperiorRankAdapter(data, ALREADYBUYID);
        rvSurperiorRank.setAdapter(rankAdapter);
        ssrlSurperiorRank.setOnPullRefreshListener(this);
        ssrlSurperiorRank.setOnPushLoadMoreListener(this);
        rankAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(PersonInformationActivity.class);
            }
        });
    }

    private void setSuperiorRankBean() {
        data = new ArrayList<>();
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.xiaohei, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
        data.add(new SuperiorRankBean(R.mipmap.renma, "瓦萨其", 100, 88.0, 500, 80, System.currentTimeMillis()));
    }

    /**
     * 上拉加载跟下拉刷新
     */
    @Override
    public void onRefresh() {
        headAndFootView.getTvHead().setText("正在刷新...");
        ssrlSurperiorRank.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean b) {
        headAndFootView.getTvHead().setText(b ? "松开刷新" : "下拉刷新");
        headAndFootView.getIvHead().setImageResource(b ? R.mipmap.ic_refresh_up : R.mipmap.ic_refresh_down);
    }

    @Override
    public void onLoadMore() {
        headAndFootView.getTvFoot().setText("加载中...");
        ssrlSurperiorRank.setLoadMore(false);
    }

    @Override
    public void onPushDistance(int i) {

    }

    @Override
    public void onPushEnable(boolean b) {
        headAndFootView.getTvFoot().setText(b ? "松开加载" : "上拉加载");
        headAndFootView.getIvFoot().setImageResource(b ? R.mipmap.ic_refresh_down : R.mipmap.ic_refresh_up);
    }
}
