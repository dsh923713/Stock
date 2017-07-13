package com.zmq.stock.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zmq.stock.R;
import com.zmq.stock.bean.InquireBean;
import com.zmq.stock.utils.DateUtil;

import java.util.List;

/**
 * 问答适配器
 * Created by Administrator on 2017/7/8.
 */

public class InquireAdapter extends BaseQuickAdapter<InquireBean, BaseViewHolder> {

    public InquireAdapter(@Nullable List<InquireBean> data) {
        super(R.layout.rv_item_inquire, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, InquireBean item) {
        holder.setText(R.id.tv_inquire_content, item.getInquireContent()).setText(R.id.tv_inquire_time, item.getInquireName()
                .replace(item.getInquireName().substring(3, 7), "****") + "　" + DateUtil.toTime10(item.getInquireTime())).setText
                (R.id.tv_answer_name, item.getAnswerName()).setText(R.id.tv_answer_time, DateUtil.toTime10(item.getAnswerTime()
        ) + "").setText(R.id.tv_answer_content, item.getAnswerContent());
        holder.setVisible(R.id.rl_answer, TextUtils.isEmpty(item.getAnswerContent()) ? false : true);
    }
}
