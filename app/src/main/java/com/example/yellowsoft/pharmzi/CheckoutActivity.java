package com.example.yellowsoft.pharmzi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yellowsoft on 5/1/18.
 */

public class CheckoutActivity extends Activity {
    CheckoutAdapter adapter;
    TextView area;
    ImageView back_btn;
    EditText block;
    EditText building;
    ImageView cod_img;
    LinearLayout cod_ll;
    String coupon;
    TextView date;
    TextView dc;
    String discount_value;
    EditText email;
    EditText flat;
    EditText fname;
    EditText jaddah;
    ListView listView;
    EditText lname;
    String message;
    LinearLayout now_btn;
    ImageView now_img;
    String pay_met = "";
    LinearLayout payment_option;
    EditText phone;
    LinearLayout place_order;
    LinearLayout preorder_btn;
    ImageView preorder_img;
    ArrayList<Products> products;
    String shipping_price;
    EditText street;
    TextView sub;
    ImageView tap_img;
    LinearLayout tap_ll;
    TextView time;
    TextView total;
    String total_price;
    String title,area_id_;
    TextView st_date,st_time,st_os,st_ps,st_pm,st_subtotal,st_dc,st_total;

    class C03821 implements View.OnClickListener {
        C03821() {
        }

        public void onClick(View view) {
            CheckoutActivity.this.onBackPressed();
        }
    }

