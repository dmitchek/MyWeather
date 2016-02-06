package com.example.MyWeather.data;

/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherData {

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

    public Weather weather;
    public Main main;
    public Wind wind;
    public int id;
    public String name;
}
