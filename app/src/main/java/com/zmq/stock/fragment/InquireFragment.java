package com.zmq.stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zmq.stock.R;
import com.zmq.stock.adapter.InquireAdapter;
import com.zmq.stock.base.BaseFragment;
import com.zmq.stock.bean.InquireBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/4.
 */

public class InquireFragment extends BaseFragment {
    @BindView(R.id.et_inquire)
    EditText etInquire; //输入提出的问题
    @BindView(R.id.tv_inquire)
    TextView tvInquire; //提问
    @BindView(R.id.tv_not_inquire)
    TextView tvNotInquire; //无提问显示
    @BindView(R.id.rv_inquire)
    RecyclerView rvInquire; //问答列表
    @BindView(R.id.nsv_inquire)
    NestedScrollView nsvInquire;


    private List<InquireBean> mData;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inquire, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        getmData();
        rvInquire.setLayoutManager(new LinearLayoutManager(activity));
        rvInquire.setAdapter(new InquireAdapter(mData));
    }

    private void getmData() {
        mData = new ArrayList<>();
        mData.add(new InquireBean("东山岛", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(), "都是第三方是"));
        mData.add(new InquireBean("的说法的是否", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(), ""));
        mData.add(new InquireBean("稳定擦双休的", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(),
                "时的发的是发生的"));
        mData.add(new InquireBean("同事的方式发送", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(),
                "出现的付费点晚上"));
        mData.add(new InquireBean("颠三倒四", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(), "撒范德萨发"));
        mData.add(new InquireBean("温度场", "15466523535", System.currentTimeMillis(), "阿达是", System.currentTimeMillis(),
                "士大夫大师傅的撒范德萨范德萨范德萨范德萨发的顺丰三分撒的发生大范德萨范德萨范德萨发的顺丰阿斯蒂芬第三方"));
    }
}
