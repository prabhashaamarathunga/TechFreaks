package com.example.mobilemania;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class LoginItemList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Item> list;
    ItemListAdapter adapter = null;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginitem_list);

        sqLiteHelper = new SQLiteHelper(this, "DBMobileMania", null, 1);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ItemListAdapter(this, R.layout.mob_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        final Cursor cursor = ItemMainActivity.sqLiteHelper.getData("SELECT * FROM ITEM");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String des = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Item(name, price, des, image, id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                CharSequence[] items = {"Add to cart"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginItemList.this);


                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            //Intent change for addtocart
                            Intent intent = new Intent(LoginItemList.this, checkout.class);
                            String name = ItemMainActivity.sqLiteHelper.orderadd(2);
                            intent.putExtra("item", name);
                            startActivity(intent);
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}