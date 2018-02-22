package com.example.yellowsoft.pharmzi;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD.Style;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class CityActivity extends FragmentActivity {
    MyOrdersAdapter adapter;
    LinearLayout area_btn;
    String area_id;
    TextView area_textview;
    ArrayList<Areas> areasfrom_api;
    LinearLayout cart_btn;
    String count;
    TextView delivery_btn;
    String id;
    ImageView lang_btn;
    OffersAdapter offersAdapter;
    ArrayList<Offers> offersfrom_api;
    TextView offersmore_btn;
    LinearLayout orders_layout;
    ArrayList<Orders> ordersfrom_api;
    TextView ordersmore_btn;
    TextView pickup_btn;
    ArrayList<Products> productsfrom_api;
    RecyclerView recyclerView;
    RecyclerView rv1;
    ImageView select;
    TextView select_area;
    TextView st_deliver,st_country,st_orders,st_offers;
    String title;


    class C03641 implements OnClickListener {
        C03641() {
        }

        public void onClick(View view) {
            Intent intent = new Intent(CityActivity.this, MoreOrdersPage.class);
            intent.putExtra("orders", CityActivity.this.ordersfrom_api);
            intent.putExtra("products", CityActivity.this.productsfrom_api);
            CityActivity.this.startActivity(intent);
        }
    }

    class C03652 implements OnClickListener {
        C03652() {
        }

        public void onClick(View view) {
            Intent intent = new Intent(CityActivity.this, OffersActivity.class);
            intent.putExtra("offers", CityActivity.this.offersfrom_api);
            CityActivity.this.startActivity(intent);
        }
    }

    class C03663 implements OnClickListener {
        C03663() {
        }

        public void onClick(View view) {
            CityActivity.this.startActivity(new Intent(CityActivity.this, LanguageActivity.class));
            CityActivity.this.finish();
        }
    }

    class C03674 implements OnClickListener {
        C03674() {
        }

        public void onClick(View view) {
            CityActivity.this.startActivityForResult(new Intent(CityActivity.this, AreaActivity.class), 1);
        }
    }

    class C03685 implements OnClickListener {
        C03685() {
        }

        public void onClick(View view) {
            CityActivity.this.startActivityForResult(new Intent(CityActivity.this, AreaActivity.class), 1);
        }
    }

    class C03696 implements OnClickListener {
        C03696() {
        }

        public void onClick(View view) {
        }
    }

    class C03707 implements OnClickListener {
        C03707() {
        }

        public void onClick(View view) {
            if (CityActivity.this.select_area.getText().toString().equals("")) {
                Log.e("coming", "inside");
                Toast.makeText(CityActivity.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
                CityActivity.this.select_area.requestFocus();
                return;
            }else {
                Intent intent = new Intent(CityActivity.this, MainActivity.class);
                intent.putExtra("id", CityActivity.this.area_id);
                intent.putExtra("act", "0");
                intent.putExtra(Session.delivery, "0");
                Session.SetDelivery(CityActivity.this, Session.delivery);
                CityActivity.this.startActivity(intent);
            }
        }
    }

    class C03718 implements OnClickListener {
        C03718() {
        }

        public void onClick(View view) {
            if (CityActivity.this.select_area.getText().toString().equals("")) {
                Log.e("coming", "inside");
                Toast.makeText(CityActivity.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
                CityActivity.this.select_area.requestFocus();
                return;
            }else {
                Intent intent = new Intent(CityActivity.this, MainActivity.class);
                intent.putExtra("id", CityActivity.this.area_id);
                intent.putExtra("act", "0");
                intent.putExtra(Session.delivery, "1");
                Session.SetDelivery(CityActivity.this, Session.delivery);
                Log.e("pickup", "1");
                CityActivity.this.startActivity(intent);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_screen);
        Session.forceRTLIfSupported(this);
        this.ordersfrom_api = new ArrayList();
        this.areasfrom_api = new ArrayList();
        this.offersfrom_api = new ArrayList();
        this.productsfrom_api = new ArrayList();
        this.select_area = (TextView) findViewById(R.id.select_area);
        this.orders_layout = (LinearLayout) findViewById(R.id.orders_layout);
        this.delivery_btn = (TextView) findViewById(R.id.delivery_btn);
        this.pickup_btn = (TextView) findViewById(R.id.pickup_btn);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv);
        this.rv1 = (RecyclerView) findViewById(R.id.rv1);
        this.select = (ImageView) findViewById(R.id.select);
        this.ordersmore_btn = (TextView) findViewById(R.id.ordersmore_btn);
        this.st_deliver = (TextView) findViewById(R.id.st_deliver);
        this.st_country = (TextView) findViewById(R.id.st_country);
        this.st_orders = (TextView) findViewById(R.id.st_orders);
        this.st_offers = (TextView) findViewById(R.id.st_offers);
        this.offersmore_btn = (TextView) findViewById(R.id.offersmore_btn);
        this.area_btn = (LinearLayout) findViewById(R.id.area_btn);
        this.lang_btn = (ImageView) findViewById(R.id.lang_btn);
        this.adapter = new MyOrdersAdapter(this, this, this.ordersfrom_api, this.productsfrom_api);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.offersAdapter = new OffersAdapter(this, this.offersfrom_api);
        this.rv1.setAdapter(this.offersAdapter);
        this.rv1.setHasFixedSize(true);
        this.rv1.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.select_area.setHint("Select Area");
        this.ordersmore_btn.setOnClickListener(new C03641());
        this.offersmore_btn.setOnClickListener(new C03652());
        this.lang_btn.setOnClickListener(new C03663());
        this.select_area.setOnClickListener(new C03674());
        this.area_btn.setOnClickListener(new C03685());
        this.select.setOnClickListener(new C03696());
        if (Session.GetUserId(this).equals("-1")) {
            this.orders_layout.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.GONE);
        } else {
            this.orders_layout.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.VISIBLE);
        }
        this.delivery_btn.setOnClickListener(new C03707());
        this.pickup_btn.setOnClickListener(new C03718());
        st_deliver.setText(Session.GetWord(this,"Deliver to..."));
        st_country.setText(Session.GetWord(this,"Kuwait"));
        select_area.setHint(Session.GetWord(this,"Select Area"));
        delivery_btn.setText(Session.GetWord(this,"DELIVERY"));
        pickup_btn.setText(Session.GetWord(this,"PICK-UP"));
        ordersmore_btn.setText(Session.GetWord(this,"See All"));
        st_orders.setText(Session.GetWord(this,"My Orders"));
        st_offers.setText(Session.GetWord(this,"Featured Offers"));
        offersmore_btn.setText(Session.GetWord(this,"See All"));


        get_orders();
        get_offers();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            if (data != null && data.hasExtra("area_title")) {
                this.title = data.getExtras().getString("area_title");
                this.area_id = data.getExtras().getString(Session.area_id);
                Log.e("area_title", this.title);
                this.select_area.setText(this.title);
            }
        } else if (resultCode != 0) {
        }
    }

    public void get_areas() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
         Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/areas.php").asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("country", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        CityActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject(), CityActivity.this, "0"));
                        for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {
                            CityActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), CityActivity.this, "1"));
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public Dialog onAreaChoice() {
        Builder builder = new Builder(this);
        final CharSequence[] array = new CharSequence[this.areasfrom_api.size()];
        for (int i = 0; i < this.areasfrom_api.size(); i++) {
            array[i] = ((Areas) this.areasfrom_api.get(i)).title;
        }
        builder.setTitle("Select Area").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select", selectedItem);
                CityActivity.this.select_area.setText(selectedItem);
                CityActivity.this.area_id = ((Areas) CityActivity.this.areasfrom_api.get(i)).id;
                Log.e(Session.area_id, CityActivity.this.area_id);
                Session.SerAreaId(CityActivity.this, CityActivity.this.area_id);
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }

    public void get_orders() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
         Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/order-history.php").setBodyParameter("member_id", Session.GetUserId(this)).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("orders", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        CityActivity.this.ordersfrom_api.add(new Orders(result.get(i).getAsJsonObject(), CityActivity.this));
                    }
                    CityActivity.this.adapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void get_offers() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
       Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/offers.php").asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("offers", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        CityActivity.this.offersfrom_api.add(new Offers(result.get(i).getAsJsonObject(), CityActivity.this));
                    }
                    CityActivity.this.offersAdapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void get_products(String pro_id) {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/products.php").setBodyParameter("product_id", pro_id).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("productsres", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        CityActivity.this.productsfrom_api.add(new Products(result.get(i).getAsJsonObject(), CityActivity.this));
                        Session.SetCartProducts(CityActivity.this, (Products) CityActivity.this.productsfrom_api.get(i));
                    }
                    Toast.makeText(CityActivity.this, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CityActivity.this, MainActivity.class);
                    intent.putExtra("act", "1");
                    intent.putExtra("id", Session.GetAreaId(CityActivity.this));
                    intent.putExtra(Session.delivery, "0");
                    intent.putExtra(Session.delivery, "1");
                    CityActivity.this.startActivity(intent);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
