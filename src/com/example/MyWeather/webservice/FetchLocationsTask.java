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

    private String TEST_DATA = "{\"message\":\"like\",\"cod\":\"200\",\"count\":5,\"list\":[{\"id\":4297999,\"name\":\"Lexington-Fayette\",\"coord\":{\"lon\":-84.458549,\"lat\":38.049801},\"main\":{\"temp\":18.57,\"pressure\":1015,\"humidity\":78,\"temp_min\":17.6,\"temp_max\":19.4},\"dt\":1455077700,\"wind\":{\"speed\":13.39,\"deg\":260},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":90},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}]},{\"id\":4475773,\"name\":\"Lexington\",\"coord\":{\"lon\":-80.25338,\"lat\":35.824032},\"main\":{\"temp\":27.77,\"temp_min\":27.77,\"temp_max\":27.77,\"pressure\":1001.75,\"sea_level\":1025.9,\"grnd_level\":1001.75,\"humidity\":44},\"dt\":1455077870,\"wind\":{\"speed\":10,\"deg\":297.504},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}]},{\"id\":4941935,\"name\":\"Lexington\",\"coord\":{\"lon\":-71.224503,\"lat\":42.447319},\"main\":{\"temp\":18.86,\"pressure\":1008,\"humidity\":64,\"temp_min\":12.2,\"temp_max\":39.2},\"dt\":1455078155,\"wind\":{\"speed\":8.86,\"deg\":290,\"gust\":7.2},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":90},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}]},{\"id\":4297983,\"name\":\"Lexington\",\"coord\":{\"lon\":-84.477722,\"lat\":37.988689},\"main\":{\"temp\":22.19,\"temp_min\":22.19,\"temp_max\":22.19,\"pressure\":994.05,\"sea_level\":1029.22,\"grnd_level\":994.05,\"humidity\":66},\"dt\":1455077477,\"wind\":{\"speed\":12.59,\"deg\":269.504},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":80},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}]},{\"id\":4585000,\"name\":\"Lexington\",\"coord\":{\"lon\":-81.236214,\"lat\":33.981541},\"main\":{\"temp\":32.65,\"pressure\":1014,\"temp_min\":30.2,\"temp_max\":35.6,\"humidity\":51},\"dt\":1455078327,\"wind\":{\"speed\":7.78,\"deg\":260,\"gust\":5.1},\"sys\":{\"country\":\"US\"},\"clouds\":{\"all\":90},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}]}]}";

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
        if(result != null)
            mListener.populate(WeatherParser.getWeatherData(result));
        else
        {
            Toast toast = Toast.makeText(mContext, "location not found", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
