package com.example.MyWeather.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.MyWeather.R;
import com.example.MyWeather.fragment.LocationFragment;
import com.example.MyWeather.fragment.WeatherFragment;

/**
 * Created by dave on 2/9/16.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    @Override
    public void onStart()
    {
        super.onStart();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        LocationFragment locationFragment = new LocationFragment();
        transaction.replace(R.id.content, locationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