    class C03832 implements View.OnTouchListener {
        C03832() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case 0:
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case 1:
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            v.onTouchEvent(event);
            return true;
        }
    }

    class C03843 implements View.OnClickListener {
        C03843() {
        }

        public void onClick(View v) {
            tap_img.setImageResource(R.drawable.radio_off);
            CheckoutActivity.this.cod_img.setImageResource(R.drawable.radio_on);
            CheckoutActivity.this.pay_met = "Cash";
        }
    }

    class C03854 implements View.OnClickListener {
        C03854() {
        }

        public void onClick(View v) {
            CheckoutActivity.this.tap_img.setImageResource(R.drawable.radio_on);
            CheckoutActivity.this.cod_img.setImageResource(R.drawable.radio_off);
            CheckoutActivity.this.pay_met = "Tap";
        }
    }

    class C03865 implements View.OnClickListener {
        C03865() {
        }

        public void onClick(View view) {
            CheckoutActivity.this.now_img.setImageResource(R.drawable.radio_on);
            CheckoutActivity.this.preorder_img.setImageResource(R.drawable.radio_off);
            Date todaysdate = new Date();
            CheckoutActivity.this.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(todaysdate));
            CheckoutActivity.this.time.setText(new SimpleDateFormat("hh:mm a").format(new Date()));
        }
    }

    class C03876 implements View.OnClickListener {
        C03876() {
        }

        public void onClick(View view) {
            CheckoutActivity.this.preorder_img.setImageResource(R.drawable.radio_on);
            CheckoutActivity.this.now_img.setImageResource(R.drawable.radio_off);
            CheckoutActivity.this.date.setText("");
            CheckoutActivity.this.time.setText("");
            CheckoutActivity.this.date.setHint("Select Date");
            CheckoutActivity.this.time.setHint("Select Time");
        }
    }

    class C03897 implements View.OnClickListener {

        class C03881 implements TimePickerDialog.OnTimeSetListener {
            C03881() {
            }

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                boolean isPM;
                int i = 12;
                if (selectedHour >= 12) {
                    isPM = true;
                } else {
                    isPM = false;
                }
                TextView textView = CheckoutActivity.this.time;
                String str = "%02d:%02d %s";
                Object[] objArr = new Object[3];
                if (!(selectedHour == 12 || selectedHour == 0)) {
                    i = selectedHour % 12;
                }
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Integer.valueOf(selectedMinute);
                objArr[2] = isPM ? "PM" : "AM";
                textView.setText(String.format(str, objArr));
            }
        }

        C03897() {
        }

        @RequiresApi(api = 24)
        public void onClick(View view) {
            Calendar mcurrentTime = Calendar.getInstance();
            TimePickerDialog mTimePicker = new TimePickerDialog(CheckoutActivity.this, new C03881(), mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    }

    class C03918 implements View.OnClickListener {

        class C03901 implements DatePickerDialog.OnDateSetListener {
            C03901() {
            }

            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                CheckoutActivity.this.date.setText(selectedday + "-" + (selectedmonth + 1) + "-" + selectedyear);
            }
        }

        C03918() {
        }

        public void onClick(View view) {
            Calendar mcurrentDate = Calendar.getInstance();
            DatePickerDialog mDatePicker = new DatePickerDialog(CheckoutActivity.this, new C03901(), mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
            mDatePicker.setTitle("Select date");
            mDatePicker.show();
        }
    }

    class C03929 implements View.OnClickListener {
        C03929() {
        }

        public void onClick(View view) {
            String fname_string = fname.getText().toString();
            String lname_string = lname.getText().toString();
            String email_string = email.getText().toString();
            String phone_string = phone.getText().toString();
            String area_string = Session.GetAreaId(CheckoutActivity.this);
            String block_string = block.getText().toString();
            String street_string =street.getText().toString();
            String house_string = building.getText().toString();
            String flat_string = flat.getText().toString();
            String juddah_string = jaddah.getText().toString();
            if (fname_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter First Name",Toast.LENGTH_SHORT).show();
                fname.requestFocus();
            } else if (lname_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                lname.requestFocus();
            } else if (phone_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                phone.requestFocus();
            } else if (area_string == "") {
                Toast.makeText(CheckoutActivity.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
              area.requestFocus();
            } else if (block_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Block", Toast.LENGTH_SHORT).show();
               block.requestFocus();
            } else if (street_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Street", Toast.LENGTH_SHORT).show();
                street.requestFocus();
            } else if (house_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter House", Toast.LENGTH_SHORT).show();
               building.requestFocus();
            } else if (flat_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Flat", Toast.LENGTH_SHORT).show();
                CheckoutActivity.this.flat.requestFocus();
            } else if (juddah_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Juddah", Toast.LENGTH_SHORT).show();
                CheckoutActivity.this.jaddah.requestFocus();
            } else if (email_string.equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                CheckoutActivity.this.email.requestFocus();
            } else if (CheckoutActivity.this.date.getText().equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                CheckoutActivity.this.date.requestFocus();
            } else if (CheckoutActivity.this.time.getText().equals("")) {
                Toast.makeText(CheckoutActivity.this, "Please Select Time", Toast.LENGTH_SHORT).show();
                CheckoutActivity.this.time.requestFocus();
            } else if (!CheckoutActivity.this.tap_img.isSelected()) {
                Toast.makeText(CheckoutActivity.this, "Please Select Payment Method", Toast.LENGTH_SHORT).show();
            } else if (CheckoutActivity.this.pay_met.equals("Tap")) {
                Intent intent = new Intent(CheckoutActivity.this, PaymentPage.class);
                intent.putExtra("amount", CheckoutActivity.this.total_price);
                CheckoutActivity.this.startActivityForResult(intent, 1);
            } else if (CheckoutActivity.this.pay_met.equals("Cash")) {
                CheckoutActivity.this.place_order();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_screen);
        getWindow().setSoftInputMode(3);
        Session.forceRTLIfSupported(this);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.fname = (EditText) findViewById(R.id.fname);
        this.lname = (EditText) findViewById(R.id.lname);
        this.phone = (EditText) findViewById(R.id.phone);
        this.block = (EditText) findViewById(R.id.block);
        this.street = (EditText) findViewById(R.id.street);
        this.building = (EditText) findViewById(R.id.building);
        this.flat = (EditText) findViewById(R.id.flat);
        this.email = (EditText) findViewById(R.id.email);
        this.area = (TextView) findViewById(R.id.area);
        this.date = (TextView) findViewById(R.id.date);
        this.time = (TextView) findViewById(R.id.time);
        this.sub = (TextView) findViewById(R.id.sub);
        this.dc = (TextView) findViewById(R.id.dc);
        this.total = (TextView) findViewById(R.id.total);
        this.tap_img = (ImageView) findViewById(R.id.tap_img);
        this.cod_img = (ImageView) findViewById(R.id.cod_img);
        this.tap_ll = (LinearLayout) findViewById(R.id.tap_ll);
        this.cod_ll = (LinearLayout) findViewById(R.id.cod_ll);
        this.jaddah = (EditText) findViewById(R.id.jaddah);
        this.listView = (ListView) findViewById(R.id.order_summ);
        this.payment_option = (LinearLayout) findViewById(R.id.payment_option);
        this.place_order = (LinearLayout) findViewById(R.id.place_order);
        this.now_btn = (LinearLayout) findViewById(R.id.now_btn);
        this.preorder_btn = (LinearLayout) findViewById(R.id.preorder_btn);
        this.now_img = (ImageView) findViewById(R.id.now_img);
        this.preorder_img = (ImageView) findViewById(R.id.preorder_img);
        this.back_btn.setOnClickListener(new C03821());
        if (getIntent() != null && getIntent().hasExtra("total")) {
            this.total_price = getIntent().getStringExtra("total");
            this.coupon = getIntent().getStringExtra("text");
            this.discount_value = getIntent().getStringExtra("dv");
            this.shipping_price = getIntent().getStringExtra("dc");
            this.products = (ArrayList) getIntent().getSerializableExtra("products");
        }
        this.adapter = new CheckoutAdapter(this, this.products);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnTouchListener(new C03832());
        this.sub.setText(this.total_price + "KD");
        this.dc.setText(this.shipping_price + " KD ");
        this.total.setText(this.total_price + this.shipping_price + " KD ");
        Log.e("final_cost", this.total.getText().toString());
        this.area.setText(Session.GetAreaTitle(this));
        this.tap_img.setImageResource(R.drawable.radio_off);
        this.cod_img.setImageResource(R.drawable.radio_off);
        this.cod_ll.setOnClickListener(new C03843());
        this.tap_ll.setOnClickListener(new C03854());
        this.now_btn.setOnClickListener(new C03865());
        this.preorder_btn.setOnClickListener(new C03876());
        this.time.setOnClickListener(new C03897());
        this.date.setOnClickListener(new C03918());
        this.place_order.setOnClickListener(new C03929());

        this.st_date = (TextView) findViewById(R.id.st_date);
        this.st_time = (TextView) findViewById(R.id.st_time);
        this.st_os = (TextView) findViewById(R.id.st_os);
        this.st_ps = (TextView) findViewById(R.id.st_ps);
        this.st_pm = (TextView) findViewById(R.id.st_pm);
        this.st_subtotal = (TextView) findViewById(R.id.st_subtotal);
        this.st_dc = (TextView) findViewById(R.id.st_dc);
        this.st_total = (TextView) findViewById(R.id.st_total);

        st_os.setText(Session.GetWord(this,"Order Summary"));
        st_ps.setText(Session.GetWord(this,"Price Summary"));
        st_dc.setText(Session.GetWord(this,"Delivery Charges"));
        st_subtotal.setText(Session.GetWord(this,"Subtotal"));
        st_total.setText(Session.GetWord(this,"Total Price"));
        st_date.setText(Session.GetWord(this,"PLEASE SELECT DELIVERY DATE"));
        st_time.setText(Session.GetWord(this,"PLEASE SELECT DELIVERY TIME"));
        st_pm.setText(Session.GetWord(this,"Payment Method"));

        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this,AreaActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            if (data != null && data.hasExtra("message")) {
                this.message = data.getExtras().getString("message");
                Log.e("toast", this.message);
                if (this.message.equals("success")) {
                    place_order();
                } else if (this.message.equals("failure")) {
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (resultCode == 2) {
            if (data != null && data.hasExtra("area_title")) {
                this.title = data.getExtras().getString("area_title");
                this.area_id_ = data.getExtras().getString(Session.area_id);
                Log.e("area_title", this.title);
                this.area.setText(this.title);
            }
        } else if (resultCode != 0) {
        }
    }

    public void place_order() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("member_id", Session.GetUserId(this));
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = Session.GetCartProducts(this);
        JsonArray jsonArray1 = new JsonArray();
        Log.e("products_cart", jsonArray.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("price", jsonArray.get(i).getAsJsonObject().get("price").getAsString());
            jsonObject2.addProperty("product_id", jsonArray.get(i).getAsJsonObject().get("id").getAsString());
            jsonObject2.addProperty("quantity", jsonArray.get(i).getAsJsonObject().get("cart_quantity").getAsString());
            jsonArray1.add(jsonObject2);
            Log.e("productsresponse", jsonArray1.toString());
        }
        jsonObject.add("products", jsonArray1);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty(Session.phone, this.phone.getText().toString());
        jsonObject1.addProperty("area", Session.GetAreaId(this));
        jsonObject1.addProperty("street", this.street.getText().toString());
        jsonObject1.addProperty("block", this.block.getText().toString());
        jsonObject1.addProperty("lastname", this.lname.getText().toString());
        jsonObject1.addProperty("firstname", this.fname.getText().toString());
        jsonObject1.addProperty("house", this.building.getText().toString());
        jsonObject1.addProperty("flat", this.flat.getText().toString());
        jsonObject1.addProperty("email", this.email.getText().toString());
        jsonObject1.addProperty("jaddah", this.jaddah.getText().toString());
        jsonObject.add("address", jsonObject1);
        jsonObject.addProperty("coupon_code", this.coupon);
        jsonObject.addProperty("discount_amount", this.discount_value);
        jsonObject.addProperty("total_price", this.total_price);
        Log.e("dc", Session.GetPharmciId(this));
        jsonObject.addProperty("delivery_charges", Session.GetPharmciDc(this));
        jsonObject.addProperty("payment_method", this.pay_met);
        jsonObject.addProperty("deliveryTime", this.time.getText().toString());
        jsonObject.addProperty("deliveryDate", this.date.getText().toString());
        jsonObject.addProperty("pharmacy", Session.GetPharmciId(this));
        jsonObject.addProperty("payment", "0");
        Log.e("reeeee", jsonObject.toString());
        Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/place-order.php").setBodyParameter("content", jsonObject.toString()).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            public void onCompleted(Exception e, JsonObject result) {
                hud.dismiss();
                try {
                    if (result.get("status").getAsString().equals("Success")) {
                        Log.e("result", result.toString());
                        Log.e("invoice_id", result.get("invoice_id").getAsString());
                        Log.e("result", result.get("message").getAsString());
                        Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(),Toast.LENGTH_SHORT).show();
                        Session.deleteCart(CheckoutActivity.this);
                        CheckoutActivity.this.startActivity(new Intent(CheckoutActivity.this, ThankyouActivity.class));
                        CheckoutActivity.this.finish();
                        return;
                    }
                    Toast.makeText(CheckoutActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
