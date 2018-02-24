package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yellowsoft.pharmzi.Products.Pharmacies;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD.Style;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import java.io.Serializable;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    MainActivity mainActivity;
    TextView sub_total,dc,total_price;
    Float total;
    float total_cart_price = 0.0f;
    int delivery_charges =0;
    CartAdapter cartAdapter;
    ArrayList<Products> productsfrom_api;
    LinearLayout line4,discount,line5,after_discount,browse_btn,checkout_option,check_out_lll;
    TextView check_btn,discount_value,total_discount_price,cancel_btn,login_btn,guest_btn;
    EditText coupon_text;
    String tot;
    ArrayList<Pharmacies> pharmaciesfrom_api;
    String date,time;
    LinearLayout no_cart;
    int previous_postion=0;
    Float delivery_charge;
    TextView st_total,st_subtotal,st_delivery_charges,st_text;
    LinearLayout continue_shopping_ll;

    public static CartFragment newInstance(String someInt) {
        CartFragment myFragment = new CartFragment();

        Bundle args = new Bundle();
        args.putString("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getActivity() fragment
        View view = inflater.inflate(R.layout.cart_screen, container, false);
        Session.forceRTLIfSupported(getActivity());
        mainActivity = (MainActivity) getActivity();
        pharmaciesfrom_api = new ArrayList<>();
        productsfrom_api = new ArrayList<>();
        sub_total = (TextView) view.findViewById(R.id.sub_total);
        dc = (TextView) view.findViewById(R.id.dc);
        total_price = (TextView) view.findViewById(R.id.total);
        check_out_lll = (LinearLayout) view.findViewById(R.id.check_out_lll);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        line4 = (LinearLayout) view.findViewById(R.id.line4);
        discount = (LinearLayout) view.findViewById(R.id.discount);
        line5 = (LinearLayout) view.findViewById(R.id.line5);
        after_discount = (LinearLayout) view.findViewById(R.id.after_discount);
        checkout_option = (LinearLayout) view.findViewById(R.id.checkout_option);
        coupon_text = (EditText) view.findViewById(R.id.coupon_text);
        check_btn = (TextView) view.findViewById(R.id.check_btn);
        discount_value = (TextView) view.findViewById(R.id.discount_value);
        total_discount_price = (TextView) view.findViewById(R.id.total_discount_price);
        browse_btn = (LinearLayout) view.findViewById(R.id.browse_btn);
        no_cart = (LinearLayout) view.findViewById(R.id.no_cart);
        cancel_btn = (TextView) view.findViewById(R.id.cancel_btn);
        login_btn = (TextView) view.findViewById(R.id.login_btn);
        guest_btn = (TextView) view.findViewById(R.id.guest_btn);
        st_total = (TextView) view.findViewById(R.id.st_total);
        st_delivery_charges = (TextView) view.findViewById(R.id.st_delivery_charges);
        st_subtotal = (TextView) view.findViewById(R.id.st_subtotal);
        st_text = (TextView) view.findViewById(R.id.st_text);
        continue_shopping_ll = (LinearLayout) view.findViewById(R.id.continue_shopping_ll);
        check_out_lll = (LinearLayout) view.findViewById(R.id.check_out_lll);
        cartAdapter = new CartAdapter(getActivity(),productsfrom_api,this);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ///dc.setText(delivery_charges+"KD");

        st_total.setText(Session.GetWord(getActivity(),"Total Price"));
        st_delivery_charges.setText(Session.GetWord(getActivity(),"Delivery Charges"));
        st_subtotal.setText(Session.GetWord(getActivity(),"Subtotal"));
        st_text.setText(Session.GetWord(getActivity(),"Delivery charges may vary during check out."));

        check_out_lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session.GetUserId(CartFragment.this.getActivity()).equals("-1")) {
                    CartFragment.this.checkout_option.setVisibility(View.VISIBLE);
                }else {
                    checkout_option.setVisibility(View.GONE);
                    if (Session.GetCartProductType(CartFragment.this.getActivity()).equals(Session.GetDelivery(CartFragment.this.getActivity()))) {
                        CartFragment.this.startActivity(new Intent(CartFragment.this.getActivity(), CheckoutDeliveryActivity.class));
                    }else {
                        checkout1();
                    }
                }
            }
        });

        continue_shopping_ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("act", "0");
                intent.putExtra("id", Session.GetAreaId(getContext()));
                intent.putExtra(Session.delivery, "0");
                Session.SetDelivery(getContext(), Session.delivery);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment.this.login_btn.setTextColor(CartFragment.this.getResources().getColor(R.color.languagecolor));
                CartFragment.this.guest_btn.setTextColor(CartFragment.this.getResources().getColor(R.color.textcolor));
                CartFragment.this.checkout_option.setVisibility(View.GONE);
                CartFragment.this.mainActivity.mViewPager.setCurrentItem(3);
            }
        });

        cancel_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout_option.setVisibility(View.GONE);
            }
        });

        guest_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment.this.login_btn.setTextColor(CartFragment.this.getResources().getColor(R.color.textcolor));
                CartFragment.this.guest_btn.setTextColor(CartFragment.this.getResources().getColor(R.color.languagecolor));
                CartFragment.this.checkout_option.setVisibility(View.GONE);
                CartFragment.this.checkout2();
            }
        });


        line4.setVisibility(View.GONE);
        discount.setVisibility(View.GONE);
        line5.setVisibility(View.GONE);
        after_discount.setVisibility(View.GONE);

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_coupon();
            }
        });

        get_products();

        if (mainActivity.delivery.equals("0")) {
            get_delivery_pharmacies();
        }else if (mainActivity.delivery.equals("1")){
            get_pharmacies();
        }


        return view;
    }


    public  void remove_cart_data(int pos){
        productsfrom_api.remove(pos);
        Session.deleteCart(getActivity());
        if(productsfrom_api.size()==0){
            no_cart.setVisibility(View.VISIBLE);
            browse_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("act", "1");
                    intent.putExtra("id", Session.GetAreaId(getActivity()));
                    intent.putExtra("delivery", "0");
                    intent.putExtra("pickup", "1");
                    startActivity(intent);
                }
            });
        }else{
            no_cart.setVisibility(View.GONE);
        }
        for(int i=0;i<productsfrom_api.size();i++){
            Session.SetCartProducts(getActivity(), productsfrom_api.get(i));
        }
        cartAdapter.notifyDataSetChanged();
        calculate_total_price();

    }


    public void get_products() {
        Log.e("cart_list", String.valueOf(Session.GetCartProducts(getContext()).toString()));
        JsonArray temp;

        temp = Session.GetCartProducts(getContext());
        for (int i=0;i<temp.size();i++){
            Log.e("result",temp.toString());
            Products products = new Products(temp.get(i).getAsJsonObject(),getContext());
            productsfrom_api.add(products);
            Log.e("_price",products.price);
            total_cart_price = total_cart_price + (products.cart_quantity * Float.parseFloat(products.price));
            dc.setText(Session.GetPharmciDc(getActivity()) + " KD ");
            Log.e("delivery_charge",Session.GetPharmciDc(getActivity()));
            delivery_charge = Float.valueOf(Float.parseFloat(Session.GetPharmciDc(getActivity())));
//            sp = String.valueOf(Float.parseFloat(products.shipping_price));


        }

        try {
            this.total = Float.valueOf(this.total_cart_price + this.delivery_charge.floatValue());
            total_discount_price.setText(String.valueOf(total) +"KD");
        }catch (Exception e){
            e.printStackTrace();
        }


        cartAdapter.notifyDataSetChanged();

        if (productsfrom_api.size() == 0){
            no_cart.setVisibility(View.VISIBLE);
            browse_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("act", "0");
                    intent.putExtra("id", Session.GetAreaId(getActivity()));
                    intent.putExtra("delivery", "0");
                    intent.putExtra("pickup", "1");
                    startActivity(intent);
                }
            });
        }else {
            no_cart.setVisibility(View.GONE);
        }
