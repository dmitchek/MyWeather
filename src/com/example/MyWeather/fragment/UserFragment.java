package com.example.MyWeather.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
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

/**
 * Created by dave on 2/11/16.
 */
public class UserFragment extends DialogFragment {

    public UserFragment()
    {
        setArguments(new Bundle());
    }

    @Override
    public void onStart()
    {
        super.onStart();

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

                    String [] locations = usersAdapter.getUserLocations(user);
                    if(locations != null && locations.length > 0)
                    {
                        Bundle args = new Bundle();
                        args.putStringArray("userLocations", locations);
                        Fragment fragment = new WeatherFragment();
                        fragment.setArguments(args);

                        FragmentUtil.switchFragment(getActivity(), ((MainActivity)getActivity()).mUserFragment, R.id.content, fragment, args);

                        dismiss();
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
