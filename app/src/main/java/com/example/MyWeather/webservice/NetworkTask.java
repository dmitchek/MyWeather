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

        public NetworkTask(Context context)
        {
            mContext = context;
        }

        public interface OnDataListener
        {
            void populate(WeatherData [] data);
        }

        protected OnDataListener mOnDataListener;

        protected JSONObject doInBackground(String... params)
        {
            // do nothing, this will be overridden
            return  null;

        }

        protected void onPostExecute(JSONObject result)
        {
            // do nothing, this will be overridden
        }
};
