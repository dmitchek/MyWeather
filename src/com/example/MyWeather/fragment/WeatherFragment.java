package com.example.MyWeather.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.MyWeather.R;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.FetchWeatherTask;
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
    private Spinner mLocationSpinner;

    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData [] data) {
            Log.d("DKM", "Data received!");

            mLocationData = new ArrayList<WeatherData>(Arrays.asList(data));

            if(mLocationData.size() == 1)
            {
                getView().findViewById(R.id.prev_location).setVisibility(View.INVISIBLE);
                getView().findViewById(R.id.next_location).setVisibility(View.INVISIBLE);
            }

            loadDataIntoView(mLocationData.get(0));

            getView().findViewById(R.id.weather_content).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.loading_screen).setVisibility(View.GONE);
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

    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();

        String [] locations = args.getStringArray("locations");

        if(locations != null && locations.length > 0)
        {
            // Show loading screen and load locations
            getView().findViewById(R.id.weather_content).setVisibility(View.GONE);
            getView().findViewById(R.id.loading_screen).setVisibility(View.VISIBLE);

            mWeatherDataListener = new OnDataListener();
            mNetworkTask = new FetchWeatherTask(getActivity().getApplicationContext(), mWeatherDataListener);

            String locationStr = "";
            for(int i = 0; i < locations.length; i++)
            {
                locationStr +=locations[i];

                if(i < locations.length-1)
                    locationStr += ",";

            }

            mNetworkTask.execute(locationStr);
        }
        else
        {
            getView().findViewById(R.id.weather_content).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.loading_screen).setVisibility(View.GONE);

            // We don't have locations data, lets assume we have a single WeatherData object then
            WeatherData data = (WeatherData)args.getSerializable("data");
            loadDataIntoView(data);
        }

        ImageButton next = (ImageButton)getView().findViewById(R.id.next_location);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(RIGHT);
            }
        });

        ImageButton previous = (ImageButton)getView().findViewById(R.id.prev_location);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView(LEFT);
            }
        });
        previous.setEnabled(false);
        mCurrentPosition = 0;
    }

    private void loadView(int direction)
    {
        int newPosition = mCurrentPosition + direction;
        if((newPosition < mLocationData.size()) && (newPosition >= 0))
        {
            mCurrentPosition = newPosition;
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }

        if(mCurrentPosition == 0)
        {
            getView().findViewById(R.id.prev_location).setEnabled(false);
            getView().findViewById(R.id.next_location).setEnabled(true);
        }
        else if(mCurrentPosition == (mLocationData.size()-1))
        {
            getView().findViewById(R.id.prev_location).setEnabled(true);
            getView().findViewById(R.id.next_location).setEnabled(false);
        }
        else
        {
            getView().findViewById(R.id.prev_location).setEnabled(true);
            getView().findViewById(R.id.next_location).setEnabled(true);
        }
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
