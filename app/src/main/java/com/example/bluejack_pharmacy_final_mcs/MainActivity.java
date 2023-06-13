package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bluejack_pharmacy_final_mcs.database.UserHelper;
import com.example.bluejack_pharmacy_final_mcs.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText emailField, passField;
    Button loginBtn, navRegisBtn;
    UserHelper userHelper = new UserHelper(this);
    ArrayList<User> users;
    User currUser;
    SharedPreferences sharedPreferences;
//    User currUser;
//    UserDatabase dbUser;
//    boolean verified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email_et);
        passField = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        navRegisBtn = findViewById(R.id.nav_regis_btn);

        // dump codingan
//        if(!getIntent().hasExtra("User Database")){
//            dbUser = new UserDatabase();
//
//        }
//        else {
//            dbUser = (UserDatabase) getIntent().getSerializableExtra("User Database");
//        }

        loginBtn.setOnClickListener(e-> {
            String email = emailField.getText().toString();
            String pass = passField.getText().toString();
            if (!validate(email, pass)) {
                return;
            }

            // shared preferences
            sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("UserID", currUser.getId());
            editor.apply();

            Toast.makeText(this, "You're logged in!", Toast.LENGTH_SHORT).show();

            // intent ke OTP + cek verified
            Intent toHome = new Intent(this, HomeActivity.class);
            startActivity(toHome);

            Intent intent = new Intent(this, otp.class);
            startActivity(intent);
            finish();

            // codingan kemaren
//            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("User Database", dbUser);
//            toHome.putExtra("Logged User", currUser);
//            startActivity(toHome);
        });

        navRegisBtn.setOnClickListener(e-> {
            Intent toRegis = new Intent(this, RegisterActivity.class);
//            toRegis.putExtra("User Database", dbUser);
            startActivity(toRegis);
        });

    }
    public boolean validate(String email, String pass){
        if(email.equals("") || pass.equals("")){
            Toast.makeText(this, "All field must be filled!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            users = userHelper.getAllUsers();
            for (User user:users) {
                if(email.equals(user.getEmail())){
                    if(pass.equals(user.getPass())){
                        currUser = user;
                        return true;
                    }
                }
            }
        }
        Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
        return false;
    }
}