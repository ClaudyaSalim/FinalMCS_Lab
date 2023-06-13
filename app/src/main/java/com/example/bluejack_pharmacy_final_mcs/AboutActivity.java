package com.example.bluejack_pharmacy_final_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.bluejack_pharmacy_final_mcs.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutActivity extends AppCompatActivity {

    Button homeBtn;
    private GoogleMap map;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        user = (User) getIntent().getSerializableExtra("Logged User");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gmaps);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;
                setMap(map);
            }
        });

        homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(e->{
            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("Logged User", user);
            startActivity(toHome);
        });

    }
    public void setMap(GoogleMap map){
        double lat = -6.20201;
        double longi = 106.78113;
        LatLng binus = new LatLng(lat, longi);
        map.moveCamera(CameraUpdateFactory.newLatLng(binus));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
        map.addMarker(new MarkerOptions().position(binus).title("Bluejack Pharmacy"));
    }
}