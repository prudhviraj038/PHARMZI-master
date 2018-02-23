package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnSingleCallbackConfirmListener;
import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 22/1/18.
 */

public class ProductsListActivity  extends AppCompatActivity {
    ImageView back_btn,cart;
    TextView cat_title;
    RecyclerView recyclerView;
    LinearLayout refine_btn,list_btn;
    boolean isViewWithCatalog = true;
    ProductListAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Products> productsfrom_api;
    String cat_id,title;
    ViewFlipper viewFlipper;
    String header;
    LinearLayout search,layout,line1,line2;
    String phar_title,phar_title_ar;
    ImageView sort_image,refine_image,list_image,close_btn,search_text;
    TextView apply_btn,newest_option,title_option,distance_option,filter,cart_items;
    LinearLayout title_btn,newest_btn,distance_btn,filter_btn,filter_popup,sort_btn;
    EditText search_edit;
    String titletext,text,title_ar;
    RelativeLayout cart_ll;
    TextView sort_text,refine_text,list_text,title_text;
    LinearLayout title_check;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4;
    String sorting_option,phar_id;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);
        Session.forceRTLIfSupported(this);
        productsfrom_api = new ArrayList<>();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        header = getIntent().getStringExtra("header");
        cat_id =  getIntent().getStringExtra("catid");
        title = getIntent().getStringExtra("cat_title");
        title_ar = getIntent().getStringExtra("cat_title_ar");
        phar_id = getIntent().getStringExtra("phar_id");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        cart = (ImageView) findViewById(R.id.cart);
        cat_title = (TextView) findViewById(R.id.cat_title);
        list_text = (TextView) findViewById(R.id.list_text);
        sort_text = (TextView) findViewById(R.id.sort_text);
        refine_text = (TextView) findViewById(R.id.refine_text);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        sort_btn = (LinearLayout) findViewById(R.id.sort_btn);
        refine_btn = (LinearLayout) findViewById(R.id.refine_btn);
        viewFlipper = (ViewFlipper) findViewById(R.id.view);
        search = (LinearLayout) findViewById(R.id.search);
        layout = (LinearLayout) findViewById(R.id.layout);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        sort_image = (ImageView) findViewById(R.id.sort_image);
        refine_image = (ImageView) findViewById(R.id.refine_image);
        list_image = (ImageView) findViewById(R.id.list_image);
        apply_btn = (TextView) findViewById(R.id.apply_btn);
