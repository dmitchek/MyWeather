package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.MyWeather.R;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.NetworkTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dmitchell on 2/5/2016.
 */
public class WeatherActivity extends Activity {

    NetworkTask mNetworkTask;
    private String mLocation;
    private int mCurrentPosition;
    private ArrayList<WeatherData> mLocationData;

    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData [] data) {
            Log.d("DKM", "Data received!");

            mLocationData = new ArrayList<WeatherData>(Arrays.asList(data));
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }
    }

    private OnDataListener mWeatherDataListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        Intent intent = getIntent();

        mLocation = intent.getStringExtra("location");
        Log.d("DKM", "Location: " + mLocation);

        // Get weather for given location
        mWeatherDataListener = new OnDataListener();
        mNetworkTask = new NetworkTask(getApplicationContext());
        mNetworkTask.fetchWeatherData(mLocation, mWeatherDataListener);

        mCurrentPosition = 0;

        Button next = (Button)findViewById(R.id.next_location);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(RIGHT);
            }
        });

        Button previous = (Button)findViewById(R.id.previous_location);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(LEFT);
            }
        });
        previous.setEnabled(false);
    }

    private void loadView(int direction)
    {
        int newPosition = mCurrentPosition + direction;
        if((newPosition < mLocationData.size()) && (newPosition >= 0))
        {
            mCurrentPosition = newPosition;
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }

        if(mCurrentPosition == 0)
        {
            findViewById(R.id.previous_location).setEnabled(false);
            findViewById(R.id.next_location).setEnabled(true);
        }
        else if(mCurrentPosition == (mLocationData.size()-1))
        {
            findViewById(R.id.previous_location).setEnabled(true);
            findViewById(R.id.next_location).setEnabled(false);
        }
        else
        {
            findViewById(R.id.previous_location).setEnabled(true);
            findViewById(R.id.next_location).setEnabled(true);
        }
    }

    private void loadDataIntoView(WeatherData data)
    {
        ((TextView)findViewById(R.id.currTemp)).setText(String.valueOf(data.main.temp));
        ((TextView)findViewById(R.id.hilo)).setText(String.valueOf(data.main.temp_min) + "/" +
                String.valueOf(data.main.temp_max));

        ((TextView)findViewById(R.id.description)).setText(data.weather.main);
        ((TextView)findViewById(R.id.descriptionDetail)).setText(data.weather.description);
        ((TextView)findViewById(R.id.location)).setText(data.name);
    }
}
