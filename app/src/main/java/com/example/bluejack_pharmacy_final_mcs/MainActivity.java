package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText emailField, passField;
    Button loginBtn, navRegisBtn;
//    User currUser;
//    UserDatabase dbUser;
    boolean verified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email_et);
        passField = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        navRegisBtn = findViewById(R.id.nav_regis_btn);

//        if(!getIntent().hasExtra("User Database")){
//            dbUser = new UserDatabase();
//
//        }
//        else {
//            dbUser = (UserDatabase) getIntent().getSerializableExtra("User Database");
//        }

        loginBtn.setOnClickListener(e-> {
//            dbUser.printUsers();
            String email = emailField.getText().toString();
            String pass = passField.getText().toString();
//            if (!validate(email, pass) && !verified) {
//                return;
//            }
            Toast.makeText(this, "You're logged in!", Toast.LENGTH_SHORT).show();
            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("User Database", dbUser);
//            toHome.putExtra("Logged User", currUser);
            startActivity(toHome);
        });

        navRegisBtn.setOnClickListener(e-> {
            Intent toRegis = new Intent(this, RegisterActivity.class);
//            toRegis.putExtra("User Database", dbUser);
            startActivity(toRegis);
        });

    }

//    public boolean validate(String email, String pass){
//        if(email.equals("") || pass.equals("")){
//            Toast.makeText(this, "All field must be filled!", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (dbUser.verify(email, pass)==null) { // kalau email salah / password salah
//            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        // cara bikin verified
//        verified = true;
//        currUser = dbUser.verify(email, pass);
//        return true;
//    }
}