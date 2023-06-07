package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText nameField, emailField, phoneField, passField, confirmPassField;
    Button registerBtn, navLoginBtn;
//    UserDatabase dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = findViewById(R.id.name_et);
        emailField = findViewById(R.id.email_et);
        phoneField = findViewById(R.id.phone_et);
        passField = findViewById(R.id.password_et);
        confirmPassField = findViewById(R.id.confirm_password_et);
        registerBtn = findViewById(R.id.register_btn);
        navLoginBtn = findViewById(R.id.nav_login_btn);

//        dbUser = (UserDatabase) getIntent().getSerializableExtra("User Database");

        registerBtn.setOnClickListener(e->{

            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String phone = phoneField.getText().toString();
            String pass = passField.getText().toString();
            String confirmPass = confirmPassField.getText().toString();
//            ArrayList<Transaction> transactionList = new ArrayList<>();

//            if(!validate(name, email, phone, pass, confirmPass)){
//                return;
//            }
//            int usersAmount = dbUser.getUsersList().size();
//            User newUser = new User(usersAmount+1, name, email, phone, pass, transactionList);
//            dbUser.getUsersList().add(newUser);
//            dbUser.printUsers();

            Toast.makeText(this, "You're registered!", Toast.LENGTH_SHORT).show();
//            intent ke otp
//            Intent toHome = new Intent(this, HomeActivity.class);
//            startActivity(toHome);
        });

        navLoginBtn.setOnClickListener(e->{
            Intent toLogin = new Intent(this, MainActivity.class);
//            toLogin.putExtra("User Database", dbUser);
            startActivity(toLogin);
        });
    }

    public boolean validate(String name, String email, String phone, String pass, String confirmPass){
        if(name.equals("") || email.equals("") || phone.equals("") || pass.equals("") || confirmPass.equals("")){
            Toast.makeText(this, "All field must be filled!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (name.length()<5) {
            Toast.makeText(this, "Name should be at least 5 characters!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.endsWith(".com")) {
            Toast.makeText(this, "Email must end with '.com'!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!pass.matches("[a-zA-Z0-9]+")) {
            System.out.println(pass.matches("[a-z0-9]+"));
            Toast.makeText(this, "Password must be alphanumeric!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!confirmPass.equals(pass)) {
            Toast.makeText(this, "Password field and Confirm Password field should be the same!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}