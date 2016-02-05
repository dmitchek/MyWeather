package com.example.MyWeather.adapter;

import com.example.MyWeather.webservice.WeatherTask;

/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherAdapter {

    private WeatherTask mWeatherTask;

    private WeatherTask.OnDataListener mWeatherDataListener;

    public WeatherAdapter()
    {
        mWeatherTask = new WeatherTask();
    }

    public void setOnWeatherDataListener(WeatherTask.OnDataListener listener)
    {
        mWeatherDataListener = listener;
    }

    public void getWeatherFromZip(String zip)
    {
        mWeatherTask.fetchWeatherData(zip, mWeatherDataListener);
    }
}
