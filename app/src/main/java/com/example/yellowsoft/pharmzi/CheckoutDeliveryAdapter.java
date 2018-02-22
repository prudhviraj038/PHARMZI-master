package com.example.yellowsoft.pharmzi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by info on 20-02-2018.
 */

public class CheckoutDeliveryAdapter extends BaseAdapter {
    CheckoutPayment activity;
    Context context;
    LayoutInflater inflater;
    ArrayList<Products> products;

    public CheckoutDeliveryAdapter(Context context, ArrayList<Products> products, CheckoutPayment activity) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.products = products;
        this.activity = activity;
    }

    public int getCount() {
        return this.products.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View item_view = this.inflater.inflate(R.layout.payment_order_items, null);
        ImageView product_image = (ImageView) item_view.findViewById(R.id.product_image);
        TextView pro_title = (TextView) item_view.findViewById(R.id.pro_title);
        TextView phar_title = (TextView) item_view.findViewById(R.id.phar_title);
        TextView price = (TextView) item_view.findViewById(R.id.price);
        ImageView remove = (ImageView) item_view.findViewById(R.id.remove);
        TextView area_title = (TextView) item_view.findViewById(R.id.area_title);
        TextView block = (TextView) item_view.findViewById(R.id.block);
        TextView street = (TextView) item_view.findViewById(R.id.street);
        TextView jaddah = (TextView) item_view.findViewById(R.id.jaddah);
        TextView building = (TextView) item_view.findViewById(R.id.building);
        TextView floor = (TextView) item_view.findViewById(R.id.floor);
        TextView apartment = (TextView) item_view.findViewById(R.id.apartment);
        TextView extra_details = (TextView) item_view.findViewById(R.id.extra_details);
        TextView number = (TextView) item_view.findViewById(R.id.number);
        LinearLayout date = (LinearLayout) item_view.findViewById(R.id.date);
        ImageView select_date = (ImageView) item_view.findViewById(R.id.select_date);
        final TextView date_option = (TextView) item_view.findViewById(R.id.date_option);
        LinearLayout time = (LinearLayout) item_view.findViewById(R.id.time);
        ImageView select_time = (ImageView) item_view.findViewById(R.id.select_time);
       final TextView time_option = (TextView) item_view.findViewById(R.id.time_option);
        TextView message = (TextView) item_view.findViewById(R.id.message);
        LinearLayout address = (LinearLayout) item_view.findViewById(R.id.address);
        Log.e("productssize", String.valueOf(Session.GetCartProducts(this.context).size()));
        Picasso.with(this.context).load(((Products.Images) ((Products) this.products.get(i)).images.get(0)).image).into(product_image);
        pro_title.setText(((Products) this.products.get(i)).title);
        phar_title.setText(((Products.Pharmacies) ((Products) this.products.get(i)).pharmacies.get(0)).title);
        price.setText(((Products) this.products.get(i)).price + " KD ");
        area_title.setText(Session.GetAreaTitle(this.context));
        if (Session.GetAddressType(this.context).equals("House")) {
            block.setText(this.activity.block);
            street.setText(this.activity.street);
            jaddah.setText(this.activity.jaddah);
            building.setText(this.activity.house);
            extra_details.setText(this.activity.extradetails);
            number.setText(this.activity.phone);
            message.setText(this.activity.msg);
            address.setVisibility(View.GONE);
        } else if (Session.GetAddressType(this.context).equals("Apartment")) {
            block.setText(this.activity.b_block);
            Log.e("bblock", this.activity.b_block);
            street.setText(this.activity.b_street);
            jaddah.setText(this.activity.b_jaddah);
            building.setText(this.activity.b_house);
            extra_details.setText(this.activity.b_details);
            number.setText(this.activity.b_phone);
            floor.setText(this.activity.b_floor);
            apartment.setText(this.activity.b_apartment);
            message.setText(this.activity.msg);
            address.setVisibility(View.GONE);
        } else if (Session.GetAddressType(this.context).equals("Hospital")) {
            block.setText(this.activity.h_block);
            street.setText(this.activity.h_street);
            jaddah.setText(this.activity.h_jaddah);
            building.setText(this.activity.h_house);
            extra_details.setText(this.activity.h_details);
            number.setText(this.activity.h_phone);
            floor.setText(this.activity.h_floor);
            apartment.setText(this.activity.h_apartment);
            message.setText(this.activity.msg);
            address.setVisibility(View.GONE);
        }
        this.activity.pharmcy_title.setText("Delivery Charges  " + ((Products.Pharmacies) ((Products) this.products.get(i)).pharmacies.get(0)).title);
        date_option.setHint("Select Date");
        time_option.setHint("Select Time");
        final int i2 = i;
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CheckoutDeliveryAdapter.this.activity.remove_cart_data(i2);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {

            class C03481 implements DatePickerDialog.OnDateSetListener {
                C03481() {
                }

                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    date_option.setText(selectedday + "-" + (selectedmonth + 1) + "-" + selectedyear);
                    CheckoutDeliveryAdapter.this.activity.date = date_option.getText().toString();
                }
            }

            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(CheckoutDeliveryAdapter.this.context, new C03481(), mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        select_date.setOnClickListener(new View.OnClickListener() {

            class C03501 implements DatePickerDialog.OnDateSetListener {
                C03501() {
                }

                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    date_option.setText(selectedday + "-" + (selectedmonth + 1) + "-" + selectedyear);
                    CheckoutDeliveryAdapter.this.activity.date = date_option.getText().toString();
                }
            }

            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(CheckoutDeliveryAdapter.this.context, new C03501(), mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {

            class C03521 implements TimePickerDialog.OnTimeSetListener {
                C03521() {
                }

                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    boolean isPM;
                    int i = 12;
                    if (selectedHour >= 12) {
                        isPM = true;
                    } else {
                        isPM = false;
                    }
                    TextView textView;
                    String str = "%02d:%02d %s";
                    Object[] objArr = new Object[3];
                    if (!(selectedHour == 12 || selectedHour == 0)) {
                        i = selectedHour % 12;
                    }
                    objArr[0] = Integer.valueOf(i);
                    objArr[1] = Integer.valueOf(selectedMinute);
                    objArr[2] = isPM ? "PM" : "AM";
                    time_option.setText(String.format(str, objArr));
                    CheckoutDeliveryAdapter.this.activity.time = time_option.getText().toString();
                }
            }

            @RequiresApi(api = 24)
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                TimePickerDialog mTimePicker = new TimePickerDialog(CheckoutDeliveryAdapter.this.context, new C03521(), mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {

            class C03541 implements TimePickerDialog.OnTimeSetListener {
                C03541() {
                }

                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    boolean isPM;
                    int i = 12;
                    if (selectedHour >= 12) {
                        isPM = true;
                    } else {
                        isPM = false;
                    }
                    String str = "%02d:%02d %s";
                    Object[] objArr = new Object[3];
                    if (!(selectedHour == 12 || selectedHour == 0)) {
                        i = selectedHour % 12;
                    }
                    objArr[0] = Integer.valueOf(i);
                    objArr[1] = Integer.valueOf(selectedMinute);
                    objArr[2] = isPM ? "PM" : "AM";
                    time_option.setText(String.format(str, objArr));
                    CheckoutDeliveryAdapter.this.activity.time = time_option.getText().toString();
                }
            }

            @RequiresApi(api = 24)
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                TimePickerDialog mTimePicker = new TimePickerDialog(CheckoutDeliveryAdapter.this.context, new C03541(), mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        return item_view;
    }
}

