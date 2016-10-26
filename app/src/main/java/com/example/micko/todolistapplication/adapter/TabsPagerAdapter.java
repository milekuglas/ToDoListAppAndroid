package com.example.micko.todolistapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.micko.todolistapplication.fragment.PageFragment;

/**
 * Created by Micko on 23-Oct-16.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String value = null;
        if(position == 0)
        {
            value = "TO DO";
        }else
        {
            value = "DONE";
        }
        return value;
    }
}