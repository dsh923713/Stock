package com.zmq.stock.base;

/**
 * Created by Administrator on 2017/5/24.
 */

public interface RequestResult {
    void onSuccess(String result, String requestCode); //请求数据成功回调

    void onFailure(String result, String requestCode); //请求数据失败回调
}
