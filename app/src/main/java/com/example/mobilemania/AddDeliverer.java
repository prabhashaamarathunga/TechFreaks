package com.example.mobilemania;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddDeliverer extends AppCompatActivity {

    EditText name,phone,id,date;
    Context context = this;
    DBhelper1 dBhelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deliverer);

        Intent secondIntent = getIntent();

        name    = findViewById(R.id.add_namec);
        phone   = findViewById(R.id.add_phonec);
        id  = findViewById(R.id.add_id);
        date    = findViewById(R.id.add_datec);

    }


    public void addCreditor(View view) {

        String cname     = name.getText().toString();
        String cphone    = phone.getText().toString();
        String cid   = id.getText().toString();
        String cdate     = date.getText().toString();

        dBhelper         = new DBhelper1(context);
        sqLiteDatabase  = dBhelper.getWritableDatabase();

        dBhelper.addCreditorInfo(cname,cphone,cid,cdate,sqLiteDatabase);

        Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();

        dBhelper.close();

    }

    public void clearAll(View view){
        name.setText("");
        phone.setText("");
        id.setText("");
        date.setText("");
    }



}
