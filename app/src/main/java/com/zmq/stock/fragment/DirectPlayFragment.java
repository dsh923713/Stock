package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.stock.R;
import com.zmq.stock.adapter.DirectPlayAdapter;
import com.zmq.stock.base.BaseFragment;
import com.zmq.stock.bean.DirectPlayBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/4.
 */

public class DirectPlayFragment extends BaseFragment {

    private static DirectPlayFragment directPlayFragment;

    @BindView(R.id.rv_direct_play)
    RecyclerView rvDirectPlay;//时间轴

    private List<DirectPlayBean> mData;

    /**
     * 单例模式
     *
     * @return
     */
    public static DirectPlayFragment getInstance() {
        if (directPlayFragment == null) {
            directPlayFragment = new DirectPlayFragment();
        }
        return directPlayFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_direct_play, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        getmData();
        rvDirectPlay.setLayoutManager(new LinearLayoutManager(activity));
        rvDirectPlay.setAdapter(new DirectPlayAdapter(mData));
    }

    private void getmData() {
        mData = new ArrayList<>();
        mData.add(new DirectPlayBean(System.currentTimeMillis() + "", "啥啥地方的撒范德萨发的是发生的发生的范德萨发生的发胜多负少的方式大幅度送达方式的方式发生的的方式的放大"));
        mData.add(new DirectPlayBean(System.currentTimeMillis() + "", "啥啥地方的撒范德萨发的是发生的发生的范德萨发生的发胜多负少的方式大幅度送达方式的方式发生的的方式的放大"));
        mData.add(new DirectPlayBean(System.currentTimeMillis() + "", "啥啥地方的撒范德萨发的是发生的发生的范德萨发生的发胜多负少的方式大幅度送达方式的方式发生的的方式的放大"));
        mData.add(new DirectPlayBean(System.currentTimeMillis() + "", "啥啥地方的撒范德萨发的是发生的发生的范德萨发生的发胜多负少的方式大幅度送达方式的方式发生的的方式的放大"));
        mData.add(new DirectPlayBean(System.currentTimeMillis() + "", "啥啥地方的撒范德萨发的是发生的发生的范德萨发生的发胜多负少的方式大幅度送达方式的方式发生的的方式的放大"));
    }

}
