package com.example.MyWeather.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.utils.FragmentUtil;
import com.example.MyWeather.webservice.FetchLocationsTask;
import com.example.MyWeather.webservice.NetworkTask;

import com.example.MyWeather.fragment.WeatherFragment;

public class LocationFragment extends Fragment {

    private UsersAdapter mAdapter;
    private String mCurrLocationStr;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onStart() {
        super.onStart();

        /*final Button login = (Button)getView().findViewById(R.id.action_button);
        login.setOnClickListener(new View.OnClickListener() {
            private boolean waitingForUser = false;
            @Override
            public void onClick(View v) {



            }
        });*/

        mCurrLocationStr = "";


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.welcome_screen, container, false);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAdapter.close();
    }
}
