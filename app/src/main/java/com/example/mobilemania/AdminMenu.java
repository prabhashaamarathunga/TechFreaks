package com.example.mobilemania;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        getIntent();
    }

    public void onClickItem(View view){
        Intent intent = new Intent(this, ItemMainActivity.class);
        startActivity(intent);
    }

    public void onClickDelivery(View view){
        Intent intent = new Intent(this, DelivereyHome.class);
        startActivity(intent);
    }
}
