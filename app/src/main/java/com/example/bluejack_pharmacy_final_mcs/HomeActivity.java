package com.example.bluejack_pharmacy_final_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bluejack_pharmacy_final_mcs.adapter.PagerAdapter;
import com.example.bluejack_pharmacy_final_mcs.database.DatabaseHelper;
import com.example.bluejack_pharmacy_final_mcs.database.UserHelper;
import com.example.bluejack_pharmacy_final_mcs.model.User;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    TextView usernameTv;
    SharedPreferences sharedPreferences;
    UserHelper userHelper = new UserHelper(this);
    int userId;
    User user;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    ViewPager vpHome;
    PagerAdapter pagerAdapter;
    TabLayout topNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // dump codingan
//        user = (User) getIntent().getSerializableExtra("Logged User");
//        dbUser = (UserDatabase) getIntent().getSerializableExtra("User Database");

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getInt("UserID", 0);
        user = userHelper.getUserByID(userId);
        usernameTv = findViewById(R.id.username_tv);
        usernameTv.setText("Hello " + user.getName() + " !");

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.passUser();

        vpHome = findViewById(R.id.vp_home);
        vpHome.setAdapter(pagerAdapter);

        topNav = findViewById(R.id.top_nav);
        topNav.setupWithViewPager(vpHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItem = item.getItemId();

        if(menuItem==R.id.logout){
            Intent toLogin = new Intent(this, MainActivity.class);
//            toLogin.putExtra("User Database", dbUser);
            startActivity(toLogin);
        }

        return super.onOptionsItemSelected(item);
    }

}