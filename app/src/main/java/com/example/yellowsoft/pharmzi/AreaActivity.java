package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 20-02-2018.
 */

public class AreaActivity extends Activity {
    AreaAdapter adapter;
    ArrayList<Areas> areasfrom_api;
    ImageView back_btn;
    TextView cancel_btn;
    ListView listView;
    EditText search;
    TextView st_search;

    class C03051 implements View.OnClickListener {
        C03051() {
        }

        public void onClick(View view) {
            AreaActivity.this.onBackPressed();
        }
    }

    class C03062 implements View.OnClickListener {
        C03062() {
        }

        public void onClick(View view) {
            AreaActivity.this.search.setText("");
        }
    }

    class C03073 implements TextWatcher {
        C03073() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (AreaActivity.this.adapter != null) {
                AreaActivity.this.adapter.getFilter().filter(charSequence);
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_screen);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(3);
        areasfrom_api = new ArrayList();
        listView = (ListView) findViewById(R.id.areas_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        search = (EditText) findViewById(R.id.search);
        st_search = (TextView) findViewById(R.id.st_search);
        adapter = new AreaAdapter(this, this.areasfrom_api, this);
        listView.setAdapter(adapter);
        back_btn.setOnClickListener(new C03051());
        cancel_btn.setOnClickListener(new C03062());
        cancel_btn.setText(Session.GetWord(this,"Cancel"));
        st_search.setText(Session.GetWord(this,"Search"));
        search.setHint(Session.GetWord(this,"Select Area"));
        search.setText("");
        search.setTextColor(getResources().getColor(R.color.text_color));
        search.addTextChangedListener(new C03073());
        get_areas();
    }

    public void get_areas() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        (Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/areas.php")).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("country", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        AreaActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject(), AreaActivity.this, "0"));
                        for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {
                            AreaActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), AreaActivity.this, "1"));
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

