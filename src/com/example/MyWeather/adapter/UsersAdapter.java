package com.example.MyWeather.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.MyWeather.database.UsersContract;
import com.example.MyWeather.database.UsersDBHelper;

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
    }

    public void close()
    {
        mUsersDBHelper.close();
    }

    public long insertNewUser(String name, String locations)
    {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsersContract.UsersEntry.COLUMN_NAME_NAME, name);
        values.put(UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS, locations);

        // Insert the new row, returning the primary key value of the new row
        return mUsersDBHelper.getWritableDatabase().insert(
                UsersContract.UsersEntry.TABLE_NAME,
                null,
                values);
    }

    public String [] getUserLocations(String name)
    {
        String [] columns = {UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS};
        String [] selection = {name};
        Cursor cursor =   mUsersDBHelper.getReadableDatabase().query(UsersContract.UsersEntry.TABLE_NAME,
                                    columns,
                                    UsersContract.UsersEntry.COLUMN_NAME_NAME + "=?",
                                    selection, null, null, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String locations = cursor.getString(cursor.getColumnIndex(UsersContract.UsersEntry.COLUMN_NAME_LOCATIONS));
            String [] locationsArray = locations.split(",");
            return locationsArray;
        }
        else
            return null;
    }

}
