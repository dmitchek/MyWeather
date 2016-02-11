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

    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/find?mode=json&type=like&q=%s&cnt=10&units=imperial&APPID=%s";


    public static JSONObject getJSON(Context context, String city) {
        try {
            String apikey = context.getString(R.string.api_key);
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city, apikey));
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

            // This value will be 404 if the request was not
            // successful
            if (data.getInt("cod") != 200) {
                return null;
            }

            connection.disconnect();

            return data;
        } catch (Exception e) {
            Log.d("MyWeather", "Failed to retrieve weather info: " + e.toString());
            return null;
        }
    }
}