//        title_option = (TextView) findViewById(R.id.title_option);
//        newest_option = (TextView) findViewById(R.id.newest_option);
//        title_btn = (LinearLayout) findViewById(R.id.title_btn);
//        newest_btn = (LinearLayout) findViewById(R.id.newest_btn);
        filter_popup = (LinearLayout) findViewById(R.id.filter_popup);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        sort_btn = (LinearLayout) findViewById(R.id.sort_btn);
        search_edit = (EditText) findViewById(R.id.search_edit);
        search_text = (ImageView) findViewById(R.id.search_text);
        cart_items = (TextView) findViewById(R.id.cart_items);
        cart_ll = (RelativeLayout) findViewById(R.id.cart_ll);
        sort_text = (TextView) findViewById(R.id.sort_text);
        refine_text = (TextView) findViewById(R.id.refine_text);
        list_text = (TextView) findViewById(R.id.list_text);
        title_check = (LinearLayout) findViewById(R.id.title_check);
        title_text = (TextView) findViewById(R.id.title_text);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);

        sort_text.setText(Session.GetWord(this,"SORT BY"));
        refine_text.setText(Session.GetWord(this,"REFINE"));
        list_text.setText(Session.GetWord(this,"LIST"));
        search_edit.setFocusableInTouchMode(false);

        list_btn = (LinearLayout) findViewById(R.id.list_btn);
        adapter = new ProductListAdapter(this,productsfrom_api,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);


        cart_items.setText(String.valueOf(Session.GetCartProducts(this).size()));
        cart.setColorFilter(getResources().getColor(R.color.white));
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("enter","entered");
                Intent intent = new Intent(ProductsListActivity.this, MainActivity.class);
                intent.putExtra("act", "1");
                intent.putExtra("id", Session.GetAreaId(ProductsListActivity.this));
                intent.putExtra("delivery", "0");
                intent.putExtra("pickup", "1");
                startActivity(intent);
            }
        });




        if (header.equals("0")){
            search.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            if (Session.GetLang(this).equals("en")) {
                cat_title.setText(phar_title);
                Log.e("phartitle",cat_title.getText().toString());
            }else {
                cat_title.setText(phar_title_ar);

            }

            viewFlipper.setDisplayedChild(3);
            isViewWithCatalog = !isViewWithCatalog;
            supportInvalidateOptionsMenu();
            recyclerView.setLayoutManager(isViewWithCatalog ? new LinearLayoutManager(ProductsListActivity.this) : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);
        }else if (header.equals("1")){
            search.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            if (Session.GetLang(this).equals("en")) {
                cat_title.setText(title);
            }else {
                cat_title.setText(title_ar);

            }
            list_text.setTextColor(getResources().getColor(R.color.languagecolor));
            list_image.setColorFilter(getResources().getColor(R.color.languagecolor));
            sort_text.setTextColor(getResources().getColor(R.color.text_color));
            sort_image.setColorFilter(getResources().getColor(R.color.text_color));
            refine_text.setTextColor(getResources().getColor(R.color.text_color));
            refine_image.setColorFilter(getResources().getColor(R.color.text_color));
            viewFlipper.setDisplayedChild(3);
            isViewWithCatalog = !isViewWithCatalog;
            supportInvalidateOptionsMenu();
            recyclerView.setLayoutManager(isViewWithCatalog ? new LinearLayoutManager(ProductsListActivity.this) : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);
        }

        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_text.setTextColor(getResources().getColor(R.color.text_color));
                list_image.setColorFilter(getResources().getColor(R.color.text_color));
                sort_text.setTextColor(getResources().getColor(R.color.languagecolor));
                sort_image.setColorFilter(getResources().getColor(R.color.languagecolor));
                refine_text.setTextColor(getResources().getColor(R.color.text_color));
                refine_image.setColorFilter(getResources().getColor(R.color.text_color));
                if (filter_popup.isShown()){
                    filter_popup.setVisibility(View.GONE);
                }else {
                    filter_popup.setVisibility(View.VISIBLE);
                }
            }
        });

        refine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_text.setTextColor(getResources().getColor(R.color.text_color));
                list_image.setColorFilter(getResources().getColor(R.color.text_color));
                sort_text.setTextColor(getResources().getColor(R.color.text_color));
                sort_image.setColorFilter(getResources().getColor(R.color.text_color));
                refine_text.setTextColor(getResources().getColor(R.color.languagecolor));
                refine_image.setColorFilter(getResources().getColor(R.color.languagecolor));
                search_edit.setFocusableInTouchMode(true);
                search_edit.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_edit, InputMethodManager.SHOW_IMPLICIT);
            }
        });





        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_text.setTextColor(getResources().getColor(R.color.languagecolor));
                list_image.setColorFilter(getResources().getColor(R.color.languagecolor));
                sort_text.setTextColor(getResources().getColor(R.color.text_color));
                refine_text.setTextColor(getResources().getColor(R.color.text_color));
                sort_image.setColorFilter(getResources().getColor(R.color.text_color));
                refine_text.setTextColor(getResources().getColor(R.color.text_color));
                refine_image.setColorFilter(getResources().getColor(R.color.text_color));
                viewFlipper.setDisplayedChild(3);
                isViewWithCatalog = !isViewWithCatalog;
                supportInvalidateOptionsMenu();
                //loading = false;
                recyclerView.setLayoutManager(isViewWithCatalog ? new LinearLayoutManager(ProductsListActivity.this) : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductsListActivity.this.onBackPressed();
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter_popup.setVisibility(View.GONE);
            }
        });

        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_edit.requestFocus();
                search_edit.setFocusableInTouchMode(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_edit, InputMethodManager.SHOW_IMPLICIT);
                text  = search_edit.getText().toString();
                Log.e("text",text);
                productsfrom_api.clear();
                adapter.notifyDataSetChanged();
                get_products();
                Log.e("textt", search_edit.getText().toString());
            }
        });



        search_edit.setText("");










        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                titletext = title_option.getText().toString();
