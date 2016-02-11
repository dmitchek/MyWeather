package com.example.MyWeather.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import com.example.MyWeather.R;
import com.example.MyWeather.fragment.WeatherFragment;

/**
 * Created by dave on 2/10/16.
 */
public class FragmentUtil {
    public static void switchFragment(Activity activity, Fragment curFragment, int layoutId, Fragment fragment, Bundle args)
    {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle fragmentArguments = fragment.getArguments();

        if(args != null)
            fragmentArguments.putAll(args);

        if(curFragment != null)
            transaction.hide(curFragment);

        if(fragment.isAdded())
        {
            transaction.show(fragment);
        }
        else {
            transaction.add(layoutId, fragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void switchFragment(Activity activity, Fragment curFragment, int layoutId, Fragment fragment)
    {
        switchFragment(activity, curFragment, layoutId, fragment, null);
    }

}
