package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 23/1/18.
 */

public class PharmcyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Pharmacies> pharmacies;
    PharmcyActivity activity;
    Categories categories;


    public PharmcyAdapter(Context context,ArrayList<Pharmacies> pharmacies,PharmcyActivity activity,Categories categories){
        this.context = context;
        this.pharmacies = pharmacies;
        inflater = LayoutInflater.from(context);
        this.activity = activity;
        this.categories = categories;
    }
    @Override
    public int getCount() {
        return pharmacies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.home_screen_items,null);
        LinearLayout list = (LinearLayout) item_view.findViewById(R.id.list);
        ImageView phar_image = (ImageView) item_view.findViewById(R.id.phar_image);
        TextView title = (TextView) item_view.findViewById(R.id.title);
        TextView payment_title= (TextView) item_view.findViewById(R.id.payment_title);
        TextView time = (TextView) item_view.findViewById(R.id.time);
        TextView minimum = (TextView) item_view.findViewById(R.id.minimum);
        TextView delivery = (TextView) item_view.findViewById(R.id.delivery);
        Picasso.with(context).load(pharmacies.get(position).image).into(phar_image);
        title.setText(pharmacies.get(position).title);
        payment_title.setText(pharmacies.get(position).payments.get(0).title);
        time.setText(pharmacies.get(position).time);
        minimum.setText(pharmacies.get(position).minimum);
        delivery.setText(pharmacies.get(position).delivery_charges + " KD ");
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductsListActivity.class);
                intent.putExtra("header","0");
                intent.putExtra("catid",categories.id);
                intent.putExtra("cat_title",categories.title);
                intent.putExtra("cat_title_ar",categories.title_ar);
                intent.putExtra("phar_id",pharmacies.get(position).id);
                Log.e("phaaaaid",pharmacies.get(position).id);
                context.startActivity(intent);
            }
        });
        return item_view;
    }
}
