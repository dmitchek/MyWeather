package com.example.MyWeather.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.MyWeather.R;
import com.example.MyWeather.activity.MainActivity;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.utils.FragmentUtil;
import com.example.MyWeather.webservice.FetchWeatherTask;
import com.example.MyWeather.webservice.NetworkTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dave on 2/11/16.
 */
public class UserFragment extends DialogFragment {

    private MainActivity mActivity;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData [] data) {
            mActivity.setLocationData(new ArrayList<WeatherData>(Arrays.asList(data)));
            FragmentUtil.switchFragment(getActivity(), R.id.content, mActivity.mWeatherFragment);
        }
    }

    private OnDataListener mDataListener;

    public UserFragment()
    {
        setArguments(new Bundle());
    }

    @Override
    public void onStart()
    {
        super.onStart();

        mActivity = (MainActivity)getActivity();

        getView().findViewById(R.id.existing_user).setVisibility(View.GONE);
        EditText input = (EditText)getView().findViewById(R.id.input);
        input.setHint("user");
        final Button login = (Button)getView().findViewById(R.id.action);
        login.setText("sign in");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = ((EditText)getView().findViewById(R.id.input)).getText().toString();

                UsersAdapter usersAdapter = new UsersAdapter();
                usersAdapter.open(getActivity().getApplicationContext());

                String locations = usersAdapter.getUserLocations(user);
                if(locations != null && locations.length() > 0)
                {
                    getView().findViewById(R.id.input_content).setVisibility(View.GONE);
                    getView().findViewById(R.id.progress).setVisibility(View.VISIBLE);

                    mActivity.setCurrentUser(user);
                    mDataListener = new OnDataListener();
                    new FetchWeatherTask(getActivity().getApplicationContext(), mDataListener)
                            .execute(locations);
                }
                else
                {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "user not found", Toast.LENGTH_SHORT);
                    toast.show();
                }
                }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main, container, false);
    }
}
