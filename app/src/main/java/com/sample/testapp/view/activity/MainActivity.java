package com.sample.testapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.sample.testapp.R;
import com.sample.testapp.adapter.OrdersListAdapter;
import com.sample.testapp.interfaces.CustomItemClickListener;
import com.sample.testapp.model.OrdersListVo;
import com.sample.testapp.model.OrdersResponseVO;
import com.sample.testapp.network.BaseApiService;
import com.sample.testapp.network.DownloadOrdersService;
import com.sample.testapp.network.RetrofitClient;
import com.sample.testapp.view.dialog.BaseDialog;
import com.sample.testapp.view.dialog.ServiceErrorDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import rx.Observer;

public class MainActivity extends BaseActivity {

    private DownloadOrdersService downloadOrdersService;
    private ArrayList<OrdersListVo> ordersList = new ArrayList<>();
    private ArrayList<OrdersListVo> pagerItems;
    private BaseApiService baseApiService;
    RecyclerView ordersRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private OrdersListAdapter ordersListAdapter;
    private int selectedPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseApiService = RetrofitClient.getInstance(this).create(BaseApiService.class);

        getOrdersFromService();

    }

    private void getOrdersFromService() {

        subscriptions.add(
                baseApiService.getData()
                        .compose(retrofitClient.schedulersTransformer())
                        .subscribe(new Observer<OrdersResponseVO>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                final ServiceErrorDialog serviceErrorDialog = new ServiceErrorDialog(MainActivity.this);
                                serviceErrorDialog.show("Error", "Cancel", "Report", new BaseDialog.OnClickDialogButton() {
                                            @Override
                                            public void onClick(BaseDialog dialog, View view, Object data) {
                                                finish();
                                            }
                                        },
                                        new BaseDialog.OnClickDialogButton() {
                                            @Override
                                            public void onClick(BaseDialog dialog, View view, Object data) {
                                                finish();
                                                Toast.makeText(MainActivity.this, "Handle report issue", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                            @Override
                            public void onNext(OrdersResponseVO ordersResponseVO) {
                                ordersList = ordersResponseVO.getOrdersListVos();
                                for (OrdersListVo ordersListVo : ordersList) {
                                    try {
                                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                                        cal.setTimeInMillis(Long.valueOf(ordersListVo.getArrivesAtUtc()));
                                        String date = DateFormat.format(" dd MMM yyyy", cal).toString();
                                        ordersListVo.setDate(date);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                populateOrdersList();
                            }
                        }));
    }

    private void populateOrdersList() {
        ordersRecyclerView = (RecyclerView) findViewById(R.id.orders_recyclerview);
        ordersRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(mLinearLayoutManager);

        ordersListAdapter = new OrdersListAdapter(MainActivity.this, ordersList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                pagerItems = new ArrayList<>();

                if (position < 5 && ordersList.size() > 5) {
                    for (int i = 0; i < 5; i++)
                        pagerItems.add(ordersList.get(i));
                    selectedPosition = position;
                } else if (position < 5 && ordersList.size() < 5) {
                    for (int i = 0; i < ordersList.size(); i++)
                        pagerItems.add(ordersList.get(i));
                    selectedPosition = position;
                } else if (position > 4 && ordersList.size() > 5) {
                    for (int i = position - 2; i < position; i++) {
                        pagerItems.add(ordersList.get(i));
                    }
                    if (position + 1 == ordersList.size()) {
                        for (int i = position; i < position + 1; i++) {
                            pagerItems.add(ordersList.get(i));
                        }
                    } else if (position + 2 >= ordersList.size()){
                        for (int i = position; i < position +2; i++) {
                            pagerItems.add(ordersList.get(i));
                        }
                    }
                    selectedPosition = 2;
                }

                openOrderDetailActivity();
            }
        });
        ordersRecyclerView.setAdapter(ordersListAdapter);
    }

    private void openOrderDetailActivity() {
        Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
        intent.putExtra("pagerOrdersList", (ArrayList<OrdersListVo>) pagerItems);
        intent.putExtra("pagerPosition", selectedPosition);
        startActivity(intent);
    }
}
