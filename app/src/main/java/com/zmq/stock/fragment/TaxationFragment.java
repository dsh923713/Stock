package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.zmq.stock.R;
import com.zmq.stock.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 报单
 * Created by Administrator on 2017/6/22.
 */

public class TaxationFragment extends BaseFragment {
    private static TaxationFragment TaxationFragment;

    @BindView(R.id.rb_taxation)
    RadioButton rbTaxation; //报单
    @BindView(R.id.ll_taxation)
    LinearLayout llTaxation;//报单
    @BindView(R.id.rb_futures)
    RadioButton rbFutures;//期货
    @BindView(R.id.ll_futures)
    LinearLayout llFutures;//期货
    @BindView(R.id.fl_taxation_content)
    FrameLayout flTaxationContent; //fragment内容

    /**
     * 单例模式
     *
     * @return
     */
    public static TaxationFragment getInstance() {
        if (TaxationFragment == null) {
            TaxationFragment = new TaxationFragment();
        }
        return TaxationFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taxation, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        rbTaxation.setChecked(true);//默认选中报单
        replaceFragment(R.id.fl_taxation_content, TaxationItemFragment.getInstance());//默认显示报单
    }


    @OnClick({R.id.ll_taxation,R.id.ll_futures,R.id.rb_taxation,R.id.rb_futures})
    void onClick(View v){
        switch (v.getId()){
            case R.id.rb_taxation: //报单
            case R.id.ll_taxation:
                rbTaxation.setChecked(true);
                rbFutures.setChecked(false);
                rbTaxation.setTextColor(ContextCompat.getColor(activity,R.color.white));
                rbFutures.setTextColor(ContextCompat.getColor(activity,R.color.gary));
                replaceFragment(R.id.fl_taxation_content, TaxationItemFragment.getInstance());//fragment替换
                break;
            case R.id.rb_futures:  //期货
            case R.id.ll_futures:
                rbTaxation.setChecked(false);
                rbFutures.setChecked(true);
                rbTaxation.setTextColor(ContextCompat.getColor(activity,R.color.gary));
                rbFutures.setTextColor(ContextCompat.getColor(activity,R.color.white));
                replaceFragment(R.id.fl_taxation_content, TaxationFuturesFragment.getInstance()); //fragment替换
                break;
            default:
                break;
        }
    }
}
