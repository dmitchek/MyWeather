package com.example.MyWeather.webservice;

import android.os.AsyncTask;
import com.example.MyWeather.data.WeatherData;


/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherTask extends AsyncTask<String, String, String> {

        private static final String URI = "http://api.openweathermap.org/data/2.5/weather";

        public interface OnDataListener
        {
            public void populate(WeatherData data);
        }

        OnDataListener mOnDataListener;

        public void fetchWeatherData(String params, OnDataListener listener)
        {
            mOnDataListener = listener;
            execute(params);
        }

        protected String doInBackground(String... params)
        {
            return "";
        }

        protected void onPostExecute(String result)
        {
            WeatherData data = new WeatherData();
            data.temp = 30.0;
            data.temp_max = 75.5;
            data.temp_min = 20.0;
            data.weather = "Sunny!";

            mOnDataListener.populate(data);
        }
};
