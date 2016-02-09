package com.example.MyWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.FetchLocationsTask;
import com.example.MyWeather.webservice.NetworkTask;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeActivity extends Activity {

    private UsersAdapter mAdapter;
    private String mCurrLocationStr;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData[] data) {
            Log.d("DKM", "MAIN: Data received!");

            final ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(getApplicationContext(), R.layout.location_item, data);
            mLocationsList = (ListView)findViewById(R.id.locations_list);
            mLocationsList.setAdapter(adapter);
            mLocationsList.setVisibility(View.VISIBLE);

            mLocationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WeatherData data = adapter.getItem(position);

                    //Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                    //intent.putExtra("weatherData", );
                    //startActivity(intent);
                }
            });
        }
    }

    private OnDataListener mLocationDataListener;
    private ListView mLocationsList;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        /*Button getWeather = (Button)findViewById(R.id.get_weather);
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = ((EditText)findViewById(R.id.location)).getText().toString();
                //location.setText(mAdapter.getUserLocations("test"));

                if(location.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }

            }
        });*/

        mCurrLocationStr = "";

        final EditText location = (EditText)findViewById(R.id.location);

        mLocationDataListener = new OnDataListener();
        location.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && event.getAction() != KeyEvent.ACTION_DOWN)
                {
                    return false;
                }
                else if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || event == null
                    || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                {

                    //mWeatherDataListener = new OnDataListener();
                    FetchLocationsTask mFetchLocationsTask = new FetchLocationsTask(getApplicationContext(),
                            mLocationDataListener);
                    mFetchLocationsTask.execute(location.getText().toString());

                }

                return true;
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAdapter.close();
    }
}
