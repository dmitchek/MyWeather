package com.example.MyWeather.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.MyWeather.data.UsersContract;

/**
 * Created by dave on 2/3/16.
 */
public class UsersDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "users.db";
    private static final int    DB_VERSION = 1;
    private static final String DB_CREATE_USERS_TABLE = "CREATE TABLE " +
            UsersContract.UsersEntry.TABLE_NAME + " (" +
            UsersContract.UsersEntry.COLUMN_NAME_NAME + " TEXT, " +
            UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS + " TEXT);";

    public UsersDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);

        // TODO: do construction stuff here
    }

    public void onCreate(SQLiteDatabase db)
    {
        // TODO: do stuff when the DB is created
        db.execSQL(DB_CREATE_USERS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO: do stuff when DB needs to be updated
    }
}
