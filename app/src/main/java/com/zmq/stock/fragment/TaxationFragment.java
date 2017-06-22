package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.stock.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/22.
 */

public class TaxationFragment extends BaseFragment {

    private static TaxationFragment TaxationFragment;

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
        return null;
    }

    @Override
    protected void initView(View view) {

    }

}
