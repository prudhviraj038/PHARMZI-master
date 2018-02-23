package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 20-02-2018.
 */

public class CheckoutDeliveryActivity extends Activity {
    String a_id = "-1";
    AddressAdapter addressAdapter;
    ArrayList<String> address_id;
    ArrayList<Address> address_list;
    ListView address_lv;
    EditText address_name;
    LinearLayout address_pop_up;
    ArrayList<String> address_title;
    String area_id;
    TextView area_name;
    ArrayList<Areas> areasfrom_api;
    ImageView back_btn;
    EditText block;
    EditText buil_apartment;
    TextView buil_area;
    EditText buil_block;
    EditText buil_extra_info;
    EditText buil_floor;
    EditText buil_juddah;
    EditText buil_name;
    EditText buil_number;
    TextView buil_save_btn;
    EditText buil_street;
    EditText building_address;
    TextView building_btn;
    EditText extra_details;
    ImageView final_pop_close;
    ViewFlipper flipper;
    EditText hosp_address;
    TextView hosp_area;
    EditText hosp_block;
    EditText hosp_details;
    EditText hosp_juddah;
    EditText hosp_name;
    EditText hosp_number;
    EditText hosp_room_no;
    TextView hosp_save_btn;
    EditText hosp_street;
    EditText hosp_wing;
    TextView hospital_btn;
    EditText house;
    TextView house_btn;
    TextView house_save_btn;
    EditText juddah;
    LinearLayout line;
    EditText message;
    EditText number;
    TextView savedaddress_btn;
    EditText street;
    String title,area_id_;
    TextView st_deliver_to,st_da,st_enter_message,st_enter_da,st_delivering_to,st_or;


    class C03381 implements View.OnClickListener {
        C03381() {
        }

