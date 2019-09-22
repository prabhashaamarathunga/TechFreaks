package com.example.mobilemania;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SearchDeliverer extends AppCompatActivity {

    TextView display_phone, display_amount, display_date;
    EditText search_name;
    DBhelper1 dBhelper;
    SQLiteDatabase sqLiteDatabase;
    String searchname;

    private static final int REQUEST_CALL =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_deliverer);

        search_name = findViewById(R.id.searchtxtc);

        display_amount = findViewById(R.id.search_id);
        display_phone  = findViewById(R.id.search_phone);
        display_date   = findViewById(R.id.search_date);

        ImageView imageCall = findViewById(R.id.image_call);

        display_phone.setVisibility(View.GONE);
        display_amount.setVisibility(View.GONE);
        display_date.setVisibility(View.GONE);


//        set call button
        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

    }


//    make phone call
    private void makePhoneCall() {
        String number = display_phone.getText().toString();

        if(number.trim().length() > 0){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void searchCreditor(View view) {

        searchname      = search_name.getText().toString();
        dBhelper        = new DBhelper1(getApplicationContext());
        sqLiteDatabase  = dBhelper.getReadableDatabase();

        Cursor cursor   = dBhelper.getCDetails(searchname,sqLiteDatabase);

        if(cursor.moveToFirst()){
            String PHONE = cursor.getString(0);
            String AMOUNT = cursor.getString(1);
            String DATE = cursor.getString(2);

            display_phone.setText(PHONE);
            display_amount.setText(AMOUNT);
            display_date.setText(DATE);

            display_phone.setVisibility(View.VISIBLE);
            display_amount.setVisibility(View.VISIBLE);
            display_date.setVisibility(View.VISIBLE);
        }
    }

    public void backhome (View view){
        Intent intent =  new Intent(this, DelivereyHome.class);
        startActivity(intent);
    }

    public void dltcreditor(View view){

        dBhelper        = new DBhelper1(getApplicationContext());
        sqLiteDatabase  = dBhelper.getReadableDatabase();

        dBhelper.deleteCreditors(searchname,sqLiteDatabase);
        Toast.makeText(getApplicationContext(),"Creditor Deleted",Toast.LENGTH_LONG).show();
    }
}
