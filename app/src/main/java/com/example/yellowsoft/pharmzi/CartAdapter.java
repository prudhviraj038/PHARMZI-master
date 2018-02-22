package com.example.yellowsoft.pharmzi;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.yellowsoft.pharmzi.Products.Images;
import com.example.yellowsoft.pharmzi.Products.Pharmacies;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;

public class CartAdapter extends Adapter<CartAdapter.MyViewHolder> {
    CartFragment cartFragment;
    private Context context;
    ArrayList<Products> products;

    public class POJO {
        boolean activityCardEmptyViewVisibility;
        boolean activityCardViewVisibility;
        String activityEmptyText;
        String activityValue;
        String activityValueText;
    }

    public class MyViewHolder extends ViewHolder implements OnLongClickListener, OnClickListener {
        public TextView area;
        public ImageView clock;
        public ImageView close;
        public LinearLayout date;
        public ImageView date_cal;
        public TextView date_option;
        public TextView full_name;
        public ImageView img;
        public LinearLayout item_ll;
        public TextView minus;
        public TextView phar_title;
        public TextView plus;
        public TextView price;
        public TextView qty;
        public LinearLayout time;
        public TextView time_option;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.cart_product_title);
            this.price = (TextView) view.findViewById(R.id.cart_product_price);
            this.qty = (TextView) view.findViewById(R.id.cart_product_qty);
            this.title = (TextView) view.findViewById(R.id.cart_product_title);
            this.img = (ImageView) view.findViewById(R.id.cart_product_img);
            this.close = (ImageView) view.findViewById(R.id.cart_close_img);
            this.time = (LinearLayout) view.findViewById(R.id.time);
            this.time_option = (TextView) view.findViewById(R.id.time_option);
            this.clock = (ImageView) view.findViewById(R.id.clock);
            this.area = (TextView) view.findViewById(R.id.area);
            this.plus = (TextView) view.findViewById(R.id.plus);
            this.minus = (TextView) view.findViewById(R.id.minus);
            this.date_option = (TextView) view.findViewById(R.id.date_option);
            this.date_cal = (ImageView) view.findViewById(R.id.date_cal);
            this.phar_title = (TextView) view.findViewById(R.id.phar_name);
            this.date = (LinearLayout) view.findViewById(R.id.date);
            view.setOnClickListener(this);
        }

        public boolean onLongClick(View view) {
            this.title.setBackgroundColor(Color.parseColor("black"));
            return false;
        }

        public void onClick(View view) {
        }
    }

    public CartAdapter(Context context, ArrayList<Products> products, CartFragment cartFragment) {
        this.context = context;
        this.cartFragment = cartFragment;
        this.products = products;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item2, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            if (Session.GetLang(this.context).equals(Session.Words_en)) {
                holder.title.setText(((Products) this.products.get(position)).title);
                Session.SetProductTitle(this.context, ((Products) this.products.get(position)).title);
            } else {
                holder.title.setText(((Products) this.products.get(position)).title_ar);
            }
            holder.price.setText(((Products) this.products.get(position)).price + "KD");
            Picasso.with(this.context).load(String.valueOf(((Images) ((Products) this.products.get(position)).images.get(0)).image)).into(holder.img);
            holder.phar_title.setText(((Pharmacies) ((Products) this.products.get(position)).pharmacies.get(0)).title);
            Log.e(Session.title, ( ((Pharmacies) ((Products) this.products.get(position)).pharmacies.get(0)).areas.get(0)).title);
            holder.area.setText(Session.GetAreaTitle(this.context));
            holder.close.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CartAdapter.this.cartFragment.remove_cart_data(position);
                }
            });
            holder.date_option.setHint("Select Date");
            holder.time_option.setHint("Select Time");
            holder.plus.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Log.e("plus", String.valueOf(((Products) CartAdapter.this.products.get(position)).cart_quantity));
                    if (((Products) CartAdapter.this.products.get(position)).cart_quantity < 10) {
                        Products products = (Products) CartAdapter.this.products.get(position);
                        products.cart_quantity++;
                        holder.qty.setText(String.valueOf(((Products) CartAdapter.this.products.get(position)).cart_quantity));
                        CartAdapter.this.cartFragment.calculate_total_price();
                    }
                }
            });
            holder.minus.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Log.e("minus", String.valueOf(((Products) CartAdapter.this.products.get(position)).cart_quantity));
                    if (((Products) CartAdapter.this.products.get(position)).cart_quantity > 1) {
                        Products products = (Products) CartAdapter.this.products.get(position);
                        products.cart_quantity--;
                        holder.qty.setText(String.valueOf(((Products) CartAdapter.this.products.get(position)).cart_quantity));
                        CartAdapter.this.cartFragment.calculate_total_price();
                    }
                }
            });
            holder.date.setOnClickListener(new OnClickListener() {

                class C03121 implements OnDateSetListener {
                    C03121() {
                    }

                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        holder.date_option.setText(selectedday + "-" + (selectedmonth + 1) + "-" + selectedyear);
                        CartAdapter.this.cartFragment.date = holder.date_option.getText().toString();
                    }
                }

                public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    DatePickerDialog mDatePicker = new DatePickerDialog(CartAdapter.this.context, new C03121(), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();
                }
            });
            holder.date_cal.setOnClickListener(new OnClickListener() {

                class C03141 implements OnDateSetListener {
                    C03141() {
                    }

                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        holder.date_option.setText(selectedday + "-" + (selectedmonth + 1) + "-" + selectedyear);
                        CartAdapter.this.cartFragment.date = holder.date_option.getText().toString();
                    }
                }

                public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    DatePickerDialog mDatePicker = new DatePickerDialog(CartAdapter.this.context, new C03141(), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();
                }
            });
            holder.time.setOnClickListener(new OnClickListener() {

                class C03161 implements OnTimeSetListener {
                    C03161() {
                    }

                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        boolean isPM;
                        int i = 12;
                        if (selectedHour >= 12) {
                            isPM = true;
                        } else {
                            isPM = false;
                        }
                        TextView textView = holder.time_option;
                        String str = "%02d:%02d %s";
                        Object[] objArr = new Object[3];
                        if (!(selectedHour == 12 || selectedHour == 0)) {
                            i = selectedHour % 12;
                        }
                        objArr[0] = Integer.valueOf(i);
                        objArr[1] = Integer.valueOf(selectedMinute);
                        objArr[2] = isPM ? "PM" : "AM";
                        textView.setText(String.format(str, objArr));
                        CartAdapter.this.cartFragment.time = holder.time_option.getText().toString();
                    }
                }

                @RequiresApi(api = 24)
                public void onClick(View view) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    TimePickerDialog mTimePicker = new TimePickerDialog(CartAdapter.this.context, new C03161(), mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });
            holder.clock.setOnClickListener(new OnClickListener() {

                class C03181 implements OnTimeSetListener {
                    C03181() {
                    }

                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        boolean isPM;
                        int i = 12;
                        if (selectedHour >= 12) {
                            isPM = true;
                        } else {
                            isPM = false;
                        }
                        TextView textView = holder.time_option;
                        String str = "%02d:%02d %s";
                        Object[] objArr = new Object[3];
                        if (!(selectedHour == 12 || selectedHour == 0)) {
                            i = selectedHour % 12;
                        }
                        objArr[0] = Integer.valueOf(i);
                        objArr[1] = Integer.valueOf(selectedMinute);
                        objArr[2] = isPM ? "PM" : "AM";
                        textView.setText(String.format(str, objArr));
                        CartAdapter.this.cartFragment.time = holder.time_option.getText().toString();
                    }
                }

                @RequiresApi(api = 24)
                public void onClick(View view) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    TimePickerDialog mTimePicker = new TimePickerDialog(CartAdapter.this.context, new C03181(), mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.products.size();
    }
}
