package com.example.MyWeather.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherData implements Serializable {

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
        public int temp;
        public double pressure;
        public int humidity;
        public int temp_min;
        public int temp_max;
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

    private void writeObject(ObjectOutputStream out)
            throws IOException
    {
        out.writeObject(this);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException
    {
        WeatherData data = (WeatherData)in.readObject();
        this.id = data.id;
        this.name = data.name;
        this.country = data.country;
        this.weather.description = data.weather.description;
        this.weather.icon = data.weather.icon;
        this.weather.main = data.weather.main;
        this.weather.id = data.weather.id;
        this.main.humidity = data.main.humidity;
        this.main.pressure = data.main.pressure;
        this.main.temp = data.main.temp;
        this.main.temp_max = data.main.temp_max;
        this.main.temp_min = data.main.temp_min;
        this.wind.deg = data.wind.deg;
        this.wind.speed = data.wind.speed;
    }


}
