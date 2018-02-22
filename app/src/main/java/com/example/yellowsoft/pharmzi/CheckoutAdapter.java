package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by info on 20-02-2018.
 */

public class CheckoutAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Products> products;

    public CheckoutAdapter(Context context, ArrayList<Products> products) {
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.products.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View item_view = this.inflater.inflate(R.layout.checkout_screen_items, null);
        TextView quantity = (TextView) item_view.findViewById(R.id.quantity);
        TextView price = (TextView) item_view.findViewById(R.id.price);
        ((TextView) item_view.findViewById(R.id.pro_title)).setText(((Products) this.products.get(i)).title);
        quantity.setText(String.valueOf(((Products) this.products.get(i)).cart_quantity));
        price.setText(((Products) this.products.get(i)).price + " KD ");
        Log.e("size", String.valueOf(this.products.size()));
        return item_view;
    }
}

