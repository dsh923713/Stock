package com.zmq.stock.bean;

/**
 * Created by Administrator on 2017/7/8.
 */

public class InquireBean {
    private String inquireContent;
    private String inquireName;
    private long inquireTime;
    private String answerName;
    private long answerTime;
    private String answerContent;

    public InquireBean(String inquireContent, String inquireName, long inquireTime, String answerName, long answerTime, String
            answerContent) {
        this.inquireContent = inquireContent;
        this.inquireName = inquireName;
        this.inquireTime = inquireTime;
        this.answerName = answerName;
        this.answerTime = answerTime;
        this.answerContent = answerContent;
    }

    public String getInquireContent() {
        return inquireContent;
    }

    public void setInquireContent(String inquireContent) {
        this.inquireContent = inquireContent;
    }

    public String getInquireName() {
        return inquireName;
    }

    public void setInquireName(String inquireName) {
        this.inquireName = inquireName;
    }

    public long getInquireTime() {
        return inquireTime;
    }

    public void setInquireTime(long inquireTime) {
        this.inquireTime = inquireTime;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(long answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
