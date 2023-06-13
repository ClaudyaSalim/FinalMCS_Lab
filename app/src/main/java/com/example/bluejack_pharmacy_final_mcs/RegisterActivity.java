package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bluejack_pharmacy_final_mcs.database.UserHelper;
import com.example.bluejack_pharmacy_final_mcs.model.User;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText nameField, emailField, phoneField, passField, confirmPassField;
    Button registerBtn, navLoginBtn;
    UserHelper userHelper = new UserHelper(this);
    ArrayList<User>users;
    int totalUsers;
    SharedPreferences sharedPreferences;
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
        users = userHelper.getAllUsers();
        totalUsers = users.size();

        registerBtn.setOnClickListener(e->{

            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String phone = phoneField.getText().toString();
            String pass = passField.getText().toString();
            String confirmPass = confirmPassField.getText().toString();
//            ArrayList<Transaction> transactionList = new ArrayList<>();

            if(!validate(name, email, phone, pass, confirmPass)){
                return;
            }

            for (User user: users) {
                if(email.equals(user.getEmail())){
                    Toast.makeText(this, "Email is already taken!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            User user = new User(name, email, phone, pass, false);
            userHelper.regisUser(user);
            user.setId(totalUsers+1);
            Toast.makeText(this, "You have been registered!", Toast.LENGTH_SHORT).show();

            // shared preferences
            sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("UserID", user.getId());
            editor.apply();

            // intent ke otp
            Intent toHome = new Intent(this, HomeActivity.class);
            startActivity(toHome);
            // dicomment dulu soalnya OTP belum bisa
//            Intent intent = new Intent(this, otp.class);
//            startActivity(intent);
            finish();
        });

        navLoginBtn.setOnClickListener(e->{
            // buat liat user yg udah register di console
            printAll();
            // ke login
            Intent toLogin = new Intent(this, MainActivity.class);
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

    public void printAll(){
        users = userHelper.getAllUsers();
        for (User user: users) {
            Log.e("User", String.valueOf(user.getId()));
            Log.e("User", user.getName());
            Log.e("User", user.getEmail());
            Log.e("User", user.getPhone());
            Log.e("User", user.getPass());
            Log.e("User", String.valueOf(user.isVerified()));
        }
    }
}