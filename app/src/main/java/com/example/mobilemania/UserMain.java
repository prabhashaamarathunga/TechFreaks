package com.example.mobilemania;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UserMain extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);
        String uName = sharedPreferences.getString("username", null);
        Log.d("Maneesha", uName);

    }
    public void onClickAcc(View view){
        Intent intent = new Intent(UserMain.this, UserAccount.class);
        startActivity(intent);
    }
    public void onClickBuy(View view){
      Intent intent = new Intent(this, LoginItemList.class);
       startActivity(intent);
    }

    public void onClickManage_orders(View view){
       Intent intent = new Intent(this, yourOrders.class);
       startActivity(intent);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}
