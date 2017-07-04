package com.zmq.stock.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zmq.stock.R;
import com.zmq.stock.bean.TaxationFuturesBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class TaxationFuturesAdapter extends BaseQuickAdapter<TaxationFuturesBean, BaseViewHolder> {
    public TaxationFuturesAdapter(@Nullable List<TaxationFuturesBean> data) {
        super(R.layout.rv_item_taxation_futures, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, TaxationFuturesBean item) {
        holder.setText(R.id.tv_taxation_name, item.getName()).setText(R.id.tv_taxation_new, item.getMaxNew() + "").setText(R.id
                .tv_ups_and_downs, item.getUpsAndDowns() + "").setText(R.id.tv_today_ing, item.getTodayIng() + "");
        holder.setTextColor(R.id.tv_taxation_new, item.getUpsAndDowns() > 0 ? ContextCompat.getColor(mContext, R.color.red) :
                ContextCompat.getColor(mContext, R.color.t29d454));
        holder.setTextColor(R.id.tv_ups_and_downs, item.getUpsAndDowns() > 0 ? ContextCompat.getColor(mContext, R.color.red) :
                ContextCompat.getColor(mContext, R.color.t29d454));
    }
}
