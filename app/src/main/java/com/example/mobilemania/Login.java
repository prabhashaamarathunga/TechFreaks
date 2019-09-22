package com.example.mobilemania;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemania.Database.DBHelper;

public class Login extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText getUserName, getPassword;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);

        if(sharedPreferences.getBoolean("logged", false)){
            Intent intent = new Intent(this, UserMain.class);
            startActivity(intent);
         return;
        }

        getUserName = (EditText) findViewById(R.id.UserName);
        getPassword = (EditText) findViewById(R.id.password);

        dbHelper = new DBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onClick1(View view){
        Intent intent = new Intent(Login.this, Registration.class);
        startActivity(intent);
    }

    public void onClickWelcome(View view){
        Intent intent = new Intent(Login.this, UserMain.class);
        startActivity(intent);
    }

    public void userLogin(View view) {
        String username1 = getUserName.getText().toString();
        String password1 = getPassword.getText().toString();

        if (getUserName.getText().toString().length()<= 0 && getPassword.getText().toString().length()<=0) {
            Toast t = Toast.makeText(getApplicationContext(), "Please Fill Out All Fields!", Toast.LENGTH_LONG);
            t.show();
        }
        else{

            DBHelper db = new DBHelper(this);
            int loginResult = db.login(username1, password1);

            if(loginResult == 1) {
                sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("logged", true).apply();
                sharedPreferences.edit().putString("username", username1).apply();

                Intent intent = new Intent(Login.this, UserMain.class);
                intent.putExtra("userName", username1);
                Toast.makeText(this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return;
            }
            else if (loginResult == -1)
            {
                Toast t = Toast.makeText(getApplicationContext(), "Invalid User Name!", Toast.LENGTH_LONG);
                t.show();
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
