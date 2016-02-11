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
public class NetworkTask extends AsyncTask<String, String, JSONObject> {

        protected Context mContext;
        //private static final String URI = "http://api.openweathermap.org/data/2.5/weather";

        private static final String TEST_DATA = "{\"coord\":{\"lon\":-84.45,\"lat\":38.05}," +
                "\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}]," +
                "\"base\":\"cmc stations\"," +
                "\"main\":{\"temp\":25.29,\"pressure\":1009.88,\"humidity\":59,\"temp_min\":25.29,\"temp_max\":25.29,\"sea_level\":1041.68," +
                "\"grnd_level\":1009.88},\"wind\":{\"speed\":5.23,\"deg\":302.003},\"clouds\":{\"all\":0},\"dt\":1454643104,\"sys\":{\"message\":0.0044," +
                "\"country\":\"US\",\"sunrise\":1454675938,\"sunset\":1454713504},\"id\":4291564,\"name\":\"Fayette County\",\"cod\":200}";

        private static final String TEST_DATA_MULTIPLE = "{\"cnt\":3,\"list\":[{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"sys\":{\"type\":1,\"id\":7323,\"message\":0.0439,\"country\":\"RU\",\"sunrise\":1454821882,\"sunset\":1454854555},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"main\":{\"temp\":0.5,\"pressure\":1019,\"temp_min\":0,\"temp_max\":1,\"humidity\":92},\"visibility\":10000,\"wind\":{\"speed\":7,\"deg\":210},\"clouds\":{\"all\":40},\"dt\":1454859000,\"id\":524901,\"name\":\"Moscow\"},{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"sys\":{\"type\":1,\"id\":7348,\"message\":0.0375,\"country\":\"UA\",\"sunrise\":1454822598,\"sunset\":1454857246},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\"main\":{\"temp\":-1,\"pressure\":1026,\"humidity\":80,\"temp_min\":-1,\"temp_max\":-1},\"visibility\":10000,\"wind\":{\"speed\":5,\"deg\":170},\"clouds\":{\"all\":0},\"dt\":1454862600,\"id\":703448,\"name\":\"Kiev\"},{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"sys\":{\"type\":1,\"id\":5093,\"message\":0.0506,\"country\":\"GB\",\"sunrise\":1454830122,\"sunset\":1454864431},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"main\":{\"temp\":7.98,\"pressure\":994,\"humidity\":61,\"temp_min\":7,\"temp_max\":9.4},\"visibility\":10000,\"wind\":{\"speed\":9.3,\"deg\":220},\"clouds\":{\"all\":20},\"dt\":1454863933,\"id\":2643743,\"name\":\"London\"}]}";

        public NetworkTask(Context context)
        {
            mContext = context;
        }

        public interface OnDataListener
        {
            public void populate(WeatherData [] data);
        }

        protected OnDataListener mOnDataListener;

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
                JSONObject result = new JSONObject(TEST_DATA_MULTIPLE);
                return result;

            }catch(JSONException e)
            {
                return null;
            }

        }

        protected void onPostExecute(JSONObject result)
        {
            //WeatherData [] data = new WeatherData[2];
            //data[0] = WeatherParser.getWeatherData(result);
            //data[1] = WeatherParser.getWeatherData(result);
            mOnDataListener.populate(WeatherParser.getWeatherData(result));
        }
};
