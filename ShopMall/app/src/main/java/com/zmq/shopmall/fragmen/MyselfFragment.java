package com.zmq.shopmall.fragmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmq.shopmall.R;
import com.zmq.shopmall.R2;
import com.zmq.shopmall.activity.LoginActivity;
import com.zmq.shopmall.activity.NewsActivity;
import com.zmq.shopmall.activity.SetActivity;
import com.zmq.shopmall.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/13.
 */

public class MyselfFragment extends BaseFragment {
    @BindView(R2.id.iv_bg)
    ImageView ivBg; //背景图
    @BindView(R2.id.iv_user)
    ImageView ivUser;//用户
    @BindView(R2.id.ll_user_login)
    LinearLayout llUserLogin; //用户大头像
    @BindView(R2.id.toolbar)
    Toolbar toolbar; //标题栏
    @BindView(R2.id.collapsing)
    CollapsingToolbarLayout collapsing; //收缩控件
    @BindView(R2.id.appbar)
    AppBarLayout appbar;//
    @BindView(R2.id.tv_title)
    TextView tvTitle;//标题
    @BindView(R2.id.iv_news)
    ImageView ivNews; //消息
    @BindView(R2.id.iv_set)
    ImageView ivSet; //设置
    @BindView(R2.id.tv_obligation)
    TextView tvObligation; //待付款
    @BindView(R2.id.tv_wait_for_receive)
    TextView tvWaitForReceive; //到收货
    @BindView(R2.id.tv_no_evaluated)
    TextView tvNoEvaluated; //待评价
    @BindView(R2.id.tv_after_sale)
    TextView tvAfterSale; //退换/售后
    @BindView(R2.id.tv_my_order)
    TextView tvMyOrder;//我的订单
    @BindView(R2.id.rv_myself)
    RecyclerView rvMyself; //自定义布局

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myself_library, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        /**
         * 根据appbar的高度来判断是否改变控件状态
         */
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i == 0) { //展开状态
                    ivUser.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.GONE);
                    ivNews.setImageResource(R.mipmap.ic_news_library);
                    ivSet.setImageResource(R.mipmap.ic_set_library);
                } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) { //折叠状态
                    ivUser.setVisibility(View.VISIBLE);
                    tvTitle.setVisibility(View.VISIBLE);
                    ivNews.setImageResource(R.mipmap.ic_news_black_library);
                    ivSet.setImageResource(R.mipmap.ic_set_black_library);
                } else {  //中间状态
                    ivUser.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.GONE);
                    ivNews.setImageResource(R.mipmap.ic_news_library);
                    ivSet.setImageResource(R.mipmap.ic_set_library);
                }
            }
        });
    }

    @OnClick({R2.id.iv_user, R2.id.ll_user_login, R2.id.iv_news, R2.id.iv_set, R2.id.tv_obligation, R2.id.tv_wait_for_receive, R2.id
            .tv_no_evaluated, R2.id.tv_after_sale, R2.id.tv_my_order})
    void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_user) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.ll_user_login) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.iv_news) {
            startActivity(NewsActivity.class);

        } else if (i == R.id.iv_set) {
            startActivity(SetActivity.class);

        } else if (i == R.id.tv_obligation) {
            showShortToast("待付款");

        } else if (i == R.id.tv_wait_for_receive) {
            showShortToast("待收货");

        } else if (i == R.id.tv_no_evaluated) {
            showShortToast("待评价");

        } else if (i == R.id.tv_after_sale) {
            showShortToast("退换/售后");

        } else if (i == R.id.tv_my_order) {
            showShortToast("我的订单");

        } else {
        }
    }
}
