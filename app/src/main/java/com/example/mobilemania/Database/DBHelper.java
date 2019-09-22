package com.example.mobilemania.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MOBILE_Mania12.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UsersMaster.Orders.TABLE_NAME + " (" +
                        UsersMaster.Orders._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Orders.COLUMN_NAME_NAME + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_PHONE + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_ADDRESS + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_EMAIL + " TEXT," +
                        UsersMaster.Orders.COLUMN_NAME_ITEM + " TEXT)";

        // Use the details from the UsersMaster and Users classes we created. Specify the primary key from the BaseColumns interface.

        String SQL_CREATE_USER_ENTRIES = "CREATE TABLE " + UsrMaster.Usr.TABLE_NAME + "(" +
                UsrMaster.Usr._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsrMaster.Usr.COLUMN_NAME_FIRSTNAME + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_LASTNAME + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_USERNAME + " TEXT UNIQUE," +
                UsrMaster.Usr.COLUMN_NAME_PASSWORD + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_ADDRESS + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER + " INT)";

        db.execSQL(SQL_CREATE_ENTRIES);  // This will execute the contents of SQL_CREATE_ENTRIES
        db.execSQL(SQL_CREATE_USER_ENTRIES);


//------------------------Maneesha---------------------------------------------------------------


        ContentValues values = new ContentValues();
        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_PASSWORD, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, 772556158);

        db.insert(UsrMaster.Usr.TABLE_NAME, null, values);

//--------------------------------------------------------------------------------------------



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //-------------------------------------INSERT(Orders)-------------------------------------------
    public boolean addOrders(String name, String phone, String address, String email, String item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersMaster.Orders.COLUMN_NAME_NAME, name);
        contentValues.put(UsersMaster.Orders.COLUMN_NAME_PHONE, phone);
        contentValues.put(UsersMaster.Orders.COLUMN_NAME_ADDRESS, address);
        contentValues.put(UsersMaster.Orders.COLUMN_NAME_EMAIL, email);
        contentValues.put(UsersMaster.Orders.COLUMN_NAME_ITEM, item);
        long result = db.insert(UsersMaster.Orders.TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    //-------------------------------------View(yourOrders)-----------------------------------------
    public Cursor viewOrders() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + UsersMaster.Orders.TABLE_NAME, null);
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

        db.update(UsersMaster.Orders.TABLE_NAME, values, "_id = ?", new String[]{id});

        return true;
    }


    //-------------------------------------Delete(yourOrders)---------------------------------------
    public Integer deleteOrders(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(UsersMaster.Orders.TABLE_NAME, "_id = ?", new String[]{id});
    }

    public Integer deleteCart(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(UsersMaster.Orders.TABLE_NAME, "_id = ?", new String[]{id});
    }


    //-------------------------------------View(Cart)---------------------------------------
    public Cursor viewCheckout() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from " + UsersMaster.Orders.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor viewCart(SQLiteDatabase database){
        String[] projections = {UsersMaster.Orders.COLUMN_NAME_NAME,UsersMaster.Orders.COLUMN_NAME_PHONE};
        Cursor cursor = database.query(UsersMaster.Orders.TABLE_NAME,projections,null,null,null,null,null );
        return cursor;
    }


 //-----------------------------------------Maneesha---------------------------------------------

    public boolean addUsrDetails(String fname, String lname, String uname, String pass, String cpass, String address, String cNumber) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, fname);
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, lname);
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, uname);
        values.put(UsrMaster.Usr.COLUMN_NAME_PASSWORD, pass);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD, cpass);
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, address);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, cNumber);

        long newRowId = db.insert(UsrMaster.Usr.TABLE_NAME, null, values);

        if (newRowId >= 1)
            return true;
        else
            return false;
    }

    public int login(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM "+ UsrMaster.Usr.TABLE_NAME +" WHERE "+ UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] s = {username};

        Cursor result = sqLiteDatabase.rawQuery(query, s);

        if(result.getCount() == 0){
            //no username
            return -1;
        }

        String dbPassword;

        while(result.moveToNext()){
            dbPassword = result.getString(4);

            if(!dbPassword.equals(password)){
                //invalid pwd
                return 0;
            }
            else{
                //login success
                return 1;
            }
        }

        return  -2;

    }

    public Cursor getUserData(String username){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM "+ UsrMaster.Usr.TABLE_NAME+" WHERE "+ UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] s = {username};

        Cursor result = sqLiteDatabase.rawQuery(query, s);

        return result;
    }

    public boolean updateInfo(String fname,String lname,String uname,String address,String contactNo) {
        SQLiteDatabase db = getReadableDatabase();

        //New value for one column
        ContentValues values = new ContentValues();
        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, fname);
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, lname);
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, uname);
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, address);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, contactNo);


        //Which row to update, based on the title
        String selection = UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {uname};

        int count = db.update(
                UsrMaster.Usr.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if(count >= 1)
            return true;
        else
            return false;
    }




    public boolean deleteInfo(String userName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //Define 'where' part of query
        String selection = UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        //Specify arguments n placeholder order
        String[] selectionArgs = {userName};
        //Issue SQL statement
        int result = sqLiteDatabase.delete(UsrMaster.Usr.TABLE_NAME, selection, selectionArgs);

        if (result > 0)
        {
            return true;
        }

        else {
            return false;
        }

    }

    public int adminlogin(String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM "+ UsrMaster.Usr.TABLE_NAME +" WHERE "+ UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] s = {"Admin"};

        Cursor result = sqLiteDatabase.rawQuery(query, s);

        if(result.getCount() == 0){
            //no username
            return -1;
        }

        String dbPassword;

        while(result.moveToNext()){
            dbPassword = result.getString(4);

            if(!dbPassword.equals(password)){
                //invalid pwd
                return 0;
            }
            else{
                //login success
                return 1;
            }
        }

        return  -2;

    }








}


