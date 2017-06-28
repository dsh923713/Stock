package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.stock.R;
import com.zmq.stock.base.BaseFragment;

/**
 * 期货
 * Created by Administrator on 2017/6/22.
 */

public class TaxationFuturesFragment extends BaseFragment {

    private static TaxationFuturesFragment taxationFuturesFragment;

    /**
     * 单例模式
     *
     * @return
     */
    public static TaxationFuturesFragment getInstance() {
        if (taxationFuturesFragment == null) {
            taxationFuturesFragment = new TaxationFuturesFragment();
        }
        return taxationFuturesFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taxation_futures, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

}
