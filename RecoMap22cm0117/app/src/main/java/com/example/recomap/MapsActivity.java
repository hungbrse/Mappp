package com.example.recomap;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);




    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mapInt();
//        //, 139.70057062831293
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(35.68962799997094, 139.70057062831293);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void mapInt() {




        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(35.68962799997094, 139.70057062831293); // Thay đổi vị trí này

        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15.5f).build();

        mMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition)
        );

        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(false);
        ui.setScrollGesturesEnabled(false);
        ui.setScrollGesturesEnabled(false);
        ui.setTiltGesturesEnabled(false);
        ui.setZoomControlsEnabled(true);
        ui.setZoomGesturesEnabled(false);

        markerSet();
    }

    private ArrayList<ShopInfo> getShopInfoes(){
        ArrayList<ShopInfo> ary = new ArrayList<ShopInfo>();

        ShopInfo tmp1 = new ShopInfo();
        tmp1.setPosition(new LatLng(35.66797592873777,139.69570567977473));
        tmp1.setName("渋谷");
        tmp1.setInfomation("渋谷に人が多すぎ");
        ary.add(tmp1);

        ShopInfo tmp2 = new ShopInfo();
        tmp2.setPosition(new LatLng(35.66797592873777,139.69570567977473));
        tmp2.setName("高円寺");
        tmp2.setInfomation("高円寺は綺麗");
        ary.add(tmp2);


        return ary;
    }

    private void markerSet() {
        MarkerOptions options = new MarkerOptions();

        ArrayList<ShopInfo> list = getShopInfoes();

        for (ShopInfo shop: list) {
            options.position(shop.getPosition());
            options.title(shop.getName());
            options.snippet(shop.getInfomation());

            mMap.addMarker(options);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}