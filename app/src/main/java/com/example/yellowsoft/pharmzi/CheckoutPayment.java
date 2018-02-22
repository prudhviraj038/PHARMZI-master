package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 20-02-2018.
 */

public class CheckoutPayment extends Activity {
    CheckoutDeliveryAdapter adapter;
    String b_apartment;
    String b_block;
    String b_details;
    String b_floor;
    String b_house;
    String b_jaddah;
    String b_phone;
    String b_street;
    ImageView back_btn;
    String block;
    TextView check_btn;
    TextView cod;
    LinearLayout coupon;
    EditText coupon_text;
    String date;
    Float dc;
    TextView delivery_charge;
    TextView delivery_charge_2;
    LinearLayout discount;
    TextView discount_value;
    String email;
    String extradetails;
    String fname;
    String h_apartment;
    String h_block;
    String h_details;
    String h_floor;
    String h_house;
    String h_jaddah;
    String h_phone;
    String h_street;
    String house;
    String jaddah;
    ListView listView;
    String lname;
    String message;
    String msg;
    String pay_met = "";
    TextView payment_btn;
    TextView pharmcy_title;
    String phone;
    ArrayList<Products> productsfrom_api;
    String street;
    TextView submit_btn;
    TextView subtotal;
    String time;
    Float total;
    float total_cart_price = 0.0f;
    TextView total_order;
    TextView st_os,text,st_dp,st_subtotal,st_ot,summary;

