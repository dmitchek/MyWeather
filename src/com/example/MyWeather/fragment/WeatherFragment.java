package com.example.MyWeather.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.MyWeather.R;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.NetworkTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dave on 2/9/16.
 */
public class WeatherFragment extends Fragment {

    NetworkTask mNetworkTask;
    private String mLocation;
    private int mCurrentPosition;
    private ArrayList<WeatherData> mLocationData;

    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData [] data) {
            Log.d("DKM", "Data received!");

            mLocationData = new ArrayList<WeatherData>(Arrays.asList(data));
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }
    }

    private OnDataListener mWeatherDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.weather_info, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DKM", "Location: " + mLocation);

        // Get weather for given location
        //mWeatherDataListener = new OnDataListener();
        //mNetworkTask = new NetworkTask(getApplicationContext());
        //mNetworkTask.fetchWeatherData(mLocation, mWeatherDataListener);





    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        loadDataIntoView((WeatherData)args.getSerializable("data"));

        /*Button next = (Button)getView().findViewById(R.id.next_location);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(RIGHT);
            }
        });

        Button previous = (Button)getView().findViewById(R.id.previous_location);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(LEFT);
            }
        });
        previous.setEnabled(false);
        mCurrentPosition = 0;*/
    }

    private void loadView(int direction)
    {
        /*int newPosition = mCurrentPosition + direction;
        if((newPosition < mLocationData.size()) && (newPosition >= 0))
        {
            mCurrentPosition = newPosition;
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }

        if(mCurrentPosition == 0)
        {
            getView().findViewById(R.id.previous_location).setEnabled(false);
            getView().findViewById(R.id.next_location).setEnabled(true);
        }
        else if(mCurrentPosition == (mLocationData.size()-1))
        {
            getView().findViewById(R.id.previous_location).setEnabled(true);
            getView().findViewById(R.id.next_location).setEnabled(false);
        }
        else
        {
            getView().findViewById(R.id.previous_location).setEnabled(true);
            getView().findViewById(R.id.next_location).setEnabled(true);
        }*/
    }

    private void loadDataIntoView(WeatherData data)
    {
        ((TextView)getView().findViewById(R.id.currTemp)).setText(String.valueOf(data.main.temp) + "°");
        //((TextView)getView().findViewById(R.id.hilo)).setText(String.valueOf(data.main.temp_min) + "/" +
        //        String.valueOf(data.main.temp_max));

        ((TextView)getView().findViewById(R.id.description)).setText(data.weather.main);
        ((TextView)getView().findViewById(R.id.descriptionDetail)).setText(data.weather.description);
        ((TextView)getView().findViewById(R.id.location)).setText(data.name);
    }

}
