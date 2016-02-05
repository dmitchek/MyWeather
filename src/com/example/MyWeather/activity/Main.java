package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;

public class Main extends Activity {

    private UsersAdapter mAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        Button newUser = (Button)findViewById(R.id.newuser);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.insertNewUser("test", "12345");
            }
        });

        Button getWeather = (Button)findViewById(R.id.get_weather);
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText username = (EditText)findViewById(R.id.username);
                //username.setText(mAdapter.getUserLocations("test"));
                Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                intent.putExtra("location", "40508");
                startActivity(intent);

            }
        });

        mAdapter = new UsersAdapter();

        mAdapter.open(getApplicationContext());

        //mAdapter.insertNewUser("test", "40508,12345");
        //mAdapter.insertRow("test1", "12345");

    }

    @Override
    public void onDestroy()
    {
        mAdapter.close();
    }
}
