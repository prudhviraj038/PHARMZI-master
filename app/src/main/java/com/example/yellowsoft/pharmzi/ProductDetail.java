package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yellowsoft.pharmzi.Products.Pharmacies;
import java.util.ArrayList;

public class ProductDetail extends Activity {
    Runnable Update;
    TextView add;
    String area_id;
    ImageView back_btn;
    long back_pressed;
    LinearLayout buy_now_ll;
    ImageView cart_btn;
    TextView cart_items;
    RelativeLayout cart_ll;
    ImageView close_pop_share;
    ImageView fb_btn;
    ImageView insta_btn;
    MainActivity mainActivity;
    TextView minus;
    String pharid;
    String pharmcy_id;
    ArrayList<Products> pro;
    ProductImageSlider productImageSlider;
    TextView product_about;
    TextView product_description;
    TextView product_instructions;
    TextView product_name;
    TextView product_price;
    Products products;
    TextView quantity;
    ImageView share_btn;
    LinearLayout share_pop_ll;
    TextView title;
    float total_cart_price = 0.0f;
    ImageView twitter_btn;
    String type;
    ViewPager viewPager;
    TextView addtocart_btn;

    class C04321 implements OnClickListener {
        C04321() {
        }

        public void onClick(View view) {
            ProductDetail.this.onBackPressed();
        }
    }

    class C04332 implements OnClickListener {
        C04332() {
        }

        public void onClick(View view) {
            Intent intent = new Intent(ProductDetail.this, MainActivity.class);
            intent.putExtra("act", "1");
            intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
            intent.putExtra("delivery", "0");
            Session.SetDelivery(ProductDetail.this, Session.delivery);
            ProductDetail.this.startActivity(intent);
        }
    }

    class C04343 implements OnClickListener {
        C04343() {
        }

        public void onClick(View view) {
            ProductDetail.this.share_pop_ll.setVisibility(View.VISIBLE);
        }
    }

    class C04354 implements OnClickListener {
        C04354() {
        }

        public void onClick(View view) {
            ProductDetail.this.share_pop_ll.setVisibility(View.GONE);
        }
    }

    class C04376 implements OnClickListener {
        C04376() {
        }

        public void onClick(View view) {
            ProductDetail.this.share_pop_ll.setVisibility(View.VISIBLE);
        }
    }

    class C04387 implements OnClickListener {
        C04387() {
        }

        public void onClick(View view) {
            if (ProductDetail.this.products.cart_quantity < 10) {
                Products products = ProductDetail.this.products;
                products.cart_quantity++;
                ProductDetail.this.quantity.setText(String.valueOf(ProductDetail.this.products.cart_quantity));
            }
        }
    }

    class C04398 implements OnClickListener {
        C04398() {
        }

        public void onClick(View view) {
            if (ProductDetail.this.products.cart_quantity > 1) {
                Products products = ProductDetail.this.products;
                products.cart_quantity--;
                ProductDetail.this.quantity.setText(String.valueOf(ProductDetail.this.products.cart_quantity));
            }
        }
    }

    class C04409 implements OnClickListener {
        C04409() {
        }

        public void onClick(View view) {
            if (Session.GetCartProductType(ProductDetail.this).equals(Session.GetDelivery(ProductDetail.this))) {
                ProductDetail.this.cart_function();
            } else {
                ProductDetail.this.cart_function();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_screen);
        Session.forceRTLIfSupported(this);
        this.pro = new ArrayList();
        this.back_btn = (ImageView) findViewById(R.id.back_img_pd);
        this.product_name = (TextView) findViewById(R.id.product_name_pd_tv);
        this.product_price = (TextView) findViewById(R.id.product_price_pd_tv);
        this.title = (TextView) findViewById(R.id.title);
        this.product_about = (TextView) findViewById(R.id.product_about);
        this.product_description = (TextView) findViewById(R.id.product_description);
        this.product_instructions = (TextView) findViewById(R.id.product_instructions);
        this.share_btn = (ImageView) findViewById(R.id.share_btn);
        this.share_pop_ll = (LinearLayout) findViewById(R.id.share_pop_ll);
        this.buy_now_ll = (LinearLayout) findViewById(R.id.buy_now_ll);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.minus = (TextView) findViewById(R.id.minus);
        this.addtocart_btn = (TextView) findViewById(R.id.addtocart_btn);
        this.add = (TextView) findViewById(R.id.add);
        this.quantity = (TextView) findViewById(R.id.quantity);
        this.cart_btn = (ImageView) findViewById(R.id.cart_btn);
        this.close_pop_share = (ImageView) findViewById(R.id.close_pop_share);
        this.cart_items = (TextView) findViewById(R.id.cart_items);
        this.cart_ll = (RelativeLayout) findViewById(R.id.cart_ll);
        this.back_btn.setOnClickListener(new C04321());
        if (getIntent() != null && getIntent().hasExtra("products")) {
            this.products = (Products) getIntent().getSerializableExtra("products");
        }
        this.pharmcy_id = getIntent().getStringExtra("phar_id");
        this.cart_items.setText(String.valueOf(Session.GetCartProducts(this).size()));
        this.cart_btn.setOnClickListener(new C04332());
        this.share_btn.setOnClickListener(new C04343());
        this.close_pop_share.setOnClickListener(new C04354());
        if (Session.GetLang(this).equals("en")) {
            this.product_name.setText(this.products.title);
            this.title.setText(this.products.title);
            this.product_about.setText(Html.fromHtml(this.products.about));
            this.product_description.setText(Html.fromHtml(this.products.description));
            this.product_instructions.setText(Html.fromHtml(this.products.instructions));
        } else {
            this.product_name.setText(this.products.title_ar);
            this.title.setText(this.products.title_ar);
            this.product_about.setText(Html.fromHtml(this.products.about_ar));
            this.product_description.setText(Html.fromHtml(this.products.description_ar));
            this.product_instructions.setText(Html.fromHtml(this.products.instructions_ar));
        }

        addtocart_btn.setText(Session.GetWord(this,"Add to Cart"));
        this.product_price.setText(this.products.price + " KD ");
        this.productImageSlider = new ProductImageSlider(this, this.products);
        this.viewPager.setAdapter(this.productImageSlider);
        final Handler handler = new Handler();
        this.Update = new Runnable() {
            public void run() {
                if (ProductDetail.this.viewPager.getCurrentItem() < ProductDetail.this.products.images.size() - 1) {
                    ProductDetail.this.viewPager.setCurrentItem(ProductDetail.this.viewPager.getCurrentItem() + 1);
                } else {
                    ProductDetail.this.viewPager.setCurrentItem(0);
                }
                handler.postDelayed(ProductDetail.this.Update, 2000);
            }
        };
        this.Update.run();
        this.share_btn.setOnClickListener(new C04376());
        this.add.setOnClickListener(new C04387());
        this.minus.setOnClickListener(new C04398());
        this.buy_now_ll.setOnClickListener(new C04409());
    }

