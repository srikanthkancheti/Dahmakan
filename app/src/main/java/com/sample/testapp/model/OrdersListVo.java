package com.sample.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class OrdersListVo implements Serializable{

    @SerializedName("order_id")
    @Expose
    private String orderId;

    @SerializedName("arrives_at_utc")
    @Expose
    private String arrivesAtUtc;

    @SerializedName("paid_with")
    @Expose
    private String paidWith;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("time")
    @Expose
    private String time;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getArrivesAtUtc() {
        return arrivesAtUtc;
    }

    public void setArrivesAtUtc(String arrivesAtUtc) {
            this.arrivesAtUtc = arrivesAtUtc;
    }

    public String getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
