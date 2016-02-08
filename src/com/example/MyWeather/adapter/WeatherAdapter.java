package com.example.MyWeather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.MyWeather.R;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.WeatherTask;

import java.util.zip.Inflater;

/**
 * Created by dmitchell on 2/4/2016.
 */
public class WeatherAdapter {

    private WeatherTask mWeatherTask;

    private WeatherTask.OnDataListener mWeatherDataListener;

    public WeatherAdapter(Context context, int viewId, WeatherData [] data)
    {
        //super(context, viewId, data);

        mWeatherTask = new WeatherTask(context);
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        WeatherData data = getItem(position);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView.inflate(getContext(),R.layout.weather_info, parent);

        ((TextView)view.findViewById(R.id.currTemp)).setText(String.valueOf(data.main.temp));
        ((TextView)view.findViewById(R.id.hilo)).setText(String.valueOf(data.main.temp_min) + "/" +
                String.valueOf(data.main.temp_max));

        ((TextView)view.findViewById(R.id.description)).setText(data.weather.main);
        ((TextView)view.findViewById(R.id.descriptionDetail)).setText(data.weather.description);
        ((TextView)view.findViewById(R.id.location)).setText("40508");

        return view;
    }*/




}
