package com.zmq.stock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmq.stock.R;
import com.zmq.stock.activity.HomeActivity;
import com.zmq.stock.base.BaseFragment;

/**
 * 报单
 * Created by Administrator on 2017/6/22.
 */

public class TaxationFragment extends BaseFragment {
    private static TaxationFragment TaxationFragment;
    private HomeActivity mActivity;

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
        replaceFragment(R.id.fl_taxation_content, TaxationItemFragment.getInstance());//默认显示报单
        mActivity.rbTaxation.setChecked(true);//默认选中报单
    }

    /**
     * 重新父类的onAttach 获取当前的activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (HomeActivity) context;
    }
}
