package com.example.MyWeather.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MyWeather.R;
import com.example.MyWeather.activity.MainActivity;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dave on 2/9/16.
 */
public class WeatherFragment extends Fragment {
    private int mCurrentPosition;
    private ArrayList<WeatherData> mLocationData;
    private MainActivity mActivity;

    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    public WeatherFragment()
    {
        setArguments(new Bundle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.weather_info, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        mActivity = (MainActivity)getActivity();
        mLocationData = mActivity.getWeatherData();

        if(mLocationData != null && mLocationData.size() > 0)
        {
            mCurrentPosition = 0;

            loadDataIntoView(mActivity.getWeatherData().get(mCurrentPosition));

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
            previous.setVisibility(View.INVISIBLE);

            if(mLocationData.size()== 1)
                next.setVisibility(View.INVISIBLE);
        }

        Button addLocation = (Button)getView().findViewById(R.id.add_location);
        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.switchFragment(getActivity(), R.id.content, mActivity.mLocationFragment);
            }
        });

        Button switchUser = (Button)getView().findViewById(R.id.switch_user);
        switchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.switchFragment(getActivity(), R.id.content, mActivity.mUserFragment);
            }
        });

        Button saveUser = (Button)getView().findViewById(R.id.save_user);
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mActivity, android.R.style.Theme_Light));
            builder.setView(R.layout.username_input);
            builder.setTitle("Please enter a name");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                EditText user = (EditText)((AlertDialog)dialog).findViewById(R.id.user_name);
                String userStr = user.getText().toString();
                mActivity.setCurrentUser(user.getText().toString());

                UsersAdapter adapter = new UsersAdapter();
                adapter.open(getActivity());
                long id = adapter.insertOrUpdateUser(userStr, mActivity.getUserLocations());

                Toast toast;
                if(id > 0) {
                    toast = Toast.makeText(mActivity, "user saved", Toast.LENGTH_SHORT);

                }
                else
                {
                    toast = Toast.makeText(mActivity, "unable to save user", Toast.LENGTH_SHORT);
                }
                toast.show();
                adapter.close();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // no-op
                }
            });
            builder.create().show();
            }
        });
    }

    private void loadView(int direction)
    {
        int newPosition = mCurrentPosition + direction;
        if((newPosition < mLocationData.size()) && (newPosition >= 0))
        {
            mCurrentPosition = newPosition;
            loadDataIntoView(mLocationData.get(mCurrentPosition));
        }

        ImageButton previous = (ImageButton)getView().findViewById(R.id.prev_location);
        ImageButton next = (ImageButton)getView().findViewById(R.id.next_location);


        if(mCurrentPosition == 0)
        {
            previous.setEnabled(false);
            previous.setVisibility(View.INVISIBLE);
            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
        }
        else if(mCurrentPosition == (mLocationData.size()-1))
        {
            previous.setEnabled(true);
            previous.setVisibility(View.VISIBLE);
            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
        }
        else
        {
            previous.setEnabled(true);
            previous.setVisibility(View.VISIBLE);
            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
        }
    }

    private void loadDataIntoView(WeatherData data)
    {
        ((TextView)getView().findViewById(R.id.currTemp)).setText(String.valueOf(data.main.temp) + "°");
        ((TextView)getView().findViewById(R.id.description)).setText(data.weather.main);
        ((TextView)getView().findViewById(R.id.descriptionDetail)).setText(data.weather.description);
        ((TextView)getView().findViewById(R.id.location_text)).setText(data.name);
    }

}
