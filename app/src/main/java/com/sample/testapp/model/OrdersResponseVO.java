package com.sample.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class OrdersResponseVO implements Serializable{

    @SerializedName("orders")
    @Expose
    private ArrayList<OrdersListVo> ordersListVos = new ArrayList<>();

    public ArrayList<OrdersListVo> getOrdersListVos() {
        return ordersListVos;
    }

    public void setNotificationsListVos(ArrayList<OrdersListVo> ordersListVos) {
        this.ordersListVos = ordersListVos;
    }
}
