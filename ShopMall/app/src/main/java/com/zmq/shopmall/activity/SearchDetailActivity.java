package com.zmq.shopmall.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmq.shopmall.R;
import com.zmq.shopmall.R2;
import com.zmq.shopmall.adapter.SearchDetailAdapter;
import com.zmq.shopmall.base.BaseActivity;
import com.zmq.shopmall.bean.SearchDetailBean;
import com.zmq.shopmall.fragmen.FiltrateFragment;
import com.zmq.shopmall.utils.ScreenUtil;
import com.zmq.shopmall.widget.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/20.
 */

public class SearchDetailActivity extends BaseActivity {
    @BindView(R2.id.iv_back)
    ImageView ivBack; //返回
    @BindView(R2.id.tv_search)
    TextView tvSearch; //搜索
    @BindView(R2.id.iv_voice)
    ImageView ivVoice; //语音
    @BindView(R2.id.iv_rv_style)
    ImageView ivRvStyle; //排序类别
    @BindView(R2.id.tv_synthesize)
    TextView tvSynthesize; //综合
    @BindView(R2.id.tv_sales_volume)
    TextView tvSalesVolume; //销量
    @BindView(R2.id.tv_price)
    TextView tvPrice; //价格
    @BindView(R2.id.tv_filtrate)
    TextView tvFiltrate; //筛选
    @BindView(R2.id.rv_search)
    RecyclerView rvSearch; //搜索列表
    @BindView(R2.id.ll_foot)
    LinearLayout llFoot;
    @BindView(R2.id.fab_footprint)
    FloatingActionButton fabFootprint; //足迹
    @BindView(R2.id.fab_up)
    FloatingActionButton fabUp; //回到顶部
    @BindView(R2.id.dl_content)
    FrameLayout dlContent;
    @BindView(R2.id.dl_menu)
    DrawerLayout dlMenu;

    private List<SearchDetailBean> searchDetailBeen = new ArrayList<>(); //搜索列表模拟数据
    private boolean isList; //是否单行显示
    private SearchDetailAdapter detailAdapter; //适配器
    private GridLayoutManager gridLayoutManager; //GridView布局样式
    private LinearLayoutManager layoutManager; //线下布局样式

    private boolean isSynthesize; //是否选中综合
    private boolean isSalesVolume; //是否选中销量
    private boolean isPrice; //是否选中价格
    private boolean isFiltrate; //是否选中筛选
    private CustomPopWindow popWindow; //综合排序弹窗
    private TextView tv_synthesize_sort; //综合排序
    private TextView tv_new_goods; //新品优先
    private TextView tv_recommend_priority; //评论数优先
    private Drawable drawable; //打勾的drawable对象
    private int rvPostion; //记录滑动的位置

    public SearchDetailActivity() {
        super(R.layout.activity_search_detail_library);
    }

    @Override
    protected void initView() {
        fabUp.hide();
        setSearchDetailBean("综合");
        gridLayoutManager = new GridLayoutManager(this, 2);
        layoutManager = new LinearLayoutManager(this);
        rvSearch.setLayoutManager(gridLayoutManager);
        detailAdapter = new SearchDetailAdapter(1, searchDetailBeen);
        rvSearch.setAdapter(detailAdapter);
        setRvonScrollListener();
        ViewGroup.LayoutParams params = dlContent.getLayoutParams();
        params.width = (int) (ScreenUtil.getScreenWidthPix(this)*0.85);
//        params.height = (int) (ScreenUtil.getScreenHeightPix(this)*0.9);
        dlContent.setLayoutParams(params);
        replaceFragment(R.id.dl_content, new FiltrateFragment());
    }

