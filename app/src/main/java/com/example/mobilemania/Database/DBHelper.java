package com.example.mobilemania.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.mobilemania.Database.UsersMaster.Orders.COLUMN_NAME_ADDRESS;
import static com.example.mobilemania.Database.UsersMaster.Orders.COLUMN_NAME_EMAIL;
import static com.example.mobilemania.Database.UsersMaster.Orders.COLUMN_NAME_ITEM;
import static com.example.mobilemania.Database.UsersMaster.Orders.COLUMN_NAME_NAME;
import static com.example.mobilemania.Database.UsersMaster.Orders.COLUMN_NAME_PHONE;
import static com.example.mobilemania.Database.UsersMaster.Orders.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MOBILE_Mania.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        UsersMaster.Orders._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Orders.COLUMN_NAME_NAME + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_PHONE + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_ADDRESS + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_EMAIL + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_ITEM + " TEXT)";
        // Use the details from the UsersMaster and Users classes we created. Specify the primary key from the BaseColumns interface.

        db.execSQL(SQL_CREATE_ENTRIES);  // This will execute the contents of SQL_CREATE_ENTRIES
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //-------------------------------------INSERT(Orders)-------------------------------------------
    public boolean addOrders(String name, String phone, String address, String email, String item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NAME, name);
        contentValues.put(COLUMN_NAME_PHONE, phone);
        contentValues.put(COLUMN_NAME_ADDRESS, address);
        contentValues.put(COLUMN_NAME_EMAIL, email);
        contentValues.put(COLUMN_NAME_ITEM, item);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    //-------------------------------------View(yourOrders)-----------------------------------------
    public Cursor viewOrders() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    //-------------------------------------Update(yourOrders)---------------------------------------
    public Boolean updateOrders(String id, String name, String phone, String address, String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersMaster.Orders._ID, id);
        values.put(UsersMaster.Orders.COLUMN_NAME_NAME, name);
        values.put(UsersMaster.Orders.COLUMN_NAME_PHONE, phone);
        values.put(UsersMaster.Orders.COLUMN_NAME_ADDRESS, address);
        values.put(UsersMaster.Orders.COLUMN_NAME_EMAIL, email);

        db.update(TABLE_NAME, values, "_id = ?", new String[]{id});

        return true;
    }


    //-------------------------------------Delete(yourOrders)---------------------------------------
    public Integer deleteOrders(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }

    public Integer deleteCart(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }


    //-------------------------------------View(Cart)---------------------------------------
    public Cursor viewCheckout() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewCart(SQLiteDatabase database){
        String[] projections = {UsersMaster.Orders.COLUMN_NAME_NAME,UsersMaster.Orders.COLUMN_NAME_PHONE};
        Cursor cursor = database.query(TABLE_NAME,projections,null,null,null,null,null );
        return cursor;
    }

}