//                Log.e("title",title);
//                filter_popup.setVisibility(View.GONE);
//                productsfrom_api.clear();
//                adapter.notifyDataSetChanged();
//                get_products();
//                Log.e("titl", title_option.getText().toString());
            }
        });



       // title_option.setText("");

//        title_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                title_dialog();
//            }
//        });
//
//        newest_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newest_dialog();
//            }
//        });
//
//        newest_option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newest_dialog();
//            }
//        });
//
//        title_option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                title_dialog();
//            }
//        });



        get_products();


        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (checkbox1.isChecked()){
                    checkbox1.setChecked(true);
                    sorting_option = "TASC";
                    productsfrom_api.clear();
                    get_products();
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                      @Override
                      public void run() {
                      filter_popup.setVisibility(View.GONE);
                      }
                     }, 1500);
                 }else {
                     checkbox1.setChecked(false);
                 }
              }
          }
        );

        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox2.isChecked()) {
                    checkbox2.setChecked(true);
                    sorting_option = "DESC";
                    productsfrom_api.clear();
                    get_products();
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox2.setChecked(false);
                }
            }
        });

        checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox3.isChecked()) {
                    checkbox3.setChecked(true);
                    sorting_option = "TASC";
                    productsfrom_api.clear();
                    get_products();
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox3.setChecked(false);
                }
            }
        });

        checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox4.isChecked()) {
                    checkbox4.setChecked(true);
                    sorting_option = "TDESC";
                    productsfrom_api.clear();
                    get_products();
                    adapter.notifyDataSetChanged();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            filter_popup.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else {
                    checkbox4.setChecked(false);
                }
            }
        });



    }






    public void title_dialog(){
        final String[] titles={"TASC","TDESC"};
        final PanterDialog panterDialog = new PanterDialog(this);
        panterDialog.setHeaderBackground(R.color.languagecolor)
                .setTitle("Select Title range")
                .setNegative("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        panterDialog.dismiss();
                    }
                })
                .setPositive("Ok")
                .setDialogType(DialogType.SINGLECHOICE)
                .items(titles, new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        if (titles[pos].equals("TASC")){
                            title_option.setText("TASC");
                        }else if (titles[pos].equals("TDESC")){
                            title_option.setText("TDESC");
                        }
                    }
                });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }



    public void newest_dialog(){
        final String[] newests={"ASC","DESC"};
        final PanterDialog panterDialog = new PanterDialog(this);
        panterDialog.setHeaderBackground(R.color.languagecolor)
                .setTitle("Select Newest Range")
                .setNegative("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        panterDialog.dismiss();
                    }
                })
                .setPositive("Ok")
                .setDialogType(DialogType.SINGLECHOICE)
                .items(newests, new OnSingleCallbackConfirmListener() {
                    @Override
                    public void onSingleCallbackConfirmed(PanterDialog dialog, int pos, String text) {
                        if (newests[pos].equals("ASC")){
                            newest_option.setText("ASC");
                        }else if (newests[pos].equals("DESC")){
                            newest_option.setText("DESC");
                        }
                    }
                });
        panterDialog.isCancelable(false);
        panterDialog.show();
    }


    public void get_products(){
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setMaxProgress(100)
                .show();
        Ion.with(this)
                .load(Session.SERVERURL+"products.php")
                .setBodyParameter("category",cat_id)
                .setBodyParameter("pharmacy",phar_id)
                .setBodyParameter("search",text)
                .setBodyParameter("sorting",sorting_option)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            hud.dismiss();
                            Log.e("productsres",result.toString());
                            Log.e("pharmcy_id",phar_id);
                            for (int i=0;i<result.size();i++) {
                                Products products = new Products(result.get(i).getAsJsonObject(),ProductsListActivity.this);
                                productsfrom_api.add(products);

                            }
                            adapter.notifyDataSetChanged();

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }

                    }
                });
    }
}
