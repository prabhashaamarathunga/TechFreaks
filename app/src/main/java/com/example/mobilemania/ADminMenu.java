package com.example.mobilemania;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ADminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        getIntent();
    }
}
