package com.example.yellowsoft.pharmzi;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yellowsoft on 8/1/18.
 */

public class Areas implements Serializable {
    String area_id;
    public String id;
    public String latitude;
    public String longitude;
    public String title;
    public String title_ar;
    public String type;

    public Areas(JsonObject jsonObject, Context context) {
        this.id = jsonObject.get("id").getAsString();
        this.title = jsonObject.get(Session.title).getAsString();
        this.title_ar = jsonObject.get("title_ar").getAsString();
        this.latitude = jsonObject.get("latitude").getAsString();
        this.longitude = jsonObject.get(Session.longitude).getAsString();
        this.type = "0";
    }

    public Areas(JsonObject jsonObject, Context context, String type) {
        this.type = type;
        if (type.equals("0")) {
            this.id = jsonObject.get("id").getAsString();
            this.title = jsonObject.get(Session.title).getAsString();
            this.title_ar = jsonObject.get("title_ar").getAsString();
            return;
        }
        this.id = jsonObject.get("id").getAsString();
        this.title = jsonObject.get(Session.title).getAsString();
        this.title_ar = jsonObject.get("title_ar").getAsString();
        this.latitude = jsonObject.get("latitude").getAsString();
        this.longitude = jsonObject.get(Session.longitude).getAsString();
    }
}
