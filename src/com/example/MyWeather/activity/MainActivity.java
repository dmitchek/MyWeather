package com.example.MyWeather.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.fragment.LocationFragment;
import com.example.MyWeather.fragment.WeatherFragment;
import com.example.MyWeather.utils.FragmentUtil;

/**
 * Created by dave on 2/9/16.
 */
public class MainActivity extends Activity {

    private final static String STORED_USER_KEY = "user";
    private String mCurUser;
    private String [] mUserLocations;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mCurUser = prefs.getString(STORED_USER_KEY, null);
        //mCurUser = "dave";

        if(mCurUser != null)
        {
            // fetch user data from the database
            UsersAdapter usersAdapter = new UsersAdapter();
            usersAdapter.open(getApplicationContext());

            mUserLocations = usersAdapter.getUserLocations(mCurUser);
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();

        Fragment fragment;
        Bundle args = new Bundle();
        // We have user, we have locations, go directly to weather screen
        if(mCurUser != null && (mUserLocations != null && mUserLocations.length > 0)) {

            args.putStringArray("locations", mUserLocations);
            fragment = new WeatherFragment();
            fragment.setArguments(args);
        }
        else
        {
            args = null;
            fragment = new LocationFragment();
        }

        FragmentUtil.switchFragment(this, fragment, args);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        if(mCurUser != null) {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(STORED_USER_KEY, mCurUser);
        }
    }

}
