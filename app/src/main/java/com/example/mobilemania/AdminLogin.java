package com.example.mobilemania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilemania.Database.DBHelper;

public class AdminLogin extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText getPassword;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);

        if(sharedPreferences.getBoolean("logged", false)){
            Intent intent = new Intent(this, UserMain.class);
            startActivity(intent);
            return;
        }

        getPassword = (EditText) findViewById(R.id.password);

        dbHelper = new DBHelper(this);
    }

    public void userLogin(View view) {

        String password1 = getPassword.getText().toString();

        if (getPassword.getText().toString().length()<=0) {
            Toast t = Toast.makeText(getApplicationContext(), "Please Fill Out All Fields!", Toast.LENGTH_LONG);
            t.show();
        }
        else{

            DBHelper db = new DBHelper(this);
            int loginResult = db.adminlogin(password1);

            if(loginResult == 1) {
                Intent intent = new Intent(AdminLogin.this, ItemMainActivity.class);
                Toast.makeText(this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return;
            }
            else if (loginResult == 0)
            {
                Toast t = Toast.makeText(getApplicationContext(), "Invalid Password!", Toast.LENGTH_LONG);
                t.show();
                return;
            }
            else
            {
                Toast t = Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG);
                t.show();
                return;
            }
        }
    }
}
