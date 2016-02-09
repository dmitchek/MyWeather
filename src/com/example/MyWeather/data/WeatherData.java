package com.example.MyWeather.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherData {

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
    public static final String TAG_COUNT = "count";
    public static final String TAG_LIST = "list";
    public static final String TAG_SYS = "sys";
    public static final String TAG_COUNTRY = "country";

    public static class Weather
    {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Main
    {
        public double temp;
        public double pressure;
        public int humidity;
        public double temp_min;
        public double temp_max;
    }

    public static class Wind
    {
        public double speed;
        public double deg;
    }


    @Override
    public String toString()
    {
        return name + ", " + country;
    }

    public Weather weather;
    public Main main;
    public Wind wind;
    public int id;
    public String name;
    public String country;
}
