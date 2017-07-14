package com.zmq.shopmall.fragmen;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.zmq.shopmall.R;
import com.zmq.shopmall.R2;
import com.zmq.shopmall.activity.GoodsDetailsActivity;
import com.zmq.shopmall.activity.LoginActivity;
import com.zmq.shopmall.adapter.GoodShopTrolleyAdapter;
import com.zmq.shopmall.adapter.HomeFootAdapter;
import com.zmq.shopmall.base.BaseFragment;
import com.zmq.shopmall.bean.GoodShopTrolleyBean;
import com.zmq.shopmall.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ShopTrolleyFragment extends BaseFragment implements GoodShopTrolleyAdapter.GoodsAccountListener {
    @BindView(R2.id.tv_login)
    TextView tvLogin; //登录按钮
    @BindView(R2.id.tv_shop_isempty)
    TextView tvShopIsempty; //购物车为空
    @BindView(R2.id.tv_good_goods)
    TextView tvGoodGoods;//抢好货
    @BindView(R2.id.tv_look_follow)
    TextView tvLookFollow;//看看关注
    @BindView(R2.id.rv_shop_trolley)
    RecyclerView rvShopTrolley; //购物车列表
    @BindView(R2.id.rv_shop_of_you)
    RecyclerView rvShopOfYou; //推荐列表
    @BindView(R2.id.nsv_shop_trolley)
    NestedScrollView nsvShopTrolley; //整体滑动控件
    @BindView(R2.id.srl_shop_trolley)
    SuperSwipeRefreshLayout srlShopTrolley; //下拉刷新
    @BindView(R2.id.fab_up)
    FloatingActionButton fabUp;
    @BindView(R2.id.cb_all)
    CheckBox cbAll;
    @BindView(R2.id.tv_all_goods_price)
    TextView tvAllGoodsPrice;
    @BindView(R2.id.tv_account_num)
    TextView tvAccountNum;
    @BindView(R2.id.ll_account)
    LinearLayout llAccount;
    @BindView(R2.id.ll_goto_account)
    LinearLayout llGotoAccount;

    private View vHead; //下拉刷新头布局
    private ImageView ivHead; //下拉刷新图片
    private TextView tvHead; //下拉刷新文字
    private View vFoot;//上拉加载布局
    private TextView tvLoadMore; //加载更多
    private ProgressBar pbFoot;

    private List<RecommendBean> data = new ArrayList<>();
    private HomeFootAdapter ofYouAdapter;
    private AnimationDrawable anima;
    private List<GoodShopTrolleyBean> shopTrolleyBeen = new ArrayList<>();
    private GoodShopTrolleyAdapter shopTrolleyAdapter;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_trolley_library, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        setOfYouData();
        setRefresh();
        ofYouAdapter = new HomeFootAdapter(data);
        rvShopOfYou.setLayoutManager(new GridLayoutManager(activity, 2));
        rvShopOfYou.setAdapter(ofYouAdapter);
        shopTrolleyAdapter = new GoodShopTrolleyAdapter(this, shopTrolleyBeen);
        rvShopTrolley.setLayoutManager(new LinearLayoutManager(activity));
        rvShopTrolley.setAdapter(shopTrolleyAdapter);
        ofYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(GoodsDetailsActivity.class);
            }
        });
        ofYouAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                shopTrolleyBeen.add(new GoodShopTrolleyBean(true, "MOD天宇服饰城" + position, R.mipmap.ic_timg_library, "撒的链接发就是浪费撒的",
                        "数量:12 " + "规格：2142152 " + "" + "颜色:蓝色", 120.00));
                shopTrolleyAdapter.notifyDataSetChanged();
                if (shopTrolleyBeen.size() > 0) {
                    tvShopIsempty.setVisibility(View.GONE);
                    llAccount.setVisibility(View.VISIBLE);
                    cbAll.setChecked(true);
                }
            }
        });
        //适配器长按事件
        shopTrolleyAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                shopTrolleyBeen.remove(position);
                shopTrolleyAdapter.numberButton.setCurrentNumber(1);//移除时把数量置为1
                shopTrolleyAdapter.notifyDataSetChanged();
                if (shopTrolleyBeen.size() == 0) {
                    tvShopIsempty.setVisibility(View.VISIBLE);
                    llAccount.setVisibility(View.GONE);
                }
                return false;
            }
        });

        //适配器子布局点击事件
        shopTrolleyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //所有商品全部选中 则全选按钮选中
                for (GoodShopTrolleyBean been : shopTrolleyBeen) {
                    if (been.isChecked()) {
                        cbAll.setChecked(true);
                    }
                }
                if (shopTrolleyBeen.get(position).isChecked()) {
                    shopTrolleyBeen.get(position).setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    shopTrolleyBeen.get(position).setChecked(true);
                }
                shopTrolleyAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置下拉刷新 上拉加载
     */
    private void setRefresh() {
        vHead = View.inflate(activity, R.layout.item_superswiper_head_library, null);
        ivHead = (ImageView) vHead.findViewById(R.id.iv_head);
        tvHead = (TextView) vHead.findViewById(R.id.tv_head);
        vFoot = View.inflate(activity, R.layout.item_superswiper_foot_library, null);
        tvLoadMore = (TextView) vFoot.findViewById(R.id.tv_load_more);
        pbFoot = (ProgressBar) vFoot.findViewById(R.id.pb_foot);
        srlShopTrolley.setHeaderView(vHead);
        srlShopTrolley.setFooterView(vFoot);
        srlShopTrolley.setTargetScrollWithLayout(true);
        srlShopTrolley.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {//TODO 开始刷新
                tvHead.setText("更新中...");
                anima = (AnimationDrawable) ivHead.getDrawable();
                anima.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (srlShopTrolley != null) {
                            srlShopTrolley.setRefreshing(false);
                            anima.stop();
                        }
                    }
                }, 3000);
            }

            @Override
            public void onPullDistance(int i) {//TODO 下拉距离

            }

            @Override
            public void onPullEnable(boolean b) {//TODO 下拉过程中，下拉的距离是否足够出发刷新
//                ivHead
                tvHead.setText(b ? "松开刷新..." : "下拉刷新...");
            }
        });
        srlShopTrolley.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                tvLoadMore.setText("加载中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (srlShopTrolley != null) {
                            srlShopTrolley.setLoadMore(false);
                        }
                    }
                }, 2000);

            }

            @Override
            public void onPushDistance(int i) {// TODO 上拉距离

            }

            @Override
            public void onPushEnable(boolean b) {//TODO 上拉过程中，上拉的距离是否足够出发刷新
                tvLoadMore.setText(b ? "松开加载..." : "下拉加载...");
            }
        });
    }

    @OnClick({R2.id.tv_login, R2.id.tv_good_goods, R2.id.tv_look_follow, R2.id.fab_up, R2.id.cb_all, R2.id.ll_goto_account})
    void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_login) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_good_goods) {
            showShortToast("抢好货");

        } else if (i == R.id.tv_look_follow) {
            showShortToast("看看关注");

        } else if (i == R.id.fab_up) {
            nsvShopTrolley.smoothScrollTo(0, 0);

        } else if (i == R.id.cb_all) {
            if (cbAll.isChecked()) {
                for (GoodShopTrolleyBean been : shopTrolleyBeen) {
                    been.setChecked(true);
                }
            } else {
                for (GoodShopTrolleyBean been : shopTrolleyBeen) {
                    been.setChecked(false);
                }
            }

            shopTrolleyAdapter.notifyDataSetChanged();

        } else if (i == R.id.ll_goto_account) {
        } else {
        }
    }


    /**
     * 推荐列表模拟数据
     */
    private void setOfYouData() {
        data.clear();
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
        data.add(new RecommendBean(R.mipmap.ic_timg_library, "Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色", 152.00, false, true));
    }

    @Override
    public void getGoodsAccount(int number) {
        showShortToast(number + "");

    }
}
