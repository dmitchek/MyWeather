package com.example.MyWeather.webservice;

import android.content.Context;
import android.widget.Toast;

import com.example.MyWeather.data.WeatherParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dave on 2/10/16.
 */
public class FetchWeatherTask extends NetworkTask {

    private NetworkTask.OnDataListener mListener;

    public FetchWeatherTask(Context context, NetworkTask.OnDataListener listener)
    {
        super(context);
        mListener = listener;
    }

    protected JSONObject doInBackground(String... params)
    {
        return FetchWeatherInfo.getWeatherByIds(mContext, params[0]);
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
