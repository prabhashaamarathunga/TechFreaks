package com.example.mobilemania;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListCreditor extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DBhelper1 dBhelper;
    Cursor cursor;
    ListCreditorAdaptor listCreditorAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_creditors);

        listView = (ListView) findViewById(R.id.creditor_list);

        dBhelper = new DBhelper1(getApplicationContext());
        sqLiteDatabase = dBhelper.getReadableDatabase();
        listCreditorAdaptor = new ListCreditorAdaptor(getApplicationContext(), R.layout.crow_layout);
        listView.setAdapter(listCreditorAdaptor);

        cursor = dBhelper.getCreditors(sqLiteDatabase);

        if(cursor.moveToFirst()) {
            do{

                String name,phone,amount,date;
                name = cursor.getString(0);
                phone = cursor.getString(1);
                amount = cursor.getString(2);
                date = cursor.getString(3);

                CreditorsProvider creditorsProvider = new CreditorsProvider(name,phone,amount,date);

                listCreditorAdaptor.add(creditorsProvider);

            }while (cursor.moveToNext());
        }

    }
}
