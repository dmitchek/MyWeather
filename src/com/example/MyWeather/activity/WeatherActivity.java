package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.example.MyWeather.R;
import com.example.MyWeather.adapter.WeatherAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.WeatherTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitchell on 2/5/2016.
 */
public class WeatherActivity extends Activity {

    WeatherTask mWeatherTask;
    private String mLocation;

    private class OnWeatherDataListener implements WeatherTask.OnDataListener
    {
        @Override
        public void populate(WeatherData [] data) {
            Log.d("DKM", "Data received!");

            WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), R.layout.weather_info, data);

            ListView list = (ListView)findViewById(R.id.weather_list);
            list.setAdapter(adapter);
        }
    }

    private OnWeatherDataListener mWeatherDataListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        Intent intent = getIntent();

        mLocation = intent.getStringExtra("location");
        Log.d("DKM", "Location: " + mLocation);

        // Get weather for given location
        mWeatherDataListener = new OnWeatherDataListener();
        mWeatherTask = new WeatherTask(getApplicationContext());
        mWeatherTask.fetchWeatherData(mLocation, mWeatherDataListener);
    }
}
