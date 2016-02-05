package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.MyWeather.R;
import com.example.MyWeather.adapter.WeatherAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.WeatherTask;

/**
 * Created by dmitchell on 2/5/2016.
 */
public class WeatherActivity extends Activity {

    WeatherAdapter mWeatherAdapter;

    private class OnWeatherDataListener implements WeatherTask.OnDataListener
    {
        @Override
        public void populate(WeatherData data) {
            Log.d("DKM", "Data received!");


        }
    }

    private OnWeatherDataListener mWeatherDataListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        Intent intent = getIntent();

        String location = intent.getStringExtra("location");
        Log.d("DKM", "Location: " + location);

        // Get weather for given location
        mWeatherDataListener = new OnWeatherDataListener();
        mWeatherAdapter = new WeatherAdapter();
        mWeatherAdapter.setOnWeatherDataListener(mWeatherDataListener);
        mWeatherAdapter.getWeatherFromZip(location);

    }


}
