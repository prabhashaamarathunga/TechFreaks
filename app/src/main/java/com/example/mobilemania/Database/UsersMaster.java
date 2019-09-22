package com.example.mobilemania.Database;


import android.provider.BaseColumns;

public final class UsersMaster {
    private UsersMaster() {}

    /* Inner class that defines the table contents */
    public static class Orders implements BaseColumns {
        public static final String TABLE_NAME = "Orders";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ITEM = "item";


    }
}
