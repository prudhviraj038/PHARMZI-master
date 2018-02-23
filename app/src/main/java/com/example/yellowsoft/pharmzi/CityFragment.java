package com.example.yellowsoft.pharmzi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 11/1/18.
 */

public class CityFragment  extends Fragment implements OnMapReadyCallback {
    int ASK_MULTIPLE_PERMISSION_REQUEST_CODE;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_fragment_screen, container, false);
        Session.forceRTLIfSupported(getActivity());
        try {
            ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment)).getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void onMapReady(GoogleMap map) {
        map.setMapType(3);
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            map.getUiSettings().setZoomControlsEnabled(true);
            LatLng point = new LatLng(Double.parseDouble(Session.GetLatitude(getContext())), Double.parseDouble(Session.GetLongitude(getContext())));
            Marker marker = map.addMarker(new MarkerOptions().position(point).title(Session.GetAreaTitle(getContext())).visible(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(point, 15.0f);
            map.animateCamera(location);
            map.moveCamera(location);
            return;
        }
        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.CAMERA"}, this.ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
    }
}
