package com.zmq.stock.activity;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.zmq.stock.R;
import com.zmq.stock.base.BaseActivity;
import com.zmq.stock.entity.TabEntity;
import com.zmq.stock.fragment.AlreadyBuyFragment;
import com.zmq.stock.fragment.MyselfFragment;
import com.zmq.stock.fragment.SuperiorRankFragment;
import com.zmq.stock.fragment.TaxationFragment;
import com.zmq.stock.fragment.TaxationFuturesFragment;
import com.zmq.stock.fragment.TaxationItemFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.ctl_bottom)
    CommonTabLayout ctlBottom;//底部导航栏
    @BindView(R.id.ll_top)
    LinearLayout llTop; //顶部状态栏
    @BindView(R.id.rb_taxation)
    public RadioButton rbTaxation; //报单
    @BindView(R.id.ll_taxation)
    LinearLayout llTaxation; //报单
    @BindView(R.id.rb_futures)
    RadioButton rbFutures; //期货
    @BindView(R.id.ll_futures)
    LinearLayout llFutures; //期货

    private String[] mTitles = {"高手排行", "报单", "已购买", "我的"};
    private int[] mIconUnselectIds = {R.mipmap.ic_rank_normal, R.mipmap.ic_taxation_normal, R.mipmap.ic_buy_normal, R.mipmap
            .ic_myself_normal}; //未选中图标
    private int[] mIconSelectIds = {R.mipmap.ic_rank, R.mipmap.ic_taxation, R.mipmap.ic_buy, R.mipmap.ic_myself}; //选中图标
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        setTitle("高手排行"); //默认显示高手排行
        //设置状态栏颜色
        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R.color.blue));
        //添加标题 已选图标 未选图标
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        //添加fragment
        mFragments.add(SuperiorRankFragment.getInstance());
        mFragments.add(TaxationFragment.getInstance());
        mFragments.add(AlreadyBuyFragment.getInstance());
        mFragments.add(MyselfFragment.getInstance());
        /** 关联数据支持同时切换fragments */
        ctlBottom.setTabData(mTabEntities, this, R.id.fl_content, mFragments);
        /**切换*/
        ctlBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        setTitle("高手排行");
                        tv_right.setVisibility(View.GONE);
                        toolbar.setVisibility(View.VISIBLE);
                        llTop.setVisibility(View.GONE);
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.blue));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.blue));
                        break;
                    case 1:
                        llTop.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.t4a4a4a));
                        break;
                    case 2:
                        setTitle("购买详情");
                        tv_right.setVisibility(View.GONE);
                        toolbar.setVisibility(View.VISIBLE);
                        llTop.setVisibility(View.GONE);
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.black));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                        break;
                    default:
                        setTitle("个人中心");
                        toolbar.setVisibility(View.VISIBLE);
                        llTop.setVisibility(View.GONE);
                        setRightIcon(R.mipmap.ic_set, "", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showShortToast("设置");
                            }
                        });
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.blue));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.blue));
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) { //重复点击执行

            }
        });
    }

    /**
     * 报单界面的点击事件
     *
     * @param v
     */
    @OnClick({R.id.ll_taxation, R.id.ll_futures, R.id.rb_taxation, R.id.rb_futures})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_taxation: //报单
            case R.id.ll_taxation:
                rbTaxation.setChecked(true);
                rbFutures.setChecked(false);
                rbTaxation.setTextColor(ContextCompat.getColor(this, R.color.white));
                rbFutures.setTextColor(ContextCompat.getColor(this, R.color.gary));
                replaceFragment(R.id.fl_taxation_content, TaxationItemFragment.getInstance());//fragment替换
                break;
            case R.id.rb_futures:  //期货
            case R.id.ll_futures:
                rbTaxation.setChecked(false);
                rbFutures.setChecked(true);
                rbTaxation.setTextColor(ContextCompat.getColor(this, R.color.gary));
                rbFutures.setTextColor(ContextCompat.getColor(this, R.color.white));
                replaceFragment(R.id.fl_taxation_content, TaxationFuturesFragment.getInstance()); //fragment替换
                break;
            default:
                break;
        }
    }
}
