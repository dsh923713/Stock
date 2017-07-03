package com.zmq.stock.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmq.stock.R;

/**
 * Created by Administrator on 2017/6/23.
 */

public class HeadAndFootView {
    private View headView;
    private View footView;
    private TextView tvHead;
    private ImageView ivHead;
    private TextView tvFoot;
    private ImageView ivFoot;

    public View getHeadView(Context context) {
        if (headView == null) {
            headView = LayoutInflater.from(context).inflate(R.layout.rv_head_refresh, null);
            tvHead = (TextView) headView.findViewById(R.id.tv_head_name);
            ivHead = (ImageView) headView.findViewById(R.id.iv_head);

        }
        return headView;
    }

    public View getFootView(Context context) {
        if (footView == null) {
            footView = LayoutInflater.from(context).inflate(R.layout.rv_foot_loadmore, null);
            tvFoot = (TextView) footView.findViewById(R.id.tv_foot_name);
            ivFoot = (ImageView) footView.findViewById(R.id.iv_foot);
        }
        return footView;
    }

    public TextView getTvHead() {
        return tvHead;
    }

    public ImageView getIvHead() {
        return ivHead;
    }

    public TextView getTvFoot() {
        return tvFoot;
    }

    public ImageView getIvFoot() {
        return ivFoot;
    }
}
