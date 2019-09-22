 package com.example.mobilemania;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

 public class DBhelper1 extends SQLiteOpenHelper {

     private static final String DATABASE_NAME = "DBMobileMania.db";
     private static final int DATABASE_VERSION = 1;


 //-----------------DELIVERER TABLE-----------------
     private static final String CREATE_QUERY =
             "CREATE TABLE "+ UserMaster1.NewCreditorInfo.TABLE_NAME+"("+
                     UserMaster1.NewCreditorInfo.DELIVERER_NAME+" TEXT,"+
                     UserMaster1.NewCreditorInfo.DELIVERER_PHONE+" TEXT,"+
                     UserMaster1.NewCreditorInfo.DELIVERER_ID+" TEXT," +
                     UserMaster1.NewCreditorInfo.DELIVERER_DATE+" TEXT);";



     public DBhelper1(Context context) {

         super(context,DATABASE_NAME,null,DATABASE_VERSION);
         Log.e("DATABASE OPERATIONS","Database created / opened...");
     }




     @Override
     public void onCreate(SQLiteDatabase db) {

         db.execSQL(CREATE_QUERY);
         Log.e("DATABASE OPERATIONS", "Deliverer Table Created...");

     }

     //---------------add Deliverer----------------------

     public void addCreditorInfo(String name, String phone, String id, String date, SQLiteDatabase db){

         ContentValues contentValues = new ContentValues();

         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_NAME,name);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_PHONE,phone);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_ID,id);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_DATE,date);

         db.insert(UserMaster1.NewCreditorInfo.TABLE_NAME,null,contentValues);

         Log.e("DATABASE_OPERATIONS","One row is inserted...");
     }


     //-----------getData--------------------

     public Cursor getCreditors(SQLiteDatabase db) {

         Cursor cursor;

         String[] cprojectios = {UserMaster1.NewCreditorInfo.DELIVERER_NAME, UserMaster1.NewCreditorInfo.DELIVERER_PHONE, UserMaster1.NewCreditorInfo.DELIVERER_ID, UserMaster1.NewCreditorInfo.DELIVERER_DATE};
         cursor = db.query(UserMaster1.NewCreditorInfo.TABLE_NAME,cprojectios,null,null,null,null,null);

         return cursor;
     }



     public Cursor getCDetails(String c_name, SQLiteDatabase sqLiteDatabase){

         String[] cprojections2 = {UserMaster1.NewCreditorInfo.DELIVERER_PHONE, UserMaster1.NewCreditorInfo.DELIVERER_ID, UserMaster1.NewCreditorInfo.DELIVERER_DATE};
         String selection = UserMaster1.NewCreditorInfo.DELIVERER_NAME+" LIKE ?";
         String[] selectionc_args = {c_name};

         Cursor cursor = sqLiteDatabase.query(UserMaster1.NewCreditorInfo.TABLE_NAME, cprojections2, selection,selectionc_args,null,null,null);

         return cursor;
     }


     public void deleteCreditors(String search_name, SQLiteDatabase sqLiteDatabase){

         String selection = UserMaster1.NewCreditorInfo.DELIVERER_NAME+" LIKE ?";
         String[] selectionc_args = {search_name};

         sqLiteDatabase.delete(UserMaster1.NewCreditorInfo.TABLE_NAME,selection,selectionc_args);
     }


     public int updateCreditors(String old_name,String new_name, String new_phone, String new_amount, String new_date, SQLiteDatabase sqLiteDatabase){

         ContentValues contentValues = new ContentValues();

         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_NAME, new_name);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_PHONE, new_phone);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_ID, new_amount);
         contentValues.put(UserMaster1.NewCreditorInfo.DELIVERER_DATE, new_date);

         String selection = UserMaster1.NewCreditorInfo.DELIVERER_NAME + " LIKE ?";
         String[] selection_args = {old_name};

         int count = sqLiteDatabase.update(UserMaster1.NewCreditorInfo.TABLE_NAME,contentValues,selection,selection_args);

         return count;

     }


     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

     }
 }
