package com.sample.testapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.testapp.R;
import com.sample.testapp.model.OrdersListVo;
import com.sample.testapp.view.activity.OrderDetailsActivity;

import java.util.ArrayList;

/**
 * Created by srikanth on 28/10/2017.
 */

public class OrdersPagerAdapter extends PagerAdapter {

    private final ArrayList<OrdersListVo> pagerItems;
    private Context context;
    private TextView orderIdTv, arrivesAtTv, paidWithTv;

    public OrdersPagerAdapter(Context context, ArrayList<OrdersListVo> pagerItems) {
        this.context = context;
        this.pagerItems = pagerItems;
    }

    @Override
    public int getCount() {
        return pagerItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.orders_pager_item, viewGroup, false);

        orderIdTv = (TextView) layout.findViewById(R.id.order_detail_id_textview);
        arrivesAtTv = (TextView) layout.findViewById(R.id.detail_arrives_at_textview);
        paidWithTv = (TextView) layout.findViewById(R.id.detail_paid_with_textview);

        orderIdTv.setText("#" + pagerItems.get(position).getOrderId());
        arrivesAtTv.setText(pagerItems.get(position).getTime());
        paidWithTv.setText(pagerItems.get(position).getPaidWith());

        viewGroup.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int position, Object view) {
        viewGroup.removeView((View) view);
    }
}
