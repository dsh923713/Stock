package com.zmq.shopmall.fragmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.shopmall.R;
import com.zmq.shopmall.base.BaseFragment;

/**
 * 商品评论
 * Created by Administrator on 2017/6/7.
 */

public class GoodsCommentFragment extends BaseFragment {
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods_comment_library, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }
}
