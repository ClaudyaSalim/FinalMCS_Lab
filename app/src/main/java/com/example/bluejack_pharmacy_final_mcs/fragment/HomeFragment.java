package com.example.bluejack_pharmacy_final_mcs.fragment;

import android.content.Intent;
import android.os.Bundle;

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
import com.android.volley.toolbox.Volley;
import com.example.bluejack_pharmacy_final_mcs.AboutActivity;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.adapter.MedicAdapter;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

//    private User user;
    ImageView aboutIm;
    View homeView;
    RecyclerView medicRv;
    MedicAdapter medicAdapter;
    ArrayList<Medic> medics;
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

        // about
        TextView cekName = homeView.findViewById(R.id.cek_name);
        cekName.setText("Tap on logo to see about us");

        aboutIm = homeView.findViewById(R.id.about_im);
        aboutIm.setOnClickListener(e->{
            Intent toAbout = new Intent(this.getContext(), AboutActivity.class);
//            toAbout.putExtra("Logged User", user);
            startActivity(toAbout);
        });

        // dump codingan
//        dbMedic = new MedicDatabase();
//        dbMedic.addMedic();

        // json medicines
        medics = new ArrayList<>();
        setValues();
        // tambah ke database kalau masing kosong

        medicRv = homeView.findViewById(R.id.medic_rv);
        medicAdapter = new MedicAdapter(this.getContext(), medics);
        medicRv.setAdapter(medicAdapter);
        medicRv.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        return homeView;
    }

    private void setValues(){
        // send request ke API
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        String url = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";
        JsonArrayRequest request = new JsonArrayRequest(url,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray res) {
                        Log.e("ASD", String.valueOf(res==null));
                        for(int i = 0; i < res.length(); i++){
                            // Parse
                            try{
                                JSONObject jsonObject = res.getJSONObject(i);

//                                String name, manufacturer, image, desc;
//                                int price;
//
                                JSONObject medicJson = jsonObject.getJSONObject("medicines");
//                                name = jsonObject.getString("name");
//                                manufacturer= jsonObject.getString("manufacturer");
//                                price = jsonObject.getInt("price");
//                                image = jsonObject.getString("image");
//                                desc = jsonObject.getString("description");

//                                Medic medic = new Medic(name, manufacturer, price, image, desc);
//                                medics.add(medic);

                            } catch (JSONException e) {
                                Log.e("ASD", "onResponse: Parse Error");
                            }
                        }
                        // dump codingan
                        for (Medic medic:medics) {
                            Log.i("ASD", medic.getName());
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.e("ASD", "Error while fetching data");
                    }
                }
        );
        requestQueue.add(request);
    }
}