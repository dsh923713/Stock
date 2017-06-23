package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zmq.stock.R;
import com.zmq.stock.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/22.
 */

public class MyselfFragment extends BaseFragment {
    private static MyselfFragment myselfFragment;

    @BindView(R.id.riv_icon)
    RoundedImageView rivIcon; //头像
    @BindView(R.id.tv_name)
    TextView tvName;  //昵称
    @BindView(R.id.tv_phone)
    TextView tvPhone; //绑定手机
    @BindView(R.id.tv_wallet)
    TextView tvWallet; //钱包金额
    @BindView(R.id.rl_wallet)
    RelativeLayout rlWallet; //钱包
    @BindView(R.id.tv_integral)
    TextView tvIntegral; //积分数目
    @BindView(R.id.rl_integral)
    RelativeLayout rlIntegral; //积分
    @BindView(R.id.stv_my_documentary)
    SuperTextView stvMyDocumentary; //我的跟单
    @BindView(R.id.stv_my_taxation)
    SuperTextView stvMyTaxation; //我的报单
    @BindView(R.id.stv_prize)
    SuperTextView stvPrize; //推荐有奖
    @BindView(R.id.stv_integral_mall)
    SuperTextView stvIntegralMall; //积分商城
    @BindView(R.id.stv_service)
    SuperTextView stvService; //服务中心

    /**
     * 单例模式
     *
     * @return
     */
    public static MyselfFragment getInstance() {
        if (myselfFragment == null) {
            myselfFragment = new MyselfFragment();
        }
        return myselfFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myself, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @OnClick({R.id.riv_icon, R.id.rl_wallet, R.id.rl_integral, R.id.stv_my_documentary, R.id.stv_my_taxation, R.id.stv_prize, R
            .id.stv_integral_mall, R.id.stv_service})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.riv_icon: //头像
                break;
            case R.id.rl_wallet: //钱包
                break;
            case R.id.rl_integral: //积分
                break;
            case R.id.stv_my_documentary: //我的跟单
                break;
            case R.id.stv_my_taxation: //我的报单
                break;
            case R.id.stv_prize: //推荐有奖
                break;
            case R.id.stv_integral_mall: //积分商城
                break;
            case R.id.stv_service: //服务中心
                break;
            default:
                break;
        }
    }
}
