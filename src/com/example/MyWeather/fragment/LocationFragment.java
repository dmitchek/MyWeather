package com.example.MyWeather.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyWeather.R;
import com.example.MyWeather.adapter.UsersAdapter;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.webservice.FetchLocationsTask;
import com.example.MyWeather.webservice.NetworkTask;

import com.example.MyWeather.fragment.WeatherFragment;

public class LocationFragment extends Fragment {

    private UsersAdapter mAdapter;
    private String mCurrLocationStr;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData[] data) {
            Log.d("DKM", "MAIN: Data received!");

            final ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(getActivity().getApplicationContext(), R.layout.location_item, data);
            mLocationsList = (ListView)getView().findViewById(R.id.locations_list);
            mLocationsList.setAdapter(adapter);
            mLocationsList.setVisibility(View.VISIBLE);

            mLocationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WeatherData data = adapter.getItem(position);

                    //Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                    //intent.putExtra("weatherData", );
                    //startActivity(intent);

                    Bundle args = new Bundle();
                    args.putSerializable("data", data);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();

                    WeatherFragment weatherFragment = new WeatherFragment();
                    weatherFragment.setArguments(args);
                    transaction.replace(R.id.content, weatherFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

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
    public void onStart() {
        super.onStart();

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

        final EditText location = (EditText)getView().findViewById(R.id.location);

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
                    FetchLocationsTask mFetchLocationsTask = new FetchLocationsTask(getActivity().getApplicationContext(),
                            mLocationDataListener);
                    mFetchLocationsTask.execute(location.getText().toString());

                }

                return true;
            }
        });
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
