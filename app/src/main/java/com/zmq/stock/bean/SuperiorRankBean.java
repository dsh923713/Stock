package com.zmq.stock.bean;

/**
 * Created by Administrator on 2017/6/22.
 */

public class SuperiorRankBean {
    private int imageId;
    private String name;
    private int dealNum;
    private double winRate;
    private double maxLoss;
    private double comprehensiveRate;
    private long time;

    public SuperiorRankBean(int imageId, String name, int dealNum, double winRate, double maxLoss, double comprehensiveRate,
                            long time) {
        this.imageId = imageId;
        this.name = name;
        this.dealNum = dealNum;
        this.winRate = winRate;
        this.maxLoss = maxLoss;
        this.comprehensiveRate = comprehensiveRate;
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDealNum() {
        return dealNum;
    }

    public void setDealNum(int dealNum) {
        this.dealNum = dealNum;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public double getMaxLoss() {
        return maxLoss;
    }

    public void setMaxLoss(double maxLoss) {
        this.maxLoss = maxLoss;
    }

    public double getComprehensiveRate() {
        return comprehensiveRate;
    }

    public void setComprehensiveRate(double comprehensiveRate) {
        this.comprehensiveRate = comprehensiveRate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
