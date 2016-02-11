package com.example.MyWeather.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.fragment.FindLocationFragment;
import com.example.MyWeather.fragment.LocationFragment;
import com.example.MyWeather.fragment.UserFragment;
import com.example.MyWeather.fragment.WeatherFragment;
import com.example.MyWeather.utils.FragmentUtil;

/**
 * Created by dave on 2/9/16.
 */
public class MainActivity extends Activity {

    private final static String STORED_USER_KEY = "user";
    private String mCurUser;
    private String [] mUserLocations;
    private Activity mActivity;

    public FindLocationFragment mLocationFragment;
    public UserFragment mUserFragment;
    public WeatherFragment mWeatherFragment;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        mActivity = this;

        mLocationFragment = new FindLocationFragment();
        mUserFragment = new UserFragment();
        mWeatherFragment = new WeatherFragment();

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

        // We have user, we have locations, go directly to weather screen
        if(mCurUser != null && (mUserLocations != null && mUserLocations.length > 0)) {

            Bundle args = new Bundle();

            args.putStringArray("locations", mUserLocations);
            //fragment = new WeatherFragment();
            mWeatherFragment.setArguments(args);

            FragmentUtil.switchFragment(this, null, R.id.content, mWeatherFragment, args);
        }
        else
        {
            //fragment = new FindLocationFragment();

            FragmentUtil.switchFragment(this, null, R.id.content, mLocationFragment);
        }

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