    /**
     * 监听recycleview的滑动事件
     */
    private void setRvonScrollListener() {
        rvSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) { //判断是否滑动到底部
                    llFoot.setVisibility(View.VISIBLE);
                } else {
                    llFoot.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                rvPostion = l.findFirstCompletelyVisibleItemPosition();//当前recycleview滑动的item id
                if (rvPostion > 1) { //是否显示回到顶部按钮
                    fabUp.show();
                } else {
                    fabUp.hide();
                }
            }
        });
    }

    /**
     * 点击事件
     */
    @OnClick({R2.id.iv_back, R2.id.tv_search, R2.id.iv_voice, R2.id.iv_rv_style, R2.id.fab_up, R2.id.fab_footprint, R2.id
            .tv_synthesize, R2.id.tv_sales_volume, R2.id.tv_price, R2.id.tv_filtrate})
    void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();

        } else if (i == R.id.tv_search) {
            startActivity(SearchActivity.class);

        } else if (i == R.id.iv_voice) {
            showShortToast("语音");

        } else if (i == R.id.iv_rv_style) {
            if (!isList) {
                isList = true;
                ivRvStyle.setImageResource(R.mipmap.ic_list_normal_library);
                rvSearch.setLayoutManager(layoutManager);
                detailAdapter = new SearchDetailAdapter(2, searchDetailBeen);
            } else {
                isList = false;
                ivRvStyle.setImageResource(R.mipmap.ic_list_library);
                rvSearch.setLayoutManager(gridLayoutManager);
                detailAdapter = new SearchDetailAdapter(1, searchDetailBeen);
            }
            rvSearch.scrollToPosition(rvPostion); //滑动到切换前的位置
            rvSearch.setAdapter(detailAdapter);

        } else if (i == R.id.fab_up) {
            rvSearch.scrollToPosition(0);

        } else if (i == R.id.fab_footprint) {
            showShortToast("足迹");

        } else if (i == R.id.tv_synthesize) {
            setPopToSynthesize();
            tvSalesVolume.setClickable(true);
            tvSynthesize.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));
            tvSalesVolume.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));
            tvPrice.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));

        } else if (i == R.id.tv_sales_volume) {
            tvSalesVolume.setClickable(false);
            tvSynthesize.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));
            tvSalesVolume.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));
            tvPrice.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));

        } else if (i == R.id.tv_price) {
            tvSalesVolume.setClickable(true);
            tvSynthesize.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));
            tvSalesVolume.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.black));
            tvPrice.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));

        } else if (i == R.id.tv_filtrate) {
            dlMenu.openDrawer(dlContent);

        } else {
        }
    }

    /**
     * 搜索详情模拟数据
     */
    private void setSearchDetailBean(String msg) {
        searchDetailBeen.clear();
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, msg + "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "的说法的是否打算放大师傅带伞啥地方的撒范德萨", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "相册V型从V型充值V型从v司法撒旦法师打发啥的服务", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "五色入围现场线程v的沙发斯蒂芬搜房网呃呃服务费", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "的收费的算法那的沙发斯蒂芬为从v从v小县城V型", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
        searchDetailBeen.add(new SearchDetailBean(R.mipmap.ic_timg_library, "大的范德萨发撒范德萨范德萨士大夫按时发斯蒂芬", 125.00, 21525, 99));
    }

    /**
     * 设置综合搜索弹窗选择
     */
    private void setPopToSynthesize() {
        View view = View.inflate(this, R.layout.pop_search_synthesize_item_library, null);
        tv_synthesize_sort = (TextView) view.findViewById(R.id.tv_synthesize_sort);
        tv_new_goods = (TextView) view.findViewById(R.id.tv_new_goods);
        tv_recommend_priority = (TextView) view.findViewById(R.id.tv_recommend_priority);

        drawable = ContextCompat.getDrawable(this, R.mipmap.ic_tick_library);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        handleLogic(view);
        popWindow = new CustomPopWindow.PopupWindowBuilder(this).setView(view).size(ScreenUtil.getScreenWidthPix(this),
                ScreenUtil.getScreenWidthPix(this) / 3).setOutsideTouchable(true).create().showAsDropDown(tvSynthesize, 0, 10);
        setSynthesize();
    }

    /**
     * 设置综合搜索key
     */
    private void setSynthesize() {
        if (isSynthesizeSort) {
            tv_synthesize_sort.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));
            tv_new_goods.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_recommend_priority.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_synthesize_sort.setCompoundDrawables(null, null, drawable, null);
            tv_new_goods.setCompoundDrawables(null, null, null, null);
            tv_recommend_priority.setCompoundDrawables(null, null, null, null);
            tv_synthesize_sort.setClickable(false);
            tv_new_goods.setClickable(true);
            tv_recommend_priority.setClickable(true);
            return;
        }
        if (isNewGoods) {
            tv_synthesize_sort.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_new_goods.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));
            tv_recommend_priority.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_new_goods.setCompoundDrawables(null, null, drawable, null);
            tv_synthesize_sort.setCompoundDrawables(null, null, null, null);
            tv_recommend_priority.setCompoundDrawables(null, null, null, null);
            tv_synthesize_sort.setClickable(true);
            tv_new_goods.setClickable(false);
            tv_recommend_priority.setClickable(true);
            return;
        }
        if (isRecommendPriority) {
            tv_synthesize_sort.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_new_goods.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.gary));
            tv_recommend_priority.setTextColor(ContextCompat.getColor(SearchDetailActivity.this, R.color.red));
            tv_recommend_priority.setCompoundDrawables(null, null, drawable, null);
            tv_synthesize_sort.setCompoundDrawables(null, null, null, null);
            tv_new_goods.setCompoundDrawables(null, null, null, null);
            tv_synthesize_sort.setClickable(true);
            tv_new_goods.setClickable(true);
            tv_recommend_priority.setClickable(false);
            return;
        }
    }

    private boolean isSynthesizeSort;
    private boolean isNewGoods;
    private boolean isRecommendPriority;

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null) {
                    popWindow.dissmiss();
                }
                int i = v.getId();
                if (i == R.id.tv_synthesize_sort) {
                    isSynthesizeSort = true;
                    isNewGoods = false;
                    isRecommendPriority = false;
                    tvSynthesize.setText("综合");
                    setSearchDetailBean("【综合】");

                } else if (i == R.id.tv_new_goods) {
                    isSynthesizeSort = false;
                    isNewGoods = true;
                    isRecommendPriority = false;
                    tvSynthesize.setText("新品");
                    setSearchDetailBean("【新品】");

                } else if (i == R.id.tv_recommend_priority) {
                    isSynthesizeSort = false;
                    isNewGoods = false;
                    isRecommendPriority = true;
                    tvSynthesize.setText("评论");
                    setSearchDetailBean("【评论】");

                } else {
                }
                detailAdapter.notifyDataSetChanged();
            }
        };
        contentView.findViewById(R.id.tv_synthesize_sort).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_new_goods).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_recommend_priority).setOnClickListener(listener);
    }
}
