package com.example.MyWeather.webservice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.data.WeatherParser;
import com.example.MyWeather.webservice.NetworkTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dave on 2/8/16.
 */
public class FetchLocationsTask extends NetworkTask {

    private OnDataListener mListener;

    public FetchLocationsTask(Context context, OnDataListener listener)
    {
        super(context);
        mListener = listener;
    }

    protected JSONObject doInBackground(String... params)
    {
        return FetchWeatherInfo.getWeatherByCity(mContext, params[0]);
    }

    protected void onPostExecute(JSONObject result)
    {
        if(result != null)
            mListener.populate(WeatherParser.getWeatherData(result));
        else
        {
            Toast toast = Toast.makeText(mContext, "location not found", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
