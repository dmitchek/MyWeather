package com.example.MyWeather.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.MyWeather.data.WeatherData;

/**
 * Created by dmitchell on 2/6/2016.
 */
public class WeatherParser {

    /*public static final String TAG_WEATHER = "weather";
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
    public static final String TAG_COUNT = "cnt";
    public static final String TAG_LIST = "list";*/

    public static WeatherData [] getWeatherData(JSONObject data) {
        WeatherData [] weatherData;

        try
        {
            if(data.has(WeatherData.TAG_COUNT) &&  data.getInt(WeatherData.TAG_COUNT) > 1)
            {
                int count = data.getInt(WeatherData.TAG_COUNT);
                weatherData = new WeatherData[count];
                JSONArray locations = data.getJSONArray(WeatherData.TAG_LIST);
                for(int i = 0; i < count; i++)
                {
                    WeatherData newData = new WeatherData();
                    JSONObject curLocation = (JSONObject)locations.get(i);
                    newData.weather = parseWeather(curLocation.getJSONArray(WeatherData.TAG_WEATHER).getJSONObject(0));
                    newData.main = parseMain(curLocation.getJSONObject(WeatherData.TAG_MAIN));
                    newData.wind = parseWind(curLocation.getJSONObject(WeatherData.TAG_WIND));
                    newData.id = curLocation.getInt(WeatherData.TAG_ID);
                    newData.name = curLocation.getString(WeatherData.TAG_NAME);
                    newData.country = curLocation.getJSONObject(WeatherData.TAG_SYS).getString(WeatherData.TAG_COUNTRY);
                    weatherData[i] = newData;
                }

            }
            else
            {
                weatherData = new WeatherData[1];
                WeatherData newData = new WeatherData();
                newData.weather = parseWeather(data.getJSONArray(WeatherData.TAG_WEATHER).getJSONObject(0));
                newData.main = parseMain(data.getJSONObject(WeatherData.TAG_MAIN));
                newData.wind = parseWind(data.getJSONObject(WeatherData.TAG_WIND));
                newData.id = data.getInt(WeatherData.TAG_ID);
                newData.name = data.getString(WeatherData.TAG_NAME);
                weatherData[0] = newData;
            }



        }catch(JSONException e)
        {
            return null;
        }

        return weatherData;
    }

    private static WeatherData.Weather parseWeather(JSONObject data)
    {
        WeatherData.Weather weather = new WeatherData.Weather();

        try
        {
            weather.id = data.getInt(WeatherData.TAG_ID);
            weather.main = data.getString(WeatherData.TAG_MAIN);
            weather.description = data.getString(WeatherData.TAG_DESCRIPTION);
            weather.icon = data.getString(WeatherData.TAG_ICON);

        }catch(JSONException e){}

        return weather;
    }

    private static WeatherData.Main parseMain(JSONObject data)
    {
        WeatherData.Main main = new WeatherData.Main();

        try
        {
            main.temp = data.getDouble(WeatherData.TAG_TEMP);
            main.pressure = data.getDouble(WeatherData.TAG_PRESSURE);
            main.humidity = data.getInt(WeatherData.TAG_HUMIDITY);
            main.temp_max = data.getDouble(WeatherData.TAG_TEMP_MAX);
            main.temp_min = data.getDouble(WeatherData.TAG_TEMP_MIN);


        }catch(JSONException e){}

        return main;
    }

    private static WeatherData.Wind parseWind(JSONObject data)
    {
        WeatherData.Wind wind = new WeatherData.Wind();

        try
        {
            wind.speed = data.getDouble(WeatherData.TAG_SPEED);
            wind.deg = data.getDouble(WeatherData.TAG_DEG);
        }catch (JSONException e){}

        return wind;
    }

}
