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
    private String TEST_DATA = "{\"cnt\":3,\"list\":[{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"sys\":{\"type\":1,\"id\":7323,\"message\":0.043,\"country\":\"RU\",\"sunrise\":1455166973,\"sunset\":1455200677},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"main\":{\"temp\":1,\"pressure\":1016,\"humidity\":80,\"temp_min\":1,\"temp_max\":1},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":160},\"clouds\":{\"all\":90},\"dt\":1455150600,\"id\":524901,\"name\":\"Moscow\"},{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0294,\"country\":\"UA\",\"sunrise\":1455167789,\"sunset\":1455203267},\"weather\":[{\"id\":520,\"main\":\"Rain\",\"description\":\"light intensity shower rain\",\"icon\":\"09n\"}],\"main\":{\"temp\":3.05,\"pressure\":1001,\"humidity\":86,\"temp_min\":2,\"temp_max\":4},\"visibility\":10000,\"wind\":{\"speed\":8,\"deg\":140,\"var_beg\":110,\"var_end\":170},\"clouds\":{\"all\":90},\"dt\":1455150600,\"id\":703448,\"name\":\"Kiev\"},{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"sys\":{\"message\":0.006,\"country\":\"GB\",\"sunrise\":1455175295,\"sunset\":1455210470},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\"main\":{\"temp\":-0.28,\"temp_min\":-0.27599999999995,\"temp_max\":-0.27599999999995,\"pressure\":1006.39,\"sea_level\":1016.54,\"grnd_level\":1006.39,\"humidity\":91},\"wind\":{\"speed\":2.46,\"deg\":260.001},\"clouds\":{\"all\":0},\"dt\":1455152491,\"id\":2643743,\"name\":\"London\"}]}";

    private NetworkTask.OnDataListener mListener;

    public FetchWeatherTask(Context context, NetworkTask.OnDataListener listener)
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