    public void get_cart() {
        Log.e("piccc", String.valueOf(Session.GetDelivery(this).equals("1")));
        Session.SetCartPharmciId(this, ((Pharmacies) this.products.pharmacies.get(0)).id);
        Session.SetCartProductType(this, Session.GetDelivery(this));
        Log.e("cart_type", String.valueOf(Session.GetCartProductType(this)));
        Log.e("detail_phar_id1", String.valueOf(Session.GetCartPharmciId(this)));
        Log.e("phhhhhhh", String.valueOf(Session.GetPharmciId(this).equals(((Pharmacies) this.products.pharmacies.get(0)).id)));
        Session.SetCartProducts(this, this.products);
        Log.e("cart_products_size", String.valueOf(Session.GetCartProducts(this).size()));
        Toast.makeText(this, "Product is added to the Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("act", "1");
        intent.putExtra("id", Session.GetAreaId(this));
        intent.putExtra(Session.delivery, "0");
        Session.SetDelivery(this, Session.delivery);
        startActivity(intent);
    }

    public void cart_function() {
        runOnUiThread(new Runnable() {

            class C04301 implements DialogInterface.OnClickListener {

                class C04291 implements Runnable {

                    class C04271 implements DialogInterface.OnClickListener {
                        C04271() {
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }

                    class C04282 implements DialogInterface.OnClickListener {
                        C04282() {
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            Session.deleteCart(ProductDetail.this);
                            Session.SetCartProducts(ProductDetail.this, ProductDetail.this.products);
                            Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                            intent.putExtra("act", "1");
                            intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
                            intent.putExtra(Session.delivery, "0");
                            Session.SetDelivery(ProductDetail.this, Session.delivery);
                            ProductDetail.this.startActivity(intent);
                        }
                    }

                    C04291() {
                    }

                    public void run() {
                        if (!ProductDetail.this.isFinishing()) {
                            new Builder(ProductDetail.this).setTitle("Alert").setMessage("You are in Different Pharmacy, Selected cart products will be removed").setCancelable(false).setPositiveButton("ok", new C04282()).setNegativeButton("cancel", new C04271()).show();
                        }
                    }
                }

                C04301() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (Session.GetCartPharmciId(ProductDetail.this).equals("-1")) {
                        if (!Session.GetCartProductType(ProductDetail.this).equals(Session.GetDelivery(ProductDetail.this))) {
                            Session.deleteCart(ProductDetail.this);
                        }
                        ProductDetail.this.get_cart();
                    } else if (Session.GetPharmciId(ProductDetail.this).equals(Session.GetCartPharmciId(ProductDetail.this))) {
                        if (!Session.GetCartProductType(ProductDetail.this).equals(Session.GetDelivery(ProductDetail.this))) {
                            Session.deleteCart(ProductDetail.this);
                        }
                        ProductDetail.this.get_cart();
                    } else {
                        Log.e("detail_phar_id", String.valueOf(Session.GetPharmciId(ProductDetail.this) + String.valueOf(Session.GetCartPharmciId(ProductDetail.this))));
                        ProductDetail.this.runOnUiThread(new C04291());
                    }
                }
            }

            class C04312 implements DialogInterface.OnClickListener {
                C04312() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                    intent.putExtra("act", "0");
                    intent.putExtra("id", Session.GetAreaId(ProductDetail.this));
                    intent.putExtra(Session.delivery, "0");
                    Session.SetDelivery(ProductDetail.this, Session.delivery);
                    ProductDetail.this.startActivity(intent);
                }
            }

            public void run() {
                if (!ProductDetail.this.isFinishing()) {
                    new Builder(ProductDetail.this).setTitle("Alert").setCancelable(false).setPositiveButton("Continue Shopping", new C04312()).setNegativeButton("Checkout", new C04301()).show();
                }
            }
        });
    }
}
