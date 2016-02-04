package com.example.MyWeather.data;

import android.provider.BaseColumns;

/**
 * Created by dave on 2/3/16.
 */
public final class UsersContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public UsersContract() {}

    /* Inner class that defines the table contents */
    public static abstract class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LOCATIONS = "locations";
    }
}
