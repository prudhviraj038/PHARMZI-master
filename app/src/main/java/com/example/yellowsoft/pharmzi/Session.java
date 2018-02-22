package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.Locale;
import org.json.JSONObject;

public class Session {
    public static String PAYMENT_URL = "http://clients.mamacgroup.com/sadaleya/api/Tap.php?";
    public static final String SERVERURL = "http://clients.mamacgroup.com/sadaleya/api/";
    public static final String Words_ar = "ar";
    public static final String Words_en = "en";
    public static final String address_type = "address_type";
    public static final String area_id = "area_id";
    public static final String cart_phar_id = "cart_phar_id";
    public static final String cart_product_type = "cart_product_type";
    public static final String cart_products = "cart_products";
    public static final String cat_id = "cat_id";
    public static final String delivery = "delivery";
    public static final String email = "email";
    public static final String fname = "fname";
    public static final String lang = "lan";
    public static final String lat = "lat";
    public static final String lname = "lname";
    public static final String longitude = "longitude";
    public static final String mem_id = "mem_id";
    public static final String p_dc = "p_dc";
    public static final String p_id = "p_id";
    public static final String pahr_title = "phar_title";
    public static final String pahr_title_ar = "phar_title_ar";
    public static final String phone = "phone";
    public static final String pro_title = "pro_title";
    public static final String title = "title";
    public static final String area_title_ar = "title_ar";

    public static void SetUserId(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(mem_id, id);
        editor.commit();
    }

    public static String GetUserId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(mem_id, "-1");
    }

    public static void SerAreaId(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(area_id, id);
        editor.commit();
    }

    public static String GetAreaId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(area_id, "-1");
    }

    public static void SetCatId(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(cat_id, id);
        editor.commit();
    }

    public static String GetCatId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(cat_id, "-1");
    }

    public static void SetCartProducts(Context context, Products cart_product) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = GetCartProducts(context);
        Log.e("adding_pro", String.valueOf(jsonArray.size()));
        jsonArray.add(cart_product.getJson());
        Log.e("adding_pro", String.valueOf(jsonArray.size()));
        Log.e("array_info", jsonArray.toString());
        editor.putString(cart_products, jsonArray.toString());
        editor.apply();
    }

    public static JsonArray GetCartProducts(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = new JsonArray();
        try {
            jsonArray = (JsonArray) jsonParser.parse(sharedPreferences.getString(cart_products, "[]"));
            Log.e("dfd", jsonArray.toString());
            return jsonArray;
        } catch (Exception rx) {
            rx.printStackTrace();
            return jsonArray;
        }
    }

    public static void deleteCart(Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(cart_products, "[]");
        editor.putString(p_id, "-1");
        editor.putString(cart_phar_id, "-1");
        editor.apply();
    }

    public static void SetPharmciId(Context context, String id, String dc) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(p_id, id);
        editor.putString(p_dc, dc);
        editor.commit();
    }

    public static String GetPharmciId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(p_id, "-1");
    }

    public static String GetPharmciDc(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(p_dc, "0");
    }

    public static void SetCartPharmciId(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(cart_phar_id, id);
        editor.commit();
    }

    public static String GetCartPharmciId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(cart_phar_id, "-1");
    }

    public static void SetLatitude(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(lat, id);
        editor.commit();
    }

    public static String GetLatitude(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(lat, "-1");
    }

    public static void SetLongitude(Context context, String id) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(longitude, id);
        editor.commit();
    }

    public static String GetLongitude(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(longitude, "-1");
    }

    public static void SetAreaTitle(Context context, String area_title) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(title, area_title);
        editor.commit();
    }

    public static String GetAreaTitle(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(title, "0");
    }

    public static void SetAreaTitleAr(Context context, String title_ar) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(area_title_ar, title_ar);
        editor.commit();
    }

    public static String GetAreaTitleAr(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(title, "0");
    }

    public static void SetPharmciTitle(Context context, String phar_title) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(phar_title, phar_title);
        editor.commit();
    }

    public static String GetPharmciTitle(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(pahr_title, "0");
    }

    public static void SetPharmciTitleAr(Context context, String phar_title_ar) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(phar_title_ar, phar_title_ar);
        editor.commit();
    }

    public static String GetPharmciTitleAr(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(pahr_title_ar, "0");
    }

    public static void SetFname(Context context, String name) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(fname, name);
        editor.commit();
    }

    public static String GetFname(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(fname, "0");
    }

    public static void SetLname(Context context, String Lname) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(lname, Lname);
        editor.commit();
    }

    public static String GetLname(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(lname, "0");
    }

    public static void SetEmail(Context context, String Email) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("email", Email);
        editor.commit();
    }

    public static String GetEmail(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("email", "0");
    }

    public static void SetPhone(Context context, String Phone) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(phone, Phone);
        editor.commit();
    }

    public static String GetPhone(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(phone, "0");
    }

    public static void SetProductTitle(Context context, String title) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(pro_title, title);
        editor.commit();
    }

    public static String GetProductTitle(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(pro_title, "0");
    }

    public static void SetDelivery(Context context, String deli) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(delivery, deli);
        editor.commit();
    }

    public static String GetDelivery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(delivery, "0");
    }

    public static void SetCartProductType(Context context, String type) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(cart_product_type, type);
        editor.commit();
    }

    public static String GetCartProductType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(cart_product_type, "0");
    }

    public static void SetAddressType(Context context, String type) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(address_type, type);
        editor.commit();
    }

    public static String GetAddressType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(address_type, "0");
    }

    public static void forceRTLIfSupported(Activity activity) {
        Log.e(lang, PreferenceManager.getDefaultSharedPreferences(activity).getString(lang, "-1"));
        Resources res;
        DisplayMetrics dm;
        Configuration conf;
        if (GetLang(activity).equals(Words_en)) {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_en.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(0);
            }
        } else if (GetLang(activity).equals(Words_ar)) {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_ar.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(1);
            }
        } else {
            res = activity.getResources();
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale(Words_en.toLowerCase());
            res.updateConfiguration(conf, dm);
            if (VERSION.SDK_INT >= 17) {
                activity.getWindow().getDecorView().setLayoutDirection(0);
            }
        }
    }

    public static void SetLang(Context context, String ar) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(lang, ar);
        editor.commit();
    }

    public static String GetLang(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(lang, Words_en);
    }

    public static void SetEnWords(Context context, String en) {
        Log.e("engres", en);
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Words_en, en);
        editor.commit();
    }

    public static String GetEnWords(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Words_en, "-1");
    }

    public static void SetArWords(Context context, String ar) {
        Log.e("arabicres", ar);
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Words_ar, ar);
        editor.commit();
    }

    public static String GetArWords(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Words_ar, "-1");
    }

    public static String GetWord(Context context, String word) {
        if (GetLang(context).equals(Words_ar)) {
            try {
                Log.e("ar_words", GetArWords(context));
                word = new JSONObject(GetArWords(context)).getString(word);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                word = new JSONObject(GetEnWords(context)).getString(word);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return word;
    }
}
