package com.example.yellowsoft.pharmzi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class CheckoutPickupActivity extends FragmentActivity implements OnMapReadyCallback {
    int ASK_MULTIPLE_PERMISSION_REQUEST_CODE;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    TextView area_option;
    ImageView back_btn;
    EditText block;
    String coupon;
    TextView dc;
    String discount_value;
    EditText email;
    EditText flat;
    EditText fname;
    EditText house;
    EditText jaddah;
    EditText lname;
    String main_total;
    String message;
    ArrayList<Pharmacies> pharmacies;
    EditText phone;
    TextView pro_title;
    EditText street;
    TextView submit_btn;
    TextView sub;
    String title,area_id;
    TextView total;
    CheckoutAdapter adapter;
    ArrayList<Products> products;
    ListView listView;
    String delivery_charge;
    TextView st_os,st_ps,st_subtotal,st_dc,st_total;



    class C03621 implements View.OnClickListener {
        C03621() {
        }

        public void onClick(View view) {
            CheckoutPickupActivity.this.onBackPressed();
        }
    }

    class C03632 implements View.OnClickListener {
        C03632() {
        }

        public void onClick(View view) {
            String fname_string = CheckoutPickupActivity.this.fname.getText().toString();
            String lname_string = CheckoutPickupActivity.this.lname.getText().toString();
            String email_string = CheckoutPickupActivity.this.email.getText().toString();
            String phone_string = CheckoutPickupActivity.this.phone.getText().toString();
            String block_string = CheckoutPickupActivity.this.block.getText().toString();
            String street_string = CheckoutPickupActivity.this.street.getText().toString();
            String house_string = CheckoutPickupActivity.this.house.getText().toString();
            String flat_string = CheckoutPickupActivity.this.flat.getText().toString();
            String jaddah_string = CheckoutPickupActivity.this.jaddah.getText().toString();
            if (fname_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.fname.requestFocus();
            } else if (lname_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.lname.requestFocus();
            } else if (email_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.email.requestFocus();
            } else if (phone_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.phone.requestFocus();
            } else if (block_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Block", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.block.requestFocus();
            } else if (street_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Street", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.street.requestFocus();
            } else if (house_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter House", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.house.requestFocus();
            } else if (flat_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Flat/Apartment", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.flat.requestFocus();
            } else if (jaddah_string.equals("")) {
                Toast.makeText(CheckoutPickupActivity.this, "Please Enter Avenue(Jaddah)", Toast.LENGTH_SHORT).show();
                CheckoutPickupActivity.this.jaddah.requestFocus();
            } else {
                place_order();
//                Intent intent = new Intent(CheckoutPickupActivity.this, PaymentPage.class);
//                intent.putExtra("amount", CheckoutPickupActivity.this.main_total);
//                CheckoutPickupActivity.this.startActivityForResult(intent, 1);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_pickup);
        getWindow().setSoftInputMode(3);
        Session.forceRTLIfSupported(this);
        this.pharmacies = (ArrayList) getIntent().getSerializableExtra("pharmcies");
        this.products = (ArrayList<Products>) getIntent().getSerializableExtra("products");
        this.main_total = getIntent().getStringExtra("total");
        this.coupon = getIntent().getStringExtra("text");
        this.discount_value = getIntent().getStringExtra("dv");
        Session.forceRTLIfSupported(this);
        try {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.fname = (EditText) findViewById(R.id.fname);
        this.lname = (EditText) findViewById(R.id.lname);
        this.email = (EditText) findViewById(R.id.email);
        this.phone = (EditText) findViewById(R.id.phone);
        this.block = (EditText) findViewById(R.id.block);
        this.street = (EditText) findViewById(R.id.street);
        this.house = (EditText) findViewById(R.id.house);
        this.flat = (EditText) findViewById(R.id.flat);
        this.jaddah = (EditText) findViewById(R.id.jaddah);
        this.sub = (TextView) findViewById(R.id.sub);
        this.dc = (TextView) findViewById(R.id.dc);
        this.total = (TextView) findViewById(R.id.total);
        this.submit_btn = (TextView) findViewById(R.id.submit_btn);
       // this.pro_title = (TextView) findViewById(R.id.pro_title);
        this.area_option = (TextView) findViewById(R.id.area_option);
        this.area_option.setText(Session.GetAreaTitle(this));
//        this.pro_title.setText(Session.GetProductTitle(this));
        this.sub.setText(main_total + " KD ");
        delivery_charge = Session.GetDelivery(this);
        this.total.setText(this.main_total+ delivery_charge + " KD ");
        this.back_btn.setOnClickListener(new C03621());
        this.submit_btn.setOnClickListener(new C03632());
        listView = (ListView) findViewById(R.id.order_summ);
        dc.setText(Session.GetPharmciDc(this) + " KD ");


        this.st_os = (TextView) findViewById(R.id.st_os);
        this.st_ps = (TextView) findViewById(R.id.st_ps);
        this.st_subtotal = (TextView) findViewById(R.id.st_subtotal);
        this.st_dc = (TextView) findViewById(R.id.st_dc);
        this.st_total = (TextView) findViewById(R.id.st_total);

        st_os.setText(Session.GetWord(this,"Order Summary"));
        st_ps.setText(Session.GetWord(this,"Price Summary"));
        st_dc.setText(Session.GetWord(this,"Delivery Charges"));
        st_subtotal.setText(Session.GetWord(this,"Subtotal"));
        st_total.setText(Session.GetWord(this,"Total Price"));
        submit_btn.setText(Session.GetWord(this,"Submit Order"));

        adapter = new CheckoutAdapter(this,products);
        listView.setAdapter(adapter);

        area_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(CheckoutPickupActivity.this,AreaActivity.class);
                startActivityForResult(intent,2);
            }
        });

        get_members();

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
        }else if (resultCode == 2){
            if (data != null && data.hasExtra("area_title")) {
                this.title = data.getExtras().getString("area_title");
                this.area_id = data.getExtras().getString(Session.area_id);
                Log.e("area_title", this.title);
                this.area_option.setText(this.title);
            }
        } else if (resultCode != 0) {
        }
    }

    public void onMapReady(GoogleMap map) {
        map.setMapType(3);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            map.getUiSettings().setZoomControlsEnabled(true);
            LatLng point = new LatLng(Double.parseDouble(Session.GetLatitude(this)), Double.parseDouble(Session.GetLongitude(this)));
            Log.e(Session.lat, Session.GetLatitude(this));
            Log.e("long", Session.GetLongitude(this));
            Marker marker = map.addMarker(new MarkerOptions().position(point).title(Session.GetAreaTitle(this)).visible(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            Log.e("long", Session.GetAreaTitle(this));
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(point, 15.0f);
            map.animateCamera(location);
            map.moveCamera(location);
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.CAMERA"}, this.ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
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
        jsonObject1.addProperty("house", this.house.getText().toString());
        jsonObject1.addProperty("flat", this.flat.getText().toString());
        jsonObject1.addProperty("email", this.email.getText().toString());
        jsonObject1.addProperty("jaddah", this.jaddah.getText().toString());
        jsonObject.add("address", jsonObject1);
        jsonObject.addProperty("coupon_code", this.coupon);
        jsonObject.addProperty("discount_amount", this.discount_value);
        jsonObject.addProperty("total_price", this.main_total);
        Log.e("dc", Session.GetPharmciId(this));
        jsonObject.addProperty("delivery_charges", Session.GetPharmciDc(this));
        jsonObject.addProperty("payment_method", "Cash");
        jsonObject.addProperty("deliveryTime", "");
        jsonObject.addProperty("deliveryDate", "");
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
                        Toast.makeText(CheckoutPickupActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        Session.deleteCart(CheckoutPickupActivity.this);
                        CheckoutPickupActivity.this.startActivity(new Intent(CheckoutPickupActivity.this, ThankyouActivity.class));
                        CheckoutPickupActivity.this.finish();
                        return;
                    }
                    Toast.makeText(CheckoutPickupActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    public void get_members(){
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();

        Ion.with(this)
                .load(Session.SERVERURL+"members.php")
                .setBodyParameter("member_id",Session.GetUserId(this))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            JsonObject jsonObject = result.get(0).getAsJsonObject();
                            fname.setText(jsonObject.get("fname").getAsString());
                            email.setText(jsonObject.get("email").getAsString());
                            phone.setText(jsonObject.get("phone").getAsString());
                            lname.setText(jsonObject.get("lname").getAsString());
                            block.setText(jsonObject.get("block").getAsString());
                            street.setText(jsonObject.get("street").getAsString());
                            house.setText(jsonObject.get("house").getAsString());
                            flat.setText(jsonObject.get("flat").getAsString());
                            jaddah.setText(jsonObject.get("jaddah").getAsString());

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}

