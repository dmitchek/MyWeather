package com.example.MyWeather.webservice;

import android.content.Context;
import android.os.AsyncTask;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.data.WeatherParser;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherTask extends AsyncTask<String, String, JSONObject> {

        private Context mContext;
        //private static final String URI = "http://api.openweathermap.org/data/2.5/weather";

        private static final String TEST_DATA = "{\"coord\":{\"lon\":-84.45,\"lat\":38.05}," +
                "\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}]," +
                "\"base\":\"cmc stations\"," +
                "\"main\":{\"temp\":25.29,\"pressure\":1009.88,\"humidity\":59,\"temp_min\":25.29,\"temp_max\":25.29,\"sea_level\":1041.68," +
                "\"grnd_level\":1009.88},\"wind\":{\"speed\":5.23,\"deg\":302.003},\"clouds\":{\"all\":0},\"dt\":1454643104,\"sys\":{\"message\":0.0044," +
                "\"country\":\"US\",\"sunrise\":1454675938,\"sunset\":1454713504},\"id\":4291564,\"name\":\"Fayette County\",\"cod\":200}";

        public WeatherTask(Context context)
        {
            mContext = context;
        }

        public interface OnDataListener
        {
            public void populate(WeatherData [] data);
        }

        OnDataListener mOnDataListener;

        public void fetchWeatherData(String params, OnDataListener listener)
        {
            mOnDataListener = listener;
            execute(params);
        }

        protected JSONObject doInBackground(String... params)
        {
            //return FetchWeatherInfo.getJSON(mContext, params[0]);

            try
            {
                JSONObject result = new JSONObject(TEST_DATA);
                return result;

            }catch(JSONException e)
            {
                return null;
            }

        }

        protected void onPostExecute(JSONObject result)
        {
            WeatherData [] data = new WeatherData[2];
            data[0] = WeatherParser.getWeatherData(result);
            data[1] = WeatherParser.getWeatherData(result);
            mOnDataListener.populate(data);
        }
};
