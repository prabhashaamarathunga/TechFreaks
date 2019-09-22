package com.example.mobilemania;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMobileMania extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MobileMania255.db";
    public static final String TABLE_NAME = "orders";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Contact_no";
    public static final String COL_4 = "Dilivery_address";

    public DBMobileMania(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(" +COL_1+ "INTEGER PRIMARY KEY AUTOINCREMENT," +COL_2+ "TEXT," +COL_3+ "INTEGER," +COL_4+ "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String Contact_no, String Delivery_address){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, Contact_no);
        contentValues.put(COL_4, Delivery_address);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