        public void onClick(View view) {
            CheckoutDeliveryActivity.this.line.setBackgroundColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.languagecolor));
            if (CheckoutDeliveryActivity.this.address_list.size() == 0) {
                Toast.makeText(CheckoutDeliveryActivity.this, "No delivery Address for this area", Toast.LENGTH_SHORT).show();
            } else {
                CheckoutDeliveryActivity.this.address_pop_up.setVisibility(View.VISIBLE);

            }
        }
    }

    class C03392 implements View.OnClickListener {
        C03392() {
        }

        public void onClick(View view) {
            CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
            CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
            CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.flipper.setDisplayedChild(0);
        }
    }

    class C03403 implements View.OnClickListener {
        C03403() {
        }

        public void onClick(View view) {
            CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
            CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
            CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.flipper.setDisplayedChild(1);
        }
    }

    class C03414 implements View.OnClickListener {
        C03414() {
        }

        public void onClick(View view) {
            CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
            CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
            CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
            CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
            CheckoutDeliveryActivity.this.flipper.setDisplayedChild(2);
        }
    }




    class C03469 implements View.OnClickListener {
        C03469() {
        }

        public void onClick(View view) {
            CheckoutDeliveryActivity.this.onBackPressed();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_delivery_screen);
        getWindow().setSoftInputMode(3);
        this.address_list = new ArrayList();
        this.areasfrom_api = new ArrayList();
        this.address_title = new ArrayList();
        this.address_id = new ArrayList();
        Session.forceRTLIfSupported(this);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.savedaddress_btn = (TextView) findViewById(R.id.savedaddress_btn);
        this.house_btn = (TextView) findViewById(R.id.house_btn);
        this.building_btn = (TextView) findViewById(R.id.building_btn);
        this.hospital_btn = (TextView) findViewById(R.id.hospital_btn);
        this.address_name = (EditText) findViewById(R.id.address_name);
        this.area_name = (TextView) findViewById(R.id.area_name);
        this.block = (EditText) findViewById(R.id.block);
        this.street = (EditText) findViewById(R.id.street);
        this.juddah = (EditText) findViewById(R.id.juddah);
        this.house = (EditText) findViewById(R.id.house);
        this.extra_details = (EditText) findViewById(R.id.extra_details);
        this.number = (EditText) findViewById(R.id.number);
        this.building_address = (EditText) findViewById(R.id.building_address);
        this.buil_area = (TextView) findViewById(R.id.buil_area);
        this.buil_block = (EditText) findViewById(R.id.buil_block);
        this.buil_street = (EditText) findViewById(R.id.buil_street);
        this.buil_juddah = (EditText) findViewById(R.id.buil_juddah);
        this.buil_name = (EditText) findViewById(R.id.buil_name);
        this.buil_floor = (EditText) findViewById(R.id.buil_floor);
        this.buil_apartment = (EditText) findViewById(R.id.buil_apartment);
        this.buil_extra_info = (EditText) findViewById(R.id.buil_extra_info);
        this.buil_number = (EditText) findViewById(R.id.buil_number);
        this.hosp_address = (EditText) findViewById(R.id.hosp_address);
        this.hosp_area = (TextView) findViewById(R.id.hosp_area);
        this.hosp_block = (EditText) findViewById(R.id.hosp_block);
        this.hosp_street = (EditText) findViewById(R.id.hosp_street);
        this.hosp_juddah = (EditText) findViewById(R.id.hosp_juddah);
        this.hosp_name = (EditText) findViewById(R.id.hosp_name);
        this.hosp_wing = (EditText) findViewById(R.id.hosp_wing);
        this.hosp_room_no = (EditText) findViewById(R.id.hosp_room_no);
        this.hosp_details = (EditText) findViewById(R.id.hosp_details);
        this.hosp_number = (EditText) findViewById(R.id.hosp_number);
        this.house_save_btn = (TextView) findViewById(R.id.house_save_btn);
        this.buil_save_btn = (TextView) findViewById(R.id.buil_save_btn);
        this.hosp_save_btn = (TextView) findViewById(R.id.hosp_save_btn);
        this.flipper = (ViewFlipper) findViewById(R.id.flipper);
        this.address_pop_up = (LinearLayout) findViewById(R.id.address_pop_up);
        this.line = (LinearLayout) findViewById(R.id.line);
        this.address_lv = (ListView) findViewById(R.id.address_list_final);
        this.final_pop_close = (ImageView) findViewById(R.id.final_pop_close);

        this.st_deliver_to = (TextView) findViewById(R.id.st_deliver_to);
        this.st_da = (TextView) findViewById(R.id.st_da);
        this.st_enter_message = (TextView) findViewById(R.id.st_enter_message);
        this.st_enter_da = (TextView) findViewById(R.id.st_enter_da);
        this.st_delivering_to = (TextView) findViewById(R.id.st_delivering_to);
        this.st_or = (TextView) findViewById(R.id.st_or);

        st_deliver_to.setText(Session.GetWord(this,"Deliver to..."));
        st_da.setText(Session.GetWord(this,"Select Delivery Address"));
        st_enter_message.setText(Session.GetWord(this,"Enter Message"));
        st_enter_da.setText(Session.GetWord(this,"Enter Delivery Address"));
        st_delivering_to.setText(Session.GetWord(this,"Where are we delivering to?"));
        st_or.setText(Session.GetWord(this,"OR"));

        this.message = (EditText) findViewById(R.id.message);
        this.addressAdapter = new AddressAdapter(this, this.address_title,this,address_list);
        this.address_lv.setAdapter(this.addressAdapter);
        this.savedaddress_btn.setOnClickListener(new C03381());
        this.house_btn.setBackground(getResources().getDrawable(R.drawable.fill_color));
        this.house_btn.setTextColor(getResources().getColor(R.color.white));
        this.building_btn.setBackground(getResources().getDrawable(R.drawable.rectangle));
        this.building_btn.setTextColor(getResources().getColor(R.color.textcolor));
        this.hospital_btn.setBackground(getResources().getDrawable(R.drawable.rectangle));
        this.hospital_btn.setTextColor(getResources().getColor(R.color.textcolor));
        this.flipper.setDisplayedChild(0);
        this.house_btn.setOnClickListener(new C03392());
        this.building_btn.setOnClickListener(new C03403());
        this.hospital_btn.setOnClickListener(new C03414());
        this.back_btn.setOnClickListener(new C03469());
        this.area_name.setText(Session.GetAreaTitle(this));
        this.buil_area.setText(Session.GetAreaTitle(this));
        this.hosp_area.setText(Session.GetAreaTitle(this));

        savedaddress_btn.setHint("Address book");
        savedaddress_btn.setTextColor(getResources().getColor(R.color.textcolor));
//        area_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CheckoutDeliveryActivity.this,AreaActivity.class);
//                startActivityForResult(intent,1);
//            }
//        });
//        buil_area.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CheckoutDeliveryActivity.this,AreaActivity.class);
//                startActivityForResult(intent,1);
//            }
//        });
//        hosp_area.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CheckoutDeliveryActivity.this,AreaActivity.class);
//                startActivityForResult(intent,1);
//            }
//        });
        this.address_lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int which, long id) {
                savedaddress_btn.setText(address_list.get(which).type);
                if (((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type.equals("House")) {
                    CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
                    CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
                    CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.flipper.setDisplayedChild(0);
                    Session.SetAddressType(CheckoutDeliveryActivity.this, ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type);
                    CheckoutDeliveryActivity.this.a_id = (String) CheckoutDeliveryActivity.this.address_id.get(which);
                    CheckoutDeliveryActivity.this.area_id = ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).area_id;
                    CheckoutDeliveryActivity.this.number.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).phone);
                    CheckoutDeliveryActivity.this.block.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).block);
                    CheckoutDeliveryActivity.this.street.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).street);
                    CheckoutDeliveryActivity.this.juddah.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).jaddah);
                    CheckoutDeliveryActivity.this.house.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).building);
                    CheckoutDeliveryActivity.this.buil_number.setText("");
                    CheckoutDeliveryActivity.this.buil_area.setText("");
                    CheckoutDeliveryActivity.this.buil_block.setText("");
                    CheckoutDeliveryActivity.this.buil_street.setText("");
                    CheckoutDeliveryActivity.this.buil_juddah.setText("");
                    CheckoutDeliveryActivity.this.buil_name.setText("");
                    CheckoutDeliveryActivity.this.buil_apartment.setText("");
                    CheckoutDeliveryActivity.this.hosp_number.setText("");
                    CheckoutDeliveryActivity.this.hosp_area.setText("");
                    CheckoutDeliveryActivity.this.hosp_block.setText("");
                    CheckoutDeliveryActivity.this.hosp_street.setText("");
                    CheckoutDeliveryActivity.this.hosp_juddah.setText("");
                    CheckoutDeliveryActivity.this.hosp_room_no.setText("");
                } else if (((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type.equals("Apartment")) {
                    CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
                    CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
                    CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.flipper.setDisplayedChild(1);
                    Session.SetAddressType(CheckoutDeliveryActivity.this, ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type);
                    CheckoutDeliveryActivity.this.a_id = (String) CheckoutDeliveryActivity.this.address_id.get(which);
                    CheckoutDeliveryActivity.this.area_id = ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).area_id;
                    CheckoutDeliveryActivity.this.buil_number.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).phone);
                    CheckoutDeliveryActivity.this.buil_block.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).block);
                    CheckoutDeliveryActivity.this.buil_street.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).street);
                    CheckoutDeliveryActivity.this.buil_juddah.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).jaddah);
                    CheckoutDeliveryActivity.this.buil_name.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).building);
                    CheckoutDeliveryActivity.this.buil_apartment.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).flat);
                    CheckoutDeliveryActivity.this.number.setText("");
                    CheckoutDeliveryActivity.this.area_name.setText("");
                    CheckoutDeliveryActivity.this.block.setText("");
                    CheckoutDeliveryActivity.this.street.setText("");
                    CheckoutDeliveryActivity.this.juddah.setText("");
                    CheckoutDeliveryActivity.this.house.setText("");
                    CheckoutDeliveryActivity.this.hosp_number.setText("");
                    CheckoutDeliveryActivity.this.hosp_area.setText("");
                    CheckoutDeliveryActivity.this.hosp_block.setText("");
                    CheckoutDeliveryActivity.this.hosp_street.setText("");
                    CheckoutDeliveryActivity.this.hosp_juddah.setText("");
                    CheckoutDeliveryActivity.this.hosp_room_no.setText("");
                } else if (((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type.equals("Hospital")) {
                    CheckoutDeliveryActivity.this.hospital_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.fill_color));
                    CheckoutDeliveryActivity.this.hospital_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.white));
                    CheckoutDeliveryActivity.this.building_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.building_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.house_btn.setBackground(CheckoutDeliveryActivity.this.getResources().getDrawable(R.drawable.rectangle));
                    CheckoutDeliveryActivity.this.house_btn.setTextColor(CheckoutDeliveryActivity.this.getResources().getColor(R.color.textcolor));
                    CheckoutDeliveryActivity.this.flipper.setDisplayedChild(2);
                    Session.SetAddressType(CheckoutDeliveryActivity.this, ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).type);
                    CheckoutDeliveryActivity.this.a_id = (String) CheckoutDeliveryActivity.this.address_id.get(which);
                    CheckoutDeliveryActivity.this.area_id = ((Address) CheckoutDeliveryActivity.this.address_list.get(which)).area_id;
                    CheckoutDeliveryActivity.this.hosp_number.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).phone);
                    CheckoutDeliveryActivity.this.hosp_block.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).block);
                    CheckoutDeliveryActivity.this.hosp_street.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).street);
                    CheckoutDeliveryActivity.this.hosp_juddah.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).jaddah);
                    CheckoutDeliveryActivity.this.hosp_room_no.setText(((Address) CheckoutDeliveryActivity.this.address_list.get(which)).building);
                    CheckoutDeliveryActivity.this.number.setText("");
                    CheckoutDeliveryActivity.this.area_name.setText("");
                    CheckoutDeliveryActivity.this.block.setText("");
                    CheckoutDeliveryActivity.this.street.setText("");
                    CheckoutDeliveryActivity.this.juddah.setText("");
                    CheckoutDeliveryActivity.this.house.setText("");
                    CheckoutDeliveryActivity.this.buil_number.setText("");
                    CheckoutDeliveryActivity.this.buil_area.setText("");
                    CheckoutDeliveryActivity.this.buil_block.setText("");
                    CheckoutDeliveryActivity.this.buil_street.setText("");
                    CheckoutDeliveryActivity.this.buil_juddah.setText("");
                    CheckoutDeliveryActivity.this.buil_name.setText("");
                    CheckoutDeliveryActivity.this.buil_apartment.setText("");
                }
                CheckoutDeliveryActivity.this.address_pop_up.setVisibility(View.GONE);
            }
        });
        this.house_save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String add_name = CheckoutDeliveryActivity.this.address_name.getText().toString();
                String block_string = CheckoutDeliveryActivity.this.block.getText().toString();
                String street_string = CheckoutDeliveryActivity.this.street.getText().toString();
                String juddah_string = CheckoutDeliveryActivity.this.juddah.getText().toString();
                String house_string = CheckoutDeliveryActivity.this.house.getText().toString();
                String extra_details_string = CheckoutDeliveryActivity.this.extra_details.getText().toString();
                String num_string = CheckoutDeliveryActivity.this.number.getText().toString();
                if (add_name.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Address Name", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.address_name.requestFocus();
                } else if (block_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Block", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.block.requestFocus();
                } else if (street_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Street", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.street.requestFocus();
                } else if (juddah_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Juddah", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.juddah.requestFocus();
                } else if (house_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter House", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.house.requestFocus();
                } else if (extra_details_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Extra Details", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.extra_details.requestFocus();
                } else if (num_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.number.requestFocus();
                } else {
                    Intent intent = new Intent(CheckoutDeliveryActivity.this, CheckoutPayment.class);
                    intent.putExtra("block", CheckoutDeliveryActivity.this.block.getText().toString());
                    intent.putExtra("street", CheckoutDeliveryActivity.this.street.getText().toString());
                    intent.putExtra("jaddah", CheckoutDeliveryActivity.this.juddah.getText().toString());
                    intent.putExtra("house", CheckoutDeliveryActivity.this.house.getText().toString());
                    intent.putExtra("extradetails", CheckoutDeliveryActivity.this.extra_details.getText().toString());
                    Log.e("checkdetails1", CheckoutDeliveryActivity.this.extra_details.getText().toString());
                    intent.putExtra(Session.phone, CheckoutDeliveryActivity.this.number.getText().toString());
                    Log.e("checknum", CheckoutDeliveryActivity.this.number.getText().toString());
                    intent.putExtra("message", CheckoutDeliveryActivity.this.message.getText().toString());
                    CheckoutDeliveryActivity.this.startActivity(intent);
                }
            }
        });
        this.buil_save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String add_name = CheckoutDeliveryActivity.this.building_address.getText().toString();
                String block_string = CheckoutDeliveryActivity.this.buil_block.getText().toString();
                String street_string = CheckoutDeliveryActivity.this.buil_street.getText().toString();
                String juddah_string = CheckoutDeliveryActivity.this.buil_juddah.getText().toString();
                String building_name = CheckoutDeliveryActivity.this.buil_name.getText().toString();
                String floor_string = CheckoutDeliveryActivity.this.buil_floor.getText().toString();
                String apartment_string = CheckoutDeliveryActivity.this.buil_apartment.getText().toString();
                String extra_details_string = CheckoutDeliveryActivity.this.buil_extra_info.getText().toString();
                String num_string = CheckoutDeliveryActivity.this.buil_number.getText().toString();
                if (add_name.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Building Address", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.address_name.requestFocus();
                } else if (block_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Block", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_block.requestFocus();
                } else if (street_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Street", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_street.requestFocus();
                } else if (juddah_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Juddah", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_juddah.requestFocus();
                } else if (building_name.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Building Name", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_name.requestFocus();
                } else if (floor_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Floor", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_floor.requestFocus();
                } else if (apartment_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Apartment", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_apartment.requestFocus();
                } else if (extra_details_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Extra Details", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_extra_info.requestFocus();
                } else if (num_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.buil_number.requestFocus();
                } else {
                    Intent intent = new Intent(CheckoutDeliveryActivity.this, CheckoutPayment.class);
                    intent.putExtra("block", CheckoutDeliveryActivity.this.buil_block.getText().toString());
                    intent.putExtra("street", CheckoutDeliveryActivity.this.buil_street.getText().toString());
                    intent.putExtra("jaddah", CheckoutDeliveryActivity.this.buil_juddah.getText().toString());
                    intent.putExtra("house", CheckoutDeliveryActivity.this.buil_name.getText().toString());
                    intent.putExtra("floor", CheckoutDeliveryActivity.this.buil_floor.getText().toString());
                    intent.putExtra("apartment", CheckoutDeliveryActivity.this.buil_apartment.getText().toString());
                    intent.putExtra("buil_details", CheckoutDeliveryActivity.this.buil_extra_info.getText().toString());
                    intent.putExtra(Session.phone, CheckoutDeliveryActivity.this.buil_number.getText().toString());
                    intent.putExtra("message", CheckoutDeliveryActivity.this.message.getText().toString());
                    CheckoutDeliveryActivity.this.startActivity(intent);
                }
            }
        });
        this.hosp_save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String add_name = CheckoutDeliveryActivity.this.hosp_address.getText().toString();
                String block_string = CheckoutDeliveryActivity.this.hosp_block.getText().toString();
                String street_string = CheckoutDeliveryActivity.this.hosp_street.getText().toString();
                String juddah_string = CheckoutDeliveryActivity.this.hosp_juddah.getText().toString();
                String house_string = CheckoutDeliveryActivity.this.hosp_name.getText().toString();
                String wing_string = CheckoutDeliveryActivity.this.hosp_wing.getText().toString();
                String roomno_sring = CheckoutDeliveryActivity.this.hosp_room_no.getText().toString();
                String extra_details_string = CheckoutDeliveryActivity.this.hosp_details.getText().toString();
                String num_string = CheckoutDeliveryActivity.this.hosp_number.getText().toString();
                if (add_name.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Address Name", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_address.requestFocus();
                } else if (block_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Block", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_block.requestFocus();
                } else if (street_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Street", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_street.requestFocus();
                } else if (juddah_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Juddah", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_juddah.requestFocus();
                } else if (house_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Hospital Name", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_name.requestFocus();
                } else if (wing_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Wing", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_wing.requestFocus();
                } else if (roomno_sring.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Room Number", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_room_no.requestFocus();
                } else if (extra_details_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Extra Details", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_details.requestFocus();
                } else if (num_string.equals("")) {
                    Toast.makeText(CheckoutDeliveryActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    CheckoutDeliveryActivity.this.hosp_number.requestFocus();
                } else {
                    Intent intent = new Intent(CheckoutDeliveryActivity.this, CheckoutPayment.class);
                    intent.putExtra("block", CheckoutDeliveryActivity.this.hosp_block.getText().toString());
                    intent.putExtra("street", CheckoutDeliveryActivity.this.hosp_street.getText().toString());
                    intent.putExtra("jaddah", CheckoutDeliveryActivity.this.hosp_juddah.getText().toString());
                    intent.putExtra("house", CheckoutDeliveryActivity.this.hosp_name.getText().toString());
                    intent.putExtra("floor", CheckoutDeliveryActivity.this.hosp_number.getText().toString());
                    intent.putExtra("apartment", CheckoutDeliveryActivity.this.hosp_room_no.getText().toString());
                    intent.putExtra("hosp_details", CheckoutDeliveryActivity.this.hosp_details.getText().toString());
                    intent.putExtra(Session.phone, CheckoutDeliveryActivity.this.hosp_number.getText().toString());
                    intent.putExtra("message", CheckoutDeliveryActivity.this.message.getText().toString());
                    CheckoutDeliveryActivity.this.startActivity(intent);
                }
            }
        });
        get_address();
        get_areas();
    }

    public void get_areas() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
         Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/areas.php").asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    hud.dismiss();
                    Log.e("country", result.toString());
                    for (int i = 0; i < result.size(); i++) {
                        CheckoutDeliveryActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject(), CheckoutDeliveryActivity.this, "0"));
                        for (int j = 0; j < result.get(i).getAsJsonObject().get("areas").getAsJsonArray().size(); j++) {
                            CheckoutDeliveryActivity.this.areasfrom_api.add(new Areas(result.get(i).getAsJsonObject().get("areas").getAsJsonArray().get(j).getAsJsonObject(), CheckoutDeliveryActivity.this, "1"));
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            if (data != null && data.hasExtra("area_title")) {
                this.title = data.getExtras().getString("area_title");
                this.area_id_ = data.getExtras().getString(Session.area_id);
                Log.e("area_title", this.title);
                //this.area_name.setText(this.title);
                //this.hosp_area.setText(this.title);
                //this.buil_area.setText(this.title);
            }
        } else if (resultCode != 0) {
        }
    }

    public Dialog onAreaChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = new CharSequence[this.areasfrom_api.size()];
        for (int i = 0; i < this.areasfrom_api.size(); i++) {
            array[i] = ((Areas) this.areasfrom_api.get(i)).title;
        }
        builder.setTitle("Select Country").setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String selectedItem = array[i].toString();
                Log.e("select", selectedItem);
                CheckoutDeliveryActivity.this.area_name.setText(selectedItem);
                CheckoutDeliveryActivity.this.buil_area.setText(selectedItem);
                CheckoutDeliveryActivity.this.hosp_area.setText(selectedItem);
                CheckoutDeliveryActivity.this.area_id = ((Areas) CheckoutDeliveryActivity.this.areasfrom_api.get(i)).id;
                Log.e(Session.area_id, CheckoutDeliveryActivity.this.area_id);
                Session.SerAreaId(CheckoutDeliveryActivity.this, ((Areas) CheckoutDeliveryActivity.this.areasfrom_api.get(i)).id);
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

    public void get_address() {
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with((Context) this).load("http://clients.mamacgroup.com/sadaleya/api/address-list.php").setBodyParameter("member_id", Session.GetUserId(this)).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            public void onCompleted(Exception e, JsonArray result) {
                try {
                    Log.e("addr", result.toString());
                    hud.dismiss();
                    for (int i = 0; i < result.size(); i++) {
                        JsonObject sub = result.get(i).getAsJsonObject();
                        String ar_id = sub.get("id").getAsString();
                        String ar_name = sub.get("type").getAsString();
                        CheckoutDeliveryActivity.this.address_list.add(new Address(result.get(i).getAsJsonObject(), CheckoutDeliveryActivity.this));
                        CheckoutDeliveryActivity.this.address_id.add(ar_id);
                        CheckoutDeliveryActivity.this.address_title.add(ar_name);
                    }
                    CheckoutDeliveryActivity.this.addressAdapter.notifyDataSetChanged();
                    CheckoutDeliveryActivity.this.addressAdapter = new AddressAdapter(CheckoutDeliveryActivity.this, CheckoutDeliveryActivity.this.address_title,CheckoutDeliveryActivity.this,address_list);
                    CheckoutDeliveryActivity.this.address_lv.setAdapter(CheckoutDeliveryActivity.this.addressAdapter);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

