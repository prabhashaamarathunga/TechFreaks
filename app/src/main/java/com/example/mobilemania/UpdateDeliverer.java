package com.example.mobilemania;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDeliverer extends AppCompatActivity {

    TextView titlec_txt;
    EditText searchc_name ,newc_name, newc_phone, newc_amount, newc_date;
    DBhelper1 dBhelper;
    SQLiteDatabase sqLiteDatabase;
    String searchnamec, Newnamec, Newphonec, Newamountc, Newdatec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_deliverer);

        searchc_name = findViewById(R.id.supdatetxt);

        newc_name = findViewById(R.id.update_name);
        newc_amount = findViewById(R.id.update_id);
        newc_phone  = findViewById(R.id.update_phone);
        newc_date   = findViewById(R.id.update_date);

        titlec_txt  = findViewById(R.id.txt_updatecreditor);

        newc_name.setVisibility(View.GONE);
        newc_phone.setVisibility(View.GONE);
        newc_amount.setVisibility(View.GONE);
        newc_date.setVisibility(View.GONE);

        titlec_txt.setVisibility(View.GONE);

    }

    public void backhome (View view){
        Intent intent =  new Intent(this, DelivereyHome.class);
        startActivity(intent);
    }




    public void updatecreditor(View view){

    dBhelper = new DBhelper1(getApplicationContext());
    sqLiteDatabase = dBhelper.getWritableDatabase();

    String namec, phonec, amountc, datec;

    namec = newc_name.getText().toString();
    phonec = newc_phone.getText().toString();
    amountc = newc_amount.getText().toString();
    datec = newc_date.getText().toString();

        if (namec.isEmpty()||phonec.isEmpty()||amountc.isEmpty()||datec.isEmpty()){
            if (namec.isEmpty()){
                newc_name.setError("Name is Required!!!");
            }
            if (phonec.isEmpty()){
                newc_phone.setError("Phone is Required!!!");
            }
            if (amountc.isEmpty()){
                newc_amount.setError("Amount is Required!!!");
            }
            if (datec.isEmpty()){
                newc_date.setError("Date is Required!!!");
            }
            Toast.makeText(getApplicationContext(),"Empty field cannot add!!!",Toast.LENGTH_SHORT).show();

        } else if (isPhoneValid(phonec)){
            newc_phone.setError("Invalid!!!");
            Toast.makeText(getApplicationContext(),"Phone Number is Invalid!!!",Toast.LENGTH_SHORT).show();
        }

        else  {

    int count = dBhelper.updateCreditors(searchnamec, namec, phonec, amountc,datec,sqLiteDatabase);

        Toast.makeText(getApplicationContext(),count+ " Creditors Updated",Toast.LENGTH_LONG).show();
        finish();
    }

        dBhelper.close();

        Intent intent=new Intent(this,ListCreditors.class);
        startActivity(intent);

    }

    public static boolean isPhoneValid(String phoneNumber){
        boolean valid = true;
        String regex = "^(?:00255|\\+255|0)[6-9][0-9]{9}";

        if (!phoneNumber.matches(regex)){
            valid = false;
        }
        return valid;
    }





    public void searchCreditor(View view) {

        searchnamec      = searchc_name.getText().toString();
        dBhelper        = new DBhelper1(getApplicationContext());
        sqLiteDatabase  = dBhelper.getReadableDatabase();

        Cursor cursor   = dBhelper.getCDetails(searchnamec,sqLiteDatabase);

        if(cursor.moveToFirst()){

            Newphonec = cursor.getString(0);
            Newamountc = cursor.getString(1);
            Newdatec = cursor.getString(2);
            Newnamec = searchnamec;

            newc_name.setText(Newnamec);
            newc_phone.setText(Newphonec);
            newc_amount.setText(Newamountc);
            newc_date.setText(Newdatec);

            newc_name.setVisibility(View.VISIBLE);
            newc_phone.setVisibility(View.VISIBLE);
            newc_amount.setVisibility(View.VISIBLE);
            newc_date.setVisibility(View.VISIBLE);
        }
    }
}
