package com.example.yellowsoft.pharmzi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by yellowsoft on 4/1/18.
 */

public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        get_words();
    }

    public void get_words(){
        Ion.with(this)
                .load(Session.SERVERURL+"words-json-android.php")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            Session.SetEnWords(SplashActivity.this, result.get("en").getAsJsonObject().toString());
                            Session.SetArWords(SplashActivity.this, result.get("ar").getAsJsonObject().toString());
                            Intent intent = new Intent(SplashActivity.this, CityActivity.class);
                            startActivity(intent);
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }
}
