package com.zmq.stock.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmq.stock.R;

/**
 * Created by Administrator on 2017/6/23.
 */

public class HeadAndFootView {
    private static View headView;
    private static View footView;
    private static TextView tvHead;
    private static ImageView ivHead;
    private static TextView tvFoot;
    private static ImageView ivFoot;

    public static View getHeadView(Context context) {
        if (headView == null) {
            headView = View.inflate(context, R.layout.rv_head_refresh, null);
            tvHead = (TextView) headView.findViewById(R.id.tv_head_name);
            ivHead = (ImageView) headView.findViewById(R.id.iv_head);

        }
        return headView;
    }
    public static View getFootView(Context context) {
        if (footView == null) {
            footView = View.inflate(context, R.layout.rv_foot_loadmore, null);
            tvFoot = (TextView) footView.findViewById(R.id.tv_foot_name);
            ivFoot = (ImageView) footView.findViewById(R.id.iv_foot);
        }
        return footView;
    }

    public static TextView getTvHead() {
        return tvHead;
    }

    public static ImageView getIvHead() {
        return ivHead;
    }

    public static TextView getTvFoot() {
        return tvFoot;
    }

    public static ImageView getIvFoot() {
        return ivFoot;
    }
}
