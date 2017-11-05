package com.sample.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.testapp.R;
import com.sample.testapp.interfaces.CustomItemClickListener;
import com.sample.testapp.model.OrdersListVo;

import java.util.ArrayList;

/**
 * Created by srikanth on 28/10/2017.
 */

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.ViewHolder> {

    ArrayList<OrdersListVo> data;
    Context mContext;
    CustomItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_list_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.orderIdTv.setText(Html.fromHtml(data.get(position).getOrderId()));
        holder.arriveAtTv.setText(Html.fromHtml(data.get(position).getDate()));
        holder.paidWithTv.setText(Html.fromHtml(data.get(position).getPaidWith()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public OrdersListAdapter(Context mContext, ArrayList<OrdersListVo> data, CustomItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView orderIdTv, arriveAtTv, paidWithTv;

        ViewHolder(View view) {
            super(view);

            orderIdTv = view.findViewById(R.id.order_id_textview);
            arriveAtTv = view.findViewById(R.id.arrive_at_textview);
            paidWithTv = view.findViewById(R.id.paid_with_textview);

        }

    }
}
