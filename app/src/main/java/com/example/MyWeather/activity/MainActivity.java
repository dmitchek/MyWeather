package com.example.MyWeather.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.fragment.FindLocationFragment;
import com.example.MyWeather.fragment.UserFragment;
import com.example.MyWeather.fragment.WeatherFragment;
import com.example.MyWeather.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dave on 2/9/16.
 */
public class MainActivity extends Activity {

    private String mCurUser;

    private ArrayList<WeatherData> mLocationsData;

    public FindLocationFragment mLocationFragment;
    public UserFragment mUserFragment;
    public WeatherFragment mWeatherFragment;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mLocationsData = new ArrayList<WeatherData>();

        mLocationFragment = new FindLocationFragment();
        mUserFragment = new UserFragment();
        mWeatherFragment = new WeatherFragment();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        FragmentUtil.switchFragment(this, R.id.content, mLocationFragment);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        if(mCurUser != null) {
            // Store current user and their locations
            UsersAdapter adapter = new UsersAdapter();
            adapter.open(this);
            adapter.insertOrUpdateUser(mCurUser, getUserLocations());
            adapter.close();
        }
    }

    public void setCurrentUser(String user)
    {
        mCurUser = user;
    }

    public String getUserLocations()
    {
        String locations = "";

        // Build user location string
        Iterator<WeatherData> iter = mLocationsData.iterator();
        while(iter.hasNext())
        {
            WeatherData data = iter.next();
            locations += data.id;

            if(iter.hasNext())
                locations += ",";
        }

        return locations;

    }

    public void setLocationData(ArrayList<WeatherData> data)
    {
        mLocationsData = data;
    }

    public void addLocation(WeatherData data)
    {
        mLocationsData.add(data);
    }

    public ArrayList<WeatherData> getWeatherData()
    {
        return mLocationsData;
    }



}
