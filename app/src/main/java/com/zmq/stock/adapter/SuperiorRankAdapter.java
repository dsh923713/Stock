package com.zmq.stock.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zmq.stock.R;
import com.zmq.stock.bean.SuperiorRankBean;
import com.zmq.stock.fragment.SuperiorRankFragment;
import com.zmq.stock.utils.DateUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class SuperiorRankAdapter extends BaseQuickAdapter<SuperiorRankBean, BaseViewHolder> {
    private int id;
    public SuperiorRankAdapter(@Nullable List<SuperiorRankBean> data,int id) {
        super(R.layout.rv_item_superior_rank, data);
        this.id = id;
    }

    @Override
    protected void convert(BaseViewHolder holder, SuperiorRankBean item) {
        holder.setImageResource(R.id.riv_icon, item.getImageId()).setText(R.id.tv_name, item.getName()).setText(R.id
                .tv_win_rate, mContext.getString(R.string.win_rate) + item.getWinRate() + "%").setText(R.id.tv_deal_num,
                mContext.getString(R.string.deal_num) + item.getDealNum() + "").setText(R.id.tv_max_loss, mContext.getString(R
                .string.max_loss) + item.getMaxLoss() + "").setText(R.id.tv_comprehensive_rate, mContext.getString(R.string
                .comprehensive_rate) + item.getComprehensiveRate() + "%");
        if (id == SuperiorRankFragment.SPUERIORID){ //加入时间
            holder.setText(R.id.tv_join_time, mContext.getString(R
                    .string.join_time) + DateUtil.toTime8(item.getTime()));
        }else { //购买时间
            holder.setText(R.id.tv_join_time, mContext.getString(R
                    .string.buy_time) + DateUtil.toTime8(item.getTime()));
        }
    }
}
