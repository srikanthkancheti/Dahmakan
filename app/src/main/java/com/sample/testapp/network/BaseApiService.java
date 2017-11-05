package com.sample.testapp.network;


import com.sample.testapp.model.OrdersResponseVO;

import retrofit2.http.GET;
import rx.Observable;


public interface BaseApiService {

    @GET("orders/")
    Observable<OrdersResponseVO> getData();

}
