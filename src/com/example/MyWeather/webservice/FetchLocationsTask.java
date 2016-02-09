package com.example.MyWeather.webservice;

import android.content.Context;
import android.util.Log;

import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.data.WeatherParser;
import com.example.MyWeather.webservice.NetworkTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dave on 2/8/16.
 */
public class FetchLocationsTask extends NetworkTask {

    //OnDataListener mListener;
    private String TEST_DATA = "{\"message\":\"like\",\"cod\":\"200\",\"count\":5,\"list\":[{\"id\":4297999,\"name\":\"Lexington-Fayette\",\"coord\":{\"lon\":-84.458549,\"lat\":38.049801},\"main\":{\"temp\":272.812,\"temp_min\":272.812,\"temp_max\":272.812,\"pressure\":990.4,\"sea_level\":1021.48,\"grnd_level\":990.4,\"humidity\":72},\"dt\":1454974017,\"wind\":{\"speed\":5.45,\"deg\":284.009},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":92},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}]},{\"id\":4475773,\"name\":\"Lexington\",\"coord\":{\"lon\":-80.25338,\"lat\":35.824032},\"main\":{\"temp\":278.52,\"pressure\":1004,\"humidity\":93,\"temp_min\":277.15,\"temp_max\":280.15},\"dt\":1454974216,\"wind\":{\"speed\":6.73,\"deg\":320.002},\"sys\":{\"country\":\"US\"},\"rain\":{\"1h\":1},\"clouds\":{\"all\":40},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"},{\"id\":301,\"main\":\"Drizzle\",\"description\":\"drizzle\",\"icon\":\"09n\"}]},{\"id\":4941935,\"name\":\"Lexington\",\"coord\":{\"lon\":-71.224503,\"lat\":42.447319},\"main\":{\"temp\":267.53,\"pressure\":1009,\"humidity\":79,\"temp_min\":265.15,\"temp_max\":279.15},\"dt\":1454973360,\"wind\":{\"speed\":4.1,\"deg\":340},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":90},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"},{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}]},{\"id\":4297983,\"name\":\"Lexington\",\"coord\":{\"lon\":-84.477722,\"lat\":37.988689},\"main\":{\"temp\":272.287,\"temp_min\":272.287,\"temp_max\":272.287,\"pressure\":988.13,\"sea_level\":1022.41,\"grnd_level\":988.13,\"humidity\":80},\"dt\":1454974017,\"wind\":{\"speed\":7.7,\"deg\":282.009},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":92},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}]},{\"id\":4585000,\"name\":\"Lexington\",\"coord\":{\"lon\":-81.236214,\"lat\":33.981541},\"main\":{\"temp\":280.82,\"pressure\":1007,\"temp_min\":280.15,\"temp_max\":281.45,\"humidity\":68},\"dt\":1454974523,\"wind\":{\"speed\":3.6,\"deg\":210,\"gust\":5.1},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":40},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}]}]}";

    private OnDataListener mListener;

    public FetchLocationsTask(Context context, OnDataListener listener)
    {
        super(context);
        mListener = listener;
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
        //WeatherData [] data = new WeatherData[2];
        //data[0] = WeatherParser.getWeatherData(result);
        //data[1] = WeatherParser.getWeatherData(result);
        mListener.populate(WeatherParser.getWeatherData(result));
    }

}
