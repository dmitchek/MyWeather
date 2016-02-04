package com.example.MyWeather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dave on 2/3/16.
 */
public class UsersAdapter {

    private UsersDBHelper mUsersDBHelper;
    private SQLiteDatabase mDBWrite;
    private SQLiteDatabase mDBRead;

    public void open(Context context)
    {
        mUsersDBHelper = new UsersDBHelper(context);
        mDBWrite = mUsersDBHelper.getWritableDatabase();
        mDBRead  = mUsersDBHelper.getReadableDatabase();
    }

    public void close()
    {
        mDBRead.close();
        mDBWrite.close();
    }

    public long insertNewUser(String name, String locations)
    {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsersContract.UsersEntry.COLUMN_NAME_NAME, name);
        values.put(UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS, locations);

        // Insert the new row, returning the primary key value of the new row
        return mDBWrite.insert(
                UsersContract.UsersEntry.TABLE_NAME,
                null,
                values);
    }

    public String getUserLocations(String name)
    {
        String [] columns = {UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS};
        String [] selection = {name};
        Cursor cursor =   mDBRead.query(UsersContract.UsersEntry.TABLE_NAME,
                                    columns,
                                    "name = ?",
                                    selection, null, null, null);

        return cursor.getCount() > 0 ?  cursor.getString(0) : null;
    }
}