    class C03561 implements View.OnTouchListener {
        C03561() {
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

    class C03572 implements View.OnClickListener {
        C03572() {
        }

        public void onClick(View view) {
            CheckoutPayment.this.check_coupon();
        }
    }

    class C03583 implements View.OnClickListener {
        C03583() {
        }

        public void onClick(View view) {
            CheckoutPayment.this.onBackPressed();
        }
    }

    class C03594 implements View.OnClickListener {
        C03594() {
        }

        public void onClick(View view) {
            CheckoutPayment.this.payment_btn.setBackgroundColor(CheckoutPayment.this.getResources().getColor(R.color.languagecolor));
            CheckoutPayment.this.payment_btn.setTextColor(CheckoutPayment.this.getResources().getColor(R.color.white));
            CheckoutPayment.this.cod.setBackground(CheckoutPayment.this.getResources().getDrawable(R.drawable.white_rect));
            CheckoutPayment.this.cod.setTextColor(CheckoutPayment.this.getResources().getColor(R.color.text_color));
            CheckoutPayment.this.cod.setPadding(10, 0, 0, 0);
            CheckoutPayment.this.pay_met = "Tap";
        }
    }

    class C03605 implements View.OnClickListener {
        C03605() {
        }

        public void onClick(View view) {
            CheckoutPayment.this.cod.setBackgroundColor(CheckoutPayment.this.getResources().getColor(R.color.languagecolor));
            CheckoutPayment.this.cod.setTextColor(CheckoutPayment.this.getResources().getColor(R.color.white));
            CheckoutPayment.this.payment_btn.setBackground(CheckoutPayment.this.getResources().getDrawable(R.drawable.white_rect));
            CheckoutPayment.this.payment_btn.setTextColor(CheckoutPayment.this.getResources().getColor(R.color.text_color));
            CheckoutPayment.this.payment_btn.setPadding(10, 0, 0, 0);
            CheckoutPayment.this.pay_met = "Cash";
        }
    }

    class C03616 implements View.OnClickListener {
        C03616() {
        }

        public void onClick(View view) {
            if (CheckoutPayment.this.pay_met.equals("Tap")) {
                Intent intent = new Intent(CheckoutPayment.this, PaymentPage.class);
                intent.putExtra("amount", String.valueOf(CheckoutPayment.this.total));
                Log.e("tot", String.valueOf(CheckoutPayment.this.total));
                CheckoutPayment.this.startActivityForResult(intent, 1);
            } else if (CheckoutPayment.this.pay_met.equals("Cash")) {
                CheckoutPayment.this.place_order();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_payment);
        this.productsfrom_api = new ArrayList();
        if (Session.GetAddressType(this).equals("House")) {
            this.block = getIntent().getStringExtra("block");
            this.street = getIntent().getStringExtra("street");
            this.jaddah = getIntent().getStringExtra("jaddah");
            this.house = getIntent().getStringExtra("house");
            this.extradetails = getIntent().getStringExtra("extradetails");
            this.phone = getIntent().getStringExtra(Session.phone);
            this.msg = getIntent().getStringExtra("message");
        } else if (Session.GetAddressType(this).equals("Apartment")) {
            this.b_block = getIntent().getStringExtra("block");
            this.b_street = getIntent().getStringExtra("street");
            this.b_jaddah = getIntent().getStringExtra("jaddah");
            this.b_house = getIntent().getStringExtra("house");
            this.b_floor = getIntent().getStringExtra("floor");
            this.b_apartment = getIntent().getStringExtra("apartment");
            this.b_phone = getIntent().getStringExtra(Session.phone);
            this.b_details = getIntent().getStringExtra("buil_details");
            this.msg = getIntent().getStringExtra("message");
        } else if (Session.GetAddressType(this).equals("Hospital")) {
            this.h_block = getIntent().getStringExtra("block");
            this.h_street = getIntent().getStringExtra("street");
            this.h_jaddah = getIntent().getStringExtra("jaddah");
            this.h_house = getIntent().getStringExtra("house");
            this.h_floor = getIntent().getStringExtra("floor");
            this.h_apartment = getIntent().getStringExtra("apartment");
            this.h_phone = getIntent().getStringExtra(Session.phone);
            this.h_details = getIntent().getStringExtra("hosp_details");
            this.msg = getIntent().getStringExtra("message");
        }
        this.listView = (ListView) findViewById(R.id.listview);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.coupon = (LinearLayout) findViewById(R.id.coupon);
        this.coupon_text = (EditText) findViewById(R.id.coupon_text);
        this.check_btn = (TextView) findViewById(R.id.check_btn);
        this.subtotal = (TextView) findViewById(R.id.subtotal);
        this.delivery_charge = (TextView) findViewById(R.id.delivery_charge);
        this.delivery_charge_2 = (TextView) findViewById(R.id.delivery_charge_2);
        this.total_order = (TextView) findViewById(R.id.total_order);
        this.cod = (TextView) findViewById(R.id.cod);
        this.payment_btn = (TextView) findViewById(R.id.payment_btn);
        this.submit_btn = (TextView) findViewById(R.id.submit_btn);
        this.discount = (LinearLayout) findViewById(R.id.discount);
        this.discount_value = (TextView) findViewById(R.id.discount_value);
        this.pharmcy_title = (TextView) findViewById(R.id.pharmcy_title);
        this.adapter = new CheckoutDeliveryAdapter(this, this.productsfrom_api, this);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnTouchListener(new C03561());
        this.check_btn.setOnClickListener(new C03572());
        this.back_btn.setOnClickListener(new C03583());
        this.payment_btn.setOnClickListener(new C03594());
        this.cod.setOnClickListener(new C03605());
        this.submit_btn.setOnClickListener(new C03616());

        this.st_os = (TextView) findViewById(R.id.st_os);
        this.text = (TextView) findViewById(R.id.text);
        this.st_dp = (TextView) findViewById(R.id.st_dp);
        this.st_subtotal = (TextView) findViewById(R.id.st_subtotal);
        this.summary = (TextView) findViewById(R.id.summary);
        this.st_ot = (TextView) findViewById(R.id.st_ot);

        st_os.setText(Session.GetWord(this,"Order Summary"));
        text.setText(Session.GetWord(this,"Payment Summary"));
        st_dp.setText(Session.GetWord(this,"Discount Price"));
        st_subtotal.setText(Session.GetWord(this,"Subtotal"));
        st_ot.setText(Session.GetWord(this,"Order Total"));
        summary.setText(Session.GetWord(this,"Payment Method"));
        submit_btn.setText(Session.GetWord(this,"Submit Order"));


        get_products();
    }

    public void remove_cart_data(int pos) {
        this.productsfrom_api.remove(pos);
        Session.deleteCart(this);
        for (int i = 0; i < this.productsfrom_api.size(); i++) {
            Session.SetCartProducts(this, (Products) this.productsfrom_api.get(i));
        }
        this.adapter.notifyDataSetChanged();
        calculate_total_price();
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
                    Toast.makeText(this, "Payment Done Successfully", Toast.LENGTH_SHORT).show();
                    place_order();
                } else if (this.message.equals("failure")) {
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode != 0) {
        }
    }

    public void get_products() {
        Log.e("cart_list", String.valueOf(Session.GetCartProducts(this).toString()));
        JsonArray temp = Session.GetCartProducts(this);
        for (int i = 0; i < temp.size(); i++) {
            Log.e("result", temp.toString());
            Products products = new Products(temp.get(i).getAsJsonObject(), this);
            this.productsfrom_api.add(products);
            Log.e("_price", products.price);
            this.total_cart_price += ((float) products.cart_quantity) * Float.parseFloat(products.price);
            this.delivery_charge.setText(Session.GetPharmciDc(this) + " KD ");
            this.dc = Float.valueOf(Float.parseFloat(Session.GetPharmciDc(this)));
        }
        try {
            this.total = Float.valueOf(this.total_cart_price + this.dc.floatValue());
            Log.e("tt", String.valueOf(this.total));
            this.total_order.setText(String.valueOf(this.total) + " KD ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.adapter.notifyDataSetChanged();
        this.total_order.setText(String.valueOf(this.total) + " KD ");
        Log.e("tp", this.total_order.getText().toString());
        this.subtotal.setText(String.valueOf(this.total_cart_price) + " KD ");
    }

    public void calculate_total_price() {
        this.total_cart_price = 0.0f;
        for (int i = 0; i < this.productsfrom_api.size(); i++) {
            this.total_cart_price = (Float.parseFloat(((Products) this.productsfrom_api.get(i)).price) * ((float) ((Products) this.productsfrom_api.get(i)).cart_quantity)) + this.total_cart_price;
            this.delivery_charge.setText(Session.GetPharmciDc(this) + " KD ");
            this.dc = Float.valueOf(Float.parseFloat(Session.GetPharmciDc(this)));
        }
        this.total_order.setText(String.valueOf(this.total_cart_price) + " KD ");
        this.subtotal.setText(String.valueOf(this.total_cart_price) + " KD ");
        this.total = Float.valueOf(this.total_cart_price + this.dc.floatValue());
        this.total_order.setText(String.valueOf(this.total) + " KD ");
    }

    public void check_coupon() {
        String coupon_string = this.coupon_text.getText().toString();
        if (coupon_string.equals("")) {
            Toast.makeText(this, "Please Enter Coupon Code", Toast.LENGTH_SHORT).show();
            this.coupon_text.requestFocus();
            return;
        }
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
         Ion.with(this).load("http://clients.mamacgroup.com/sadaleya/api/coupon-check.php").setBodyParameter("coupon", coupon_string).setBodyParameter("cart_total", String.valueOf(this.total_cart_price)).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            public void onCompleted(Exception e, JsonObject result) {
                try {
                    hud.dismiss();
                    Log.e("coupon", result.toString());
                    if (result.get("status").getAsString().equals("Success")) {
                        Toast.makeText(CheckoutPayment.this, result.get("discount_value").getAsString(), Toast.LENGTH_SHORT).show();
                        CheckoutPayment.this.discount.setVisibility(View.VISIBLE);
                        CheckoutPayment.this.discount_value.setText(result.get("discount_value").getAsString() + " KD ");
                        Float discountprice = Float.valueOf(Float.parseFloat(result.get("discount_value").getAsString()));
                        CheckoutPayment.this.total_cart_price -= discountprice.floatValue();
                        CheckoutPayment.this.total = Float.valueOf(CheckoutPayment.this.total_cart_price + CheckoutPayment.this.dc.floatValue());
                        Log.e("ctt", String.valueOf(CheckoutPayment.this.total));
                        CheckoutPayment.this.total_order.setText(String.valueOf(CheckoutPayment.this.total) + " KD ");
                        return;
                    }
                    Toast.makeText(CheckoutPayment.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
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
        JsonObject jsonObject1;
        if (Session.GetAddressType(this).equals("House")) {
            jsonObject1 = new JsonObject();
            jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
            jsonObject1.addProperty("area", Session.GetAreaId(this));
            jsonObject1.addProperty("street", this.street);
            jsonObject1.addProperty("block", this.block);
            jsonObject1.addProperty("lastname", Session.GetLname(this));
            jsonObject1.addProperty("firstname", Session.GetFname(this));
            jsonObject1.addProperty("house", this.house);
            jsonObject1.addProperty("flat", "");
            jsonObject1.addProperty("email", Session.GetEmail(this));
            jsonObject1.addProperty("jaddah", this.jaddah);
            jsonObject.add("address", jsonObject1);
        } else if (Session.GetAddressType(this).equals("Apartment")) {
            jsonObject1 = new JsonObject();
            jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
            jsonObject1.addProperty("area", Session.GetAreaId(this));
            jsonObject1.addProperty("street", this.b_street);
            jsonObject1.addProperty("block", this.b_block);
            jsonObject1.addProperty("lastname", Session.GetLname(this));
            jsonObject1.addProperty("firstname", Session.GetFname(this));
            jsonObject1.addProperty("house", this.b_house);
            jsonObject1.addProperty("floor", this.b_floor);
            jsonObject1.addProperty("flat", "");
            jsonObject1.addProperty("email", Session.GetEmail(this));
            jsonObject1.addProperty("jaddah", this.b_jaddah);
            jsonObject.add("address", jsonObject1);
            Log.e("apartmentadd", jsonObject1.toString());
        } else if (Session.GetAddressType(this).equals("Hospital")) {
            jsonObject1 = new JsonObject();
            jsonObject1.addProperty(Session.phone, Session.GetPhone(this));
            jsonObject1.addProperty("area", Session.GetAreaId(this));
            jsonObject1.addProperty("street", this.h_street);
            jsonObject1.addProperty("block", this.h_block);
            jsonObject1.addProperty("lastname", Session.GetLname(this));
            jsonObject1.addProperty("firstname", Session.GetFname(this));
            jsonObject1.addProperty("house", this.h_house);
            jsonObject1.addProperty("floor", this.h_floor);
            jsonObject1.addProperty("flat", "");
            jsonObject1.addProperty("email", Session.GetEmail(this));
            jsonObject1.addProperty("jaddah", this.h_jaddah);
            jsonObject.add("address", jsonObject1);
        }
        jsonObject.addProperty("coupon_code", this.coupon_text.getText().toString());
        jsonObject.addProperty("discount_amount", this.discount_value.getText().toString());
        jsonObject.addProperty("total_price", this.total);
        Log.e("dc", Session.GetPharmciId(this));
        jsonObject.addProperty("delivery_charges", Session.GetPharmciDc(this));
        jsonObject.addProperty("payment_method", this.pay_met);
        jsonObject.addProperty("deliveryTime", this.time);
        jsonObject.addProperty("deliveryDate", this.date);
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
                        Toast.makeText(CheckoutPayment.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        Session.deleteCart(CheckoutPayment.this);
                        CheckoutPayment.this.startActivity(new Intent(CheckoutPayment.this, ThankyouActivity.class));
                        CheckoutPayment.this.finish();
                        return;
                    }
                    Toast.makeText(CheckoutPayment.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void place_cod() {
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
        JsonObject jsonObject1;
        if (Session.GetAddressType(this).equals("House")) {
            jsonObject1 = new JsonObject();
            jsonObject1.addProperty(Session.phone, this.phone);
            jsonObject1.addProperty("area", Session.GetAreaId(this));
            jsonObject1.addProperty("street", this.street);
            jsonObject1.addProperty("block", this.block);
            jsonObject1.addProperty("lastname", Session.GetLname(this));
            jsonObject1.addProperty("firstname", Session.GetFname(this));
            jsonObject1.addProperty("house", this.house);
            jsonObject1.addProperty("flat", "");
            jsonObject1.addProperty("email", Session.GetEmail(this));
            jsonObject1.addProperty("jaddah", this.jaddah);
            jsonObject.add("address", jsonObject1);
        } else if (Session.GetAddressType(this).equals("Apartment")) {
            jsonObject1 = new JsonObject();
            jsonObject1.addProperty(Session.phone, this.b_phone);
            jsonObject1.addProperty("area", Session.GetAreaId(this));
            jsonObject1.addProperty("street", this.b_street);
            jsonObject1.addProperty("block", this.block);
            jsonObject1.addProperty("lastname", Session.GetLname(this));
            jsonObject1.addProperty("firstname", Session.GetFname(this));
            jsonObject1.addProperty("house", this.b_house);
            jsonObject1.addProperty("floor", this.b_floor);
            jsonObject1.addProperty("email", Session.GetEmail(this));
            jsonObject1.addProperty("jaddah", this.jaddah);
            jsonObject.add("address", jsonObject1);
        }
        jsonObject.addProperty("coupon_code", this.coupon_text.getText().toString());
        jsonObject.addProperty("discount_amount", this.discount_value.getText().toString());
        jsonObject.addProperty("total_price", this.total);
        Log.e("dc", Session.GetPharmciId(this));
        jsonObject.addProperty("delivery_charges", Session.GetPharmciDc(this));
        jsonObject.addProperty("payment_method", this.pay_met);
        jsonObject.addProperty("deliveryTime", this.time);
        jsonObject.addProperty("deliveryDate", this.date);
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
                        Toast.makeText(CheckoutPayment.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        Session.deleteCart(CheckoutPayment.this);
                        CheckoutPayment.this.startActivity(new Intent(CheckoutPayment.this, ThankyouActivity.class));
                        CheckoutPayment.this.finish();
                        return;
                    }
                    Toast.makeText(CheckoutPayment.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

