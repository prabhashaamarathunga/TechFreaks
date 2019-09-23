package com.example.mobilemania;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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

        String cname = name.getText().toString();
        String cphone = phone.getText().toString();
        String cid = id.getText().toString();
        String cdate = date.getText().toString();

        dBhelper = new DBhelper1(context);
        sqLiteDatabase = dBhelper.getWritableDatabase();

        if (cname.isEmpty() || cphone.isEmpty() || cid.isEmpty() || cdate.isEmpty()) {
            if (cname.isEmpty()) {
                name.setError("Name is Required!!!");
            }
            if (cphone.isEmpty()) {
                phone.setError("Phone is Required!!!");
            }
            if (cid.isEmpty()) {
                id.setError("Amount is Required!!!");
            }
            if (cdate.isEmpty()) {
                date.setError("Date is Required!!!");
            }
            Toast.makeText(getApplicationContext(), "Empty field cannot add!!!", Toast.LENGTH_SHORT).show();

        } else if (isPhoneValid(cphone)) {
            phone.setError("Invalid!!!");
            Toast.makeText(getApplicationContext(), "Phone Number is Invalid!!!", Toast.LENGTH_SHORT).show();
        } else {

            dBhelper.addCreditorInfo(cname, cphone, cid, cdate, sqLiteDatabase);

            Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();

            dBhelper.close();

            Intent intent = new Intent(this, ListCreditors.class);
            startActivity(intent);

        }
    }

    public static boolean isPhoneValid(String phoneNumber){
        boolean valid = true;
        String regex = "^(?:00255|\\+255|0)[6-9][0-9]{9}";

        if (!phoneNumber.matches(regex)){
            valid = false;
        }
        return valid;
    }
    public void clearAll(View view){
        name.setText("");
        phone.setText("");
        id.setText("");
        date.setText("");
    }



}
