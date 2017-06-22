package com.zmq.stock.activity;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

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

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.ctl_bottom)
    CommonTabLayout ctlBottom;//底部导航栏

    private String[] mTitles = {"高手排行", "报单", "已购买", "我的"};
    private int[] mIconUnselectIds = {R.mipmap.ic_rank_normal, R.mipmap.ic_taxation_normal, R.mipmap.ic_buy_normal, R.mipmap
            .ic_myself_normal};
    private int[] mIconSelectIds = {R.mipmap.ic_rank, R.mipmap.ic_taxation, R.mipmap.ic_buy, R.mipmap.ic_myself};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        setTitle("高手排行");
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
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.blue));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.blue));
                        break;
                    case 1:
                        setTitle("报单中心");
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.colorAccent));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent));
                        break;
                    case 2:
                        setTitle("购买详情");
                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, ContextCompat.getColor(HomeActivity.this, R
                                .color.black));
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                        break;
                    default:
                        setTitle("个人中心");
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
            public void onTabReselect(int position) {

            }
        });
    }
}
