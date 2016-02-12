package com.example.MyWeather.fragment;

import android.app.Fragment;
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
import com.example.MyWeather.activity.MainActivity;
import com.example.MyWeather.data.WeatherData;
import com.example.MyWeather.utils.FragmentUtil;
import com.example.MyWeather.webservice.FetchLocationsTask;
import com.example.MyWeather.webservice.NetworkTask;

/**
 * Created by dave on 2/11/16.
 */
public class FindLocationFragment extends Fragment {

    private MainActivity mActivity;

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData[] data) {

            if(data != null && data.length > 0) {
                final ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(getActivity().getApplicationContext(), R.layout.location_item, data);
                mLocationsList = (ListView) getView().findViewById(R.id.result_list);
                mLocationsList.setAdapter(adapter);
                mLocationsList.setVisibility(View.VISIBLE);
                getView().findViewById(R.id.action).setVisibility(View.INVISIBLE);

                mLocationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WeatherData data = adapter.getItem(position);

                        mActivity.addLocation(data);

                        FragmentUtil.switchFragment(getActivity(), R.id.content, mActivity.mWeatherFragment);
                    }
                });
            }
            else
            {
                Toast toast = Toast.makeText(mActivity, "location not found", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private OnDataListener mLocationDataListener;
    private ListView mLocationsList;

    public FindLocationFragment()
    {
        setArguments(new Bundle());
    }


    @Override
    public void onStart()
    {
        super.onStart();

        mActivity = (MainActivity)getActivity();

        getView().findViewById(R.id.existing_user).setVisibility(View.VISIBLE);
        final EditText location = (EditText)getView().findViewById(R.id.input);
        final Button login = (Button)getView().findViewById(R.id.existing_user);
        location.setHint("city");
        login.setText("existing user");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Fragment fragment = new UserFragment();
            FragmentUtil.switchFragment(mActivity, R.id.content, fragment);
            }
        });

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
                    searchLocation(location.getText().toString());
                }

                return true;
            }
        });

        Button search = (Button)getView().findViewById(R.id.action);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation(location.getText().toString());
            }
        });
    }

    private void searchLocation(String location)
    {
        if(location.length() > 0) {
            FetchLocationsTask mFetchLocationsTask = new FetchLocationsTask(mActivity.getApplicationContext(),
                    mLocationDataListener);
            mFetchLocationsTask.execute(location);
        }
        else
        {
            Toast toast = Toast.makeText(mActivity, "please enter a location", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main, container, false);
    }


}
