package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.stock.R;
import com.zmq.stock.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/22.
 */

public class AlreadyBuyFragment extends BaseFragment {

    private static AlreadyBuyFragment alreadyBuyFragment;

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
        view = inflater.inflate(R.layout.fragment_already_buy, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

}
