package com.zmq.stock.bean;

/**
 * Created by Administrator on 2017/7/4.
 */

public class DirectPlayBean {
    private String time;
    private String msg;

    public DirectPlayBean(String time, String msg) {
        this.time = time;
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