//        grandtotal = Float.parseFloat(shipping_price.getText().toString());
        this.total_price.setText(String.valueOf(this.total) + " KD ");
        sub_total.setText(String.valueOf(total_cart_price)+"KD");
        tot = String.valueOf(total_cart_price + 50);
        if (this.productsfrom_api.size() == 0) {
            this.no_cart.setVisibility(View.VISIBLE);
            this.browse_btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CartFragment.this.getActivity(), MainActivity.class);
                    intent.putExtra("act", "0");
                    intent.putExtra("id", Session.GetAreaId(CartFragment.this.getActivity()));
                    intent.putExtra(Session.delivery, "0");
                    Session.SetDelivery(CartFragment.this.getActivity(), Session.delivery);
                    CartFragment.this.startActivity(intent);
                }
            });
        }else {
            this.no_cart.setVisibility(View.GONE);
        }
    }

    public void calculate_total_price(){

        total_cart_price = 0.0f;
        for (int i=0;i<productsfrom_api.size();i++){
            total_cart_price = total_cart_price + (productsfrom_api.get(i).cart_quantity* Float.parseFloat(productsfrom_api.get(i).price));
        }
        total_price.setText(String.valueOf(total_cart_price)+"KD");
        sub_total.setText(String.valueOf(total_cart_price)+"KD");
        this.total = Float.valueOf(this.total_cart_price + this.delivery_charge.floatValue());
        total_discount_price.setText(String.valueOf(total) +"KD");
    }


    public void check_coupon(){
        String coupon_string = coupon_text.getText().toString();
        if (coupon_string.equals("")){
            Toast.makeText(getContext(),"Please Enter Coupon Code",Toast.LENGTH_SHORT).show();
            coupon_text.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVERURL + "coupon-check.php")
                    .setBodyParameter("coupon", coupon_string)
                    .setBodyParameter("cart_total", String.valueOf(total_cart_price))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("coupon", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(getContext(), result.get("discount_value").getAsString(), Toast.LENGTH_SHORT).show();
                                    line4.setVisibility(View.VISIBLE);
                                    discount.setVisibility(View.VISIBLE);
                                    line5.setVisibility(View.VISIBLE);
                                    after_discount.setVisibility(View.VISIBLE);
                                    discount_value.setText(result.get("discount_value").getAsString()+"KD");
                                    // Float totalprice = Float.parseFloat(total_price.getText().toString());
                                    Float discountprice = Float.parseFloat(result.get("discount_value").getAsString());
                                    total_cart_price=total_cart_price-discountprice;
                                    CartFragment.this.total = Float.valueOf(CartFragment.this.total_cart_price + CartFragment.this.delivery_charge.floatValue());
                                    total_discount_price.setText(String.valueOf(total) +"KD");
//                                    total_discount_price.setText(String.valueOf(total_cart_price + delivery_charges - discountprice) +"KD");

                                } else {
                                    Toast.makeText(getContext(), result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        }
                    });
        }
    }

    public void checkout(){


        if (coupon_text.getText().toString().equals("")){
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            intent.putExtra("total", String.valueOf(total));
            intent.putExtra("dc", String.valueOf(delivery_charges));
            intent.putExtra("text","");
            intent.putExtra("dv","");
            intent.putExtra("date",date);
            intent.putExtra("time",time);
            for (int i=0;i<pharmaciesfrom_api.size();i++) {
                intent.putExtra("pharmcies", pharmaciesfrom_api.get(i));
            }
//            intent.putExtra("lpath",productsfrom_api);
//            Log.e("print", localpath);
            startActivity(intent);

        }else {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            intent.putExtra("total", String.valueOf(total));
            intent.putExtra("dc", String.valueOf(delivery_charges));
            intent.putExtra("text",coupon_text.getText().toString());
            intent.putExtra("dv",discount_value.getText().toString());
            intent.putExtra("date",date);
            intent.putExtra("time",time);
            for (int i=0;i<pharmaciesfrom_api.size();i++) {
                intent.putExtra("pharmcies", pharmaciesfrom_api.get(i));
            }
//            intent.putExtra("lpath",localpath);
//            Log.e("print", localpath);
            startActivity(intent);
        }
    }


    public void get_pharmacies(){
        Ion.with(getContext())
                .load(Session.SERVERURL+"pharmacies.php")
                .setBodyParameter("pickup",mainActivity.area_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("pickuppharm",result.toString());
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                            }
                            cartAdapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_delivery_pharmacies(){
        Ion.with(getContext())
                .load(Session.SERVERURL+"pharmacies.php")
                .setBodyParameter("delivery",mainActivity.area_id)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            Log.e("homepharmacies",result.toString());
                            for (int i=0;i<result.size();i++) {
                                Pharmacies pharmacies = new Pharmacies(result.get(i).getAsJsonObject(),getContext());
                                pharmaciesfrom_api.add(pharmacies);
                            }
                            cartAdapter.notifyDataSetChanged();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public void checkout2() {
        if (this.coupon_text.getText().toString().equals("")) {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            intent.putExtra("total", String.valueOf(this.total));
            intent.putExtra("dc", String.valueOf(this.delivery_charges));
            intent.putExtra("text", "");
            intent.putExtra("dv", "");
            intent.putExtra("products", this.productsfrom_api);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            intent.putExtra("total", String.valueOf(this.total));
            intent.putExtra("dc", String.valueOf(this.delivery_charges));
            intent.putExtra("text", this.coupon_text.getText().toString());
            intent.putExtra("dv", this.discount_value.getText().toString());
            intent.putExtra("products", this.productsfrom_api);
            startActivity(intent);
        }
    }


    public void checkout1() {
        if (this.coupon_text.getText().toString().equals("")) {
            Intent intent = new Intent(getContext(), CheckoutPickupActivity.class);
            intent.putExtra("total", String.valueOf(this.total));
            intent.putExtra("text", "");
            intent.putExtra("dv", "");
            intent.putExtra("pharmcies", this.pharmaciesfrom_api);
            intent.putExtra("products",productsfrom_api);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getContext(), CheckoutPickupActivity.class);
            intent.putExtra("total", String.valueOf(this.total));
            intent.putExtra("text", this.coupon_text.getText().toString());
            intent.putExtra("dv", this.discount_value.getText().toString());
            intent.putExtra("pharmcies", this.pharmaciesfrom_api);
            intent.putExtra("products",productsfrom_api);
            startActivity(intent);
        }
    }




}
