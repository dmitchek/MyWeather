package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    WeatherAdapter mWeatherAdapter;
    private String mLocation;

    private class OnWeatherDataListener implements WeatherTask.OnDataListener
    {
        @Override
        public void populate(WeatherData data) {
            Log.d("DKM", "Data received!");

            ((TextView)findViewById(R.id.currTemp)).setText(String.valueOf(data.main.temp));
            ((TextView)findViewById(R.id.hilo)).setText(String.valueOf(data.main.temp_min) + "/" +
                                                         String.valueOf(data.main.temp_max));

            ((TextView)findViewById(R.id.description)).setText(data.weather.main);
            ((TextView)findViewById(R.id.descriptionDetail)).setText(data.weather.description);
            ((TextView)findViewById(R.id.location)).setText(mLocation);

        }
    }

    private OnWeatherDataListener mWeatherDataListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        Intent intent = getIntent();

        mLocation = intent.getStringExtra("location");
        Log.d("DKM", "Location: " + mLocation);

        // Get weather for given location
        mWeatherDataListener = new OnWeatherDataListener();
        mWeatherAdapter = new WeatherAdapter();
        mWeatherAdapter.setOnWeatherDataListener(mWeatherDataListener);
        mWeatherAdapter.getWeatherFromZip(mLocation);

    }
}
