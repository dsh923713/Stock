package com.zmq.stock.bean;

/**
 * Created by Administrator on 2017/6/29.
 */

public class TaxationFuturesBean {
    private String name;
    private long maxNew;
    private long upsAndDowns;
    private long todayIng;

    public TaxationFuturesBean(String name, long maxNew, long upsAndDowns, long todayIng) {
        this.name = name;
        this.maxNew = maxNew;
        this.upsAndDowns = upsAndDowns;
        this.todayIng = todayIng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMaxNew() {
        return maxNew;
    }

    public void setMaxNew(long maxNew) {
        this.maxNew = maxNew;
    }

    public long getUpsAndDowns() {
        return upsAndDowns;
    }

    public void setUpsAndDowns(long upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }

    public long getTodayIng() {
        return todayIng;
    }

    public void setTodayIng(long todayIng) {
        this.todayIng = todayIng;
    }
}
