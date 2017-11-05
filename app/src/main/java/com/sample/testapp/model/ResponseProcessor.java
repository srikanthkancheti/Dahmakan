package com.sample.testapp.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by srikanth on 03/10/2017.
 */

public class ResponseProcessor {

    private static ResponseProcessor objInstance;
    private OrdersResponseVO ordersResponse;

    public static ResponseProcessor getObjInstance() {
        if (objInstance == null) {
            objInstance = new ResponseProcessor();
        }
        return objInstance;
    }

    public <T> void doServiceResponseToVo(String result, Class<T> responseVo) {

        if (responseVo.isInstance(new OrdersResponseVO())) {
            try {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(result, JsonElement.class);
                setOrdersResponse(new Gson().fromJson(element.toString(), OrdersResponseVO.class));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setOrdersResponse(OrdersResponseVO ordersResponse) {
        this.ordersResponse = ordersResponse;
    }

    public OrdersResponseVO getOrdersResponse() {
        return ordersResponse;
    }
}
