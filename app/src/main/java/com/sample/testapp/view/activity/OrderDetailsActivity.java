package com.sample.testapp.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sample.testapp.R;
import com.sample.testapp.adapter.OrdersPagerAdapter;
import com.sample.testapp.model.OrdersListVo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by srikanth on 28/10/2017.
 */

public class OrderDetailsActivity extends BaseActivity {

    private ArrayList<OrdersListVo> pagerItems;
    private ViewPager ordersViewPager;
    private int currentItem;
    private LinearLayout mPagerIndicatorLayout;
    private CircleIndicator mPagerIndicator;
    private ImageView mBackIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        pagerItems = (ArrayList<OrdersListVo>)getIntent().getSerializableExtra("pagerOrdersList");
        currentItem = getIntent().getIntExtra("pagerPosition", 0);

        initializeUi();
        setListeners();
        for (OrdersListVo ordersListVo : pagerItems) {
            try {
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(Long.valueOf(ordersListVo.getArrivesAtUtc()));
                String date = DateFormat.format("hh:mm a", cal).toString();
                ordersListVo.setTime(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ordersViewPager.setAdapter(new OrdersPagerAdapter(this, pagerItems));
        ordersViewPager.setCurrentItem(currentItem);
        mPagerIndicator.setViewPager(ordersViewPager);
    }

    private void initializeUi() {
        ordersViewPager = (ViewPager) findViewById(R.id.order_detail_viewpager);
        mPagerIndicator = (CircleIndicator) findViewById(R.id.indicator);
        mBackIcon = (ImageView) findViewById(R.id.order_details_back);
    }

    private void setListeners() {
        mBackIcon.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int id = view.getId();

            if (id == R.id.order_details_back) {
                onBackPressed();
            }
        }
    };
}
