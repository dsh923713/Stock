package com.zmq.stock.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zmq.stock.R;
import com.zmq.stock.bean.DirectPlayBean;
import com.zmq.stock.utils.DateUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class DirectPlayAdapter extends BaseQuickAdapter<DirectPlayBean, BaseViewHolder> {
    public DirectPlayAdapter(@Nullable List<DirectPlayBean> data) {
        super(R.layout.rv_item_direct_play, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DirectPlayBean item) {
        holder.setText(R.id.tv_time, DateUtil.toTime2(item.getTime())).setText(R.id.tv_content, item.getMsg());
        if (holder.getAdapterPosition() == mData.size() - 1) {
            holder.setVisible(R.id.tv_hint, true);
        }
    }
}
