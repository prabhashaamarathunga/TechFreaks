package com.example.mobilemania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin_main(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onClickCheckOut(View view){
        Intent intent = new Intent(this, checkout.class);
        startActivity(intent);
    }

    public void onClickView(View view){
        Intent intent = new Intent(this, yourOrders.class);
        startActivity(intent);
    }
}
