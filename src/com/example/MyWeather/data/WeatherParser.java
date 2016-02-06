package com.example.MyWeather.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dmitchell on 2/6/2016.
 */
public class WeatherParser {

    public static final String TAG_WEATHER = "weather";
    public static final String TAG_ID = "id";
    public static final String TAG_MAIN = "main";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_ICON = "icon";
    public static final String TAG_TEMP = "temp";
    public static final String TAG_TEMP_MIN = "temp_min";
    public static final String TAG_TEMP_MAX = "temp_max";
    public static final String TAG_PRESSURE = "pressure";
    public static final String TAG_HUMIDITY = "humidity";
    public static final String TAG_WIND = "wind";
    public static final String TAG_SPEED = "speed";
    public static final String TAG_DEG = "deg";
    public static final String TAG_NAME = "name";

    public static WeatherData getWeatherData(JSONObject data) {
        WeatherData weatherData = new WeatherData();

        try
        {
            weatherData.weather = parseWeather(data.getJSONArray(TAG_WEATHER).getJSONObject(0));
            weatherData.main = parseMain(data.getJSONObject(TAG_MAIN));
            weatherData.wind = parseWind(data.getJSONObject(TAG_WIND));
            weatherData.id = data.getInt(TAG_ID);
            weatherData.name = data.getString(TAG_NAME);


        }catch(JSONException e){}

        return weatherData;
    }

    private static WeatherData.Weather parseWeather(JSONObject data)
    {
        WeatherData.Weather weather = new WeatherData.Weather();

        try
        {
            weather.id = data.getInt(TAG_ID);
            weather.main = data.getString(TAG_MAIN);
            weather.description = data.getString(TAG_DESCRIPTION);
            weather.icon = data.getString(TAG_ICON);

        }catch(JSONException e){}

        return weather;
    }

    private static WeatherData.Main parseMain(JSONObject data)
    {
        WeatherData.Main main = new WeatherData.Main();

        try
        {
            main.temp = data.getDouble(TAG_TEMP);
            main.pressure = data.getDouble(TAG_PRESSURE);
            main.humidity = data.getInt(TAG_HUMIDITY);
            main.temp_max = data.getDouble(TAG_TEMP_MAX);
            main.temp_min = data.getDouble(TAG_TEMP_MIN);

        }catch(JSONException e){}

        return main;
    }

    private static WeatherData.Wind parseWind(JSONObject data)
    {
        WeatherData.Wind wind = new WeatherData.Wind();

        try
        {
            wind.speed = data.getDouble(TAG_SPEED);
            wind.deg = data.getDouble(TAG_DEG);
        }catch (JSONException e){}

        return wind;
    }

}
