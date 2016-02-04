package com.example.MyWeather;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.widget.EditText;

import com.example.MyWeather.data.UsersAdapter;
import com.example.MyWeather.data.UsersContract;
import com.example.MyWeather.data.UsersDBHelper;

public class Main extends Activity {

    private UsersAdapter mAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        mAdapter = new UsersAdapter();

        mAdapter.open(getApplicationContext());

        mAdapter.insertNewUser("test", "40508,12345");
        EditText username = (EditText)findViewById(R.id.username);

        username.setText(mAdapter.getUserLocations("test"));

        //mAdapter.insertRow("test1", "12345");

    }

    @Override
    public void onDestroy()
    {
        mAdapter.close();
    }
}
