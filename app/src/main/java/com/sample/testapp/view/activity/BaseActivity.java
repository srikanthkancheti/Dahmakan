package com.sample.testapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.testapp.network.RetrofitClient;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by srikanth on 28/10/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected RetrofitClient retrofitClient;
    protected CompositeSubscription subscriptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new CompositeSubscription();
        retrofitClient = RetrofitClient.getInstance(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriptions != null)
            subscriptions.unsubscribe();
    }

}
