package com.zmq.shopmall.fragmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zmq.shopmall.R;
import com.zmq.shopmall.R2;
import com.zmq.shopmall.activity.GoodsDetailsActivity;
import com.zmq.shopmall.adapter.HomeAdapter;
import com.zmq.shopmall.adapter.HomeFootAdapter;
import com.zmq.shopmall.adapter.SortButtonAdapter;
import com.zmq.shopmall.base.BaseFragment;
import com.zmq.shopmall.bean.ButtonBean;
import com.zmq.shopmall.bean.HomeChlidBean;
import com.zmq.shopmall.bean.HomeItemBean;
import com.zmq.shopmall.bean.RecommendBean;
import com.zmq.shopmall.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fj.mtsortbutton.lib.DynamicSoreView;
import fj.mtsortbutton.lib.Interface.IDynamicSore;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HomeFragment extends BaseFragment implements IDynamicSore<ButtonBean>, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R2.id.rv_home)
    RecyclerView rvHome; //商品列表
    @BindView(R2.id.srl_home)
    SwipeRefreshLayout srlHome; //下拉刷新
    private View headItem; //头布局1--轮播
    private Banner banner; //轮播控件
    private View headClassify;//头布局2--分类
    private DynamicSoreView dynamicSoreView;//分类控件
    private View footRecommend; //推荐列表
    private RecyclerView rvFoot;


    private List<Integer> imageId;//图标集合
    private List<HomeItemBean> data; //首页数据
    private List<ButtonBean> buttonList; //分类按钮数据
    private HomeAdapter adapter;//首页recycleview适配器
    private List<RecommendBean> footData;
    private HomeFootAdapter homeFootAdapter;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_library, container, false);
        StatusBarUtil.setColor(activity, ContextCompat.getColor(activity, R.color.black));
        return view;
    }

    @Override
    protected void initView(View view) {
        setData();
        setButtonData();
        setFootData();
        findViewId();
        setBanner();
    }

    /**
     * 获取实例
     */
    private void findViewId() {
        headItem = View.inflate(activity, R.layout.rv_item_banner_head_library, null);
        banner = (Banner) headItem.findViewById(R.id.banner);
        headClassify = View.inflate(activity, R.layout.rv_item_classify_head_library, null);
        dynamicSoreView = (DynamicSoreView) headClassify.findViewById(R.id.dynamicSoreView);
        footRecommend = View.inflate(activity, R.layout.rv_item_home_foot_library, null);
        rvFoot = (RecyclerView) footRecommend.findViewById(R.id.rv_home_foot);


        rvFoot.setLayoutManager(new GridLayoutManager(activity, 2));
        homeFootAdapter = new HomeFootAdapter(footData);
        rvFoot.setAdapter(homeFootAdapter);
        homeFootAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(GoodsDetailsActivity.class);
            }
        });
        homeFootAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showShortToast("找相似");
            }
        });

        //设置界面监听
        dynamicSoreView.setiDynamicSore(this);
        //控件相关设置
        dynamicSoreView.setGridView(R.layout.rv_item_vp_library).init(buttonList);
        rvHome.setLayoutManager(new GridLayoutManager(activity, 2));
        srlHome.setOnRefreshListener(this);
        srlHome.setColorSchemeColors(ContextCompat.getColor(activity, android.R.color.holo_blue_light),
                ContextCompat.getColor(activity, android.R.color.holo_red_light), ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        adapter = new HomeAdapter(R.layout.rv_item_child_classify_home_library, R.layout.rv_item_child_head_library, data);
        adapter.addHeaderView(headItem);
        adapter.addHeaderView(headClassify);
        adapter.addFooterView(footRecommend);
        rvHome.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showShortToast("onItemChildClick" + position);
                startActivity(GoodsDetailsActivity.class);
            }
        });
    }

    /**
     * 模拟数据
     */
    private void setData() {
        data = new ArrayList<>();
        data.add(new HomeItemBean(true, "智能生活", false, "查看更多"));
        data.add(new HomeItemBean(new HomeChlidBean("智能电器", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("智能电器", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("智能电器", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("智能电器", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(true, "特价优惠", true, "更多"));
        data.add(new HomeItemBean(new HomeChlidBean("男女装", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("皮鞋包包", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("皮鞋包包", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(true, "特价优惠", true, "更多"));
        data.add(new HomeItemBean(new HomeChlidBean("男女装", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("皮鞋包包", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(true, "逛商城", true, "更多"));
        data.add(new HomeItemBean(new HomeChlidBean("男女装", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("皮鞋包包", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(true, "东家小院", true, "全部小院"));
        data.add(new HomeItemBean(new HomeChlidBean("男女装", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
        data.add(new HomeItemBean(new HomeChlidBean("皮鞋包包", "特价优惠，低至5折", R.mipmap.ic_launcher, R.mipmap.ic_launcher)));
    }

    /**
     * 模拟分类按钮数据
     */
    private void setButtonData() {
        buttonList = new ArrayList<>();
        ButtonBean buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_1_library);
        buttonBean.setName("美食");
        buttonList.add(buttonBean);

        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_2_library);
        buttonBean.setName("电影");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_3_library);
        buttonBean.setName("酒店");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_4_library);
        buttonBean.setName("休闲娱乐");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_5_library);
        buttonBean.setName("外卖");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_6_library);
        buttonBean.setName("机票/火车票");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_7_library);
        buttonBean.setName("KTV");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_8_library);
        buttonBean.setName("周边游");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_9_library);
        buttonBean.setName("丽人");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_10_library);
        buttonBean.setName("旅游出行");
        buttonList.add(buttonBean);

        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_11_library);
        buttonBean.setName("品质酒店");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_12_library);
        buttonBean.setName("生活服务");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_13_library);
        buttonBean.setName("足疗按摩");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_14_library);
        buttonBean.setName("母婴亲子");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_15_library);
        buttonBean.setName("结婚");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_16_library);
        buttonBean.setName("景点");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_17_library);
        buttonBean.setName("温泉");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_18_library);
        buttonBean.setName("学习培训");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_19_library);
        buttonBean.setName("洗浴/汗蒸");
        buttonList.add(buttonBean);
        buttonBean = new ButtonBean();
        buttonBean.setDrawableIcon(R.drawable.icon_20_library);
        buttonBean.setName("全部分类");
        buttonList.add(buttonBean);
    }

    /**
     * 设置分类中GridView内参数及点击事件
     *
     * @param view
     * @param i
     * @param list
     */
    @Override
    public void setGridView(View view, final int i, final List<ButtonBean> list) {
        GridView gv_item = (GridView) view.findViewById(R.id.gv_item);
        dynamicSoreView.setNumColumns(gv_item);
        SortButtonAdapter adapter = new SortButtonAdapter(activity, list);
        gv_item.setAdapter(adapter);
        gv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showShortToast(list.get(position).getName());
            }
        });
    }

    /**
     * 设置轮播模式
     */
    private void setBanner() {
        getImageList();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageId);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 添加头部轮播图
     */
    private void getImageList() {
        imageId = new ArrayList<>();
        imageId.add(R.mipmap.im1_library);
        imageId.add(R.mipmap.im2_library);
        imageId.add(R.mipmap.im3_library);
        imageId.add(R.mipmap.im4_library);
    }

    /**
     * 底部模拟数据
     */

    private void setFootData(){
        footData = new ArrayList<>();
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,true,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,false,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,true,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,true,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,false,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,true,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,true,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,false,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,false,false));
        footData.add(new RecommendBean(R.mipmap.ic_timg_library,"Letv/乐视LETV体感-超级枪王 乐视TV超级电视产品玩具体感游戏枪 电玩道具黑色",152.00,false,false));
    }
    @Override
    public void onRefresh() {
        srlHome.setRefreshing(false);
    }

}
