package com.example.MyWeather.fragment;

import android.app.DialogFragment;
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

    private class OnDataListener implements NetworkTask.OnDataListener
    {
        @Override
        public void populate(WeatherData[] data) {
            Log.d("DKM", "MAIN: Data received!");

            final ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(getActivity().getApplicationContext(), R.layout.location_item, data);
            mLocationsList = (ListView)getView().findViewById(R.id.result_list);
            mLocationsList.setAdapter(adapter);
            mLocationsList.setVisibility(View.VISIBLE);

            mLocationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WeatherData data = adapter.getItem(position);

                    Bundle args;

                    MainActivity activity = (MainActivity)getActivity();
                    args = activity.mLocationFragment.getArguments();

                    if(args == null)
                        args = new Bundle();

                    args.putSerializable("location", data);

                    //WeatherFragment weatherFragment = new WeatherFragment();
                    //weatherFragment.setArguments(args);

                    FragmentUtil.switchFragment(getActivity(), activity.mLocationFragment, R.id.content, activity.mWeatherFragment, args);
                }
            });
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

        final EditText location = (EditText)getView().findViewById(R.id.input);
        final Button login = (Button)getView().findViewById(R.id.action);
        location.setHint("city");
        login.setText("existing user");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new UserFragment();
                FragmentUtil.switchFragment(getActivity(), ((MainActivity)getActivity()).mLocationFragment, R.id.content, fragment);
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
        return inflater.inflate(R.layout.main, container, false);
    }


}
