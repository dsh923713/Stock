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

public class MyselfFragment extends BaseFragment {

    private static MyselfFragment myselfFragment;

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

}
