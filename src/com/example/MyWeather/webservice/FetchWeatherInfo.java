package com.example.MyWeather.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.MyWeather.R;

public class FetchWeatherInfo {

    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String FIND_BY_CITY =
            "find?mode=json&type=like&q=%s&cnt=10";
    private static final String FIND_BY_IDS =
            "group?id=%s";
    private static final String ENDING = "&units=imperial&APPID=%s";


    public static JSONObject getWeatherByCity(Context context, String city)
    {
        String uri = OPEN_WEATHER_URL + FIND_BY_CITY;
        return getJSON(context, String.format(uri, city) + ENDING);
    }

    public static JSONObject getWeatherByIds(Context context, String ids)
    {
        String uri = OPEN_WEATHER_URL + FIND_BY_IDS;
        return getJSON(context, String.format(uri, ids) + ENDING);
    }

    private static JSONObject getJSON(Context context, String uri) {
        try {
            String apikey = context.getString(R.string.api_key);
            URL url = new URL(String.format(uri, apikey));
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            connection.disconnect();

            return data;
        } catch (Exception e) {
            Log.d("MyWeather", "Failed to retrieve weather info: " + e.toString());
            return null;
        }
    }
}
