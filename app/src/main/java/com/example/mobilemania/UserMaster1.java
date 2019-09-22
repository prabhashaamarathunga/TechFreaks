package com.example.mobilemania;

import android.provider.BaseColumns;

public class UserMaster1 implements BaseColumns {

    public static abstract class NewCreditorInfo {

        public static final String DELIVERER_NAME = "add_name";
        public static final String DELIVERER_PHONE = "add_phone";
        public static final String DELIVERER_ID = "add_id";
        public static final String DELIVERER_DATE = "add_date";

        public static final String TABLE_NAME = "Delivery_Person";

    }

}
