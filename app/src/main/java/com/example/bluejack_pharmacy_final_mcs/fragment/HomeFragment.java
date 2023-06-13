package com.example.bluejack_pharmacy_final_mcs.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bluejack_pharmacy_final_mcs.AboutActivity;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.adapter.MedicAdapter;
//import com.example.bluejack_pharmacy_final_mcs.database.MedicinesHelper;
import com.example.bluejack_pharmacy_final_mcs.database.MedicinesHelper;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

//    private User user;
    ImageView aboutIm;
    TextView loadingMsg;
    View homeView;
    RecyclerView medicRv;
    MedicAdapter medicAdapter;
    ArrayList<Medic> medics;
    MedicinesHelper medicinesHelper;
//    MedicDatabase dbMedic;

    public HomeFragment() {

    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putSerializable("Logged User", currUser);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            user = (User) getArguments().getSerializable("Logged User");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        medicinesHelper = new MedicinesHelper(this.getContext());

        // about
        TextView cekName = homeView.findViewById(R.id.cek_name);
        cekName.setText("Tap on logo to see about us");

        aboutIm = homeView.findViewById(R.id.about_im);
        aboutIm.setOnClickListener(e->{
            Intent toAbout = new Intent(this.getContext(), AboutActivity.class);
            startActivity(toAbout);
        });

        loadingMsg = homeView.findViewById(R.id.loading_msg);

        medics = new ArrayList<>();
        medics = medicinesHelper.getAllMedics();

        // json ditambah ke database kalau masih kosong
        if(medics.size()==0){
            setValues(homeView, this.getContext());
        }
        else {
            setMedicRv(homeView, this.getContext());
        }

        return homeView;
    }

    private void setValues(View homeView, Context context){
        // send request ke API
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        String url = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";
//        Log.e("API", "Before request");
        JsonObjectRequest request = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray medicArray = response.getJSONArray("medicines");
                            for (int i = 0; i < medicArray.length(); i++) {
                                JSONObject medicJson = medicArray.getJSONObject(i);

                                String name, manufacturer, image, desc;
                                int price;
                                name = medicJson.getString("name");
                                manufacturer= medicJson.getString("manufacturer");
                                price = medicJson.getInt("price");
                                image = medicJson.getString("image");
                                desc = medicJson.getString("description");

                                Medic medic = new Medic(name, manufacturer, price, image, desc);
                                medicinesHelper.insertMedicine(medic);
//                                medics.add(medic);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error", "onResponse: parsing");
                        }

                        // cek jsonnya dah dapet belom
//                        for (Medic medic:medics) {
//                            Log.i("Medics", medic.getName());
//                        }

                        // set recycler view, btw gw harus nunggu 20 detik buat muncul gara" dia tergantung sinyal
                        medics = medicinesHelper.getAllMedics();
                        setMedicRv(homeView, context);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("API", "JSON Object is Error");
                    }
                }
        );
        requestQueue.add(request);
//        Log.e("API", "After request");
    }
    public void setMedicRv(View homeView, Context context){
        loadingMsg.setText("");
        medicRv = homeView.findViewById(R.id.medic_rv);
        medicAdapter = new MedicAdapter(context, medics);
        medicRv.setAdapter(medicAdapter);
        medicRv.setLayoutManager(new GridLayoutManager(context, 2));
    }

}