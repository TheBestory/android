package com.thebestory.android.adapter.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thebestory.android.R;
import com.thebestory.android.fragment.main.stories.RecentFragment;

public class StoriesFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLE_RES_ID = new int[]{
            R.string.main_stories_tab_recent,
            R.string.main_stories_tab_hot,
            R.string.main_stories_tab_top,
            R.string.main_stories_tab_random
    };

    Context mContext;

    public StoriesFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(TAB_TITLE_RES_ID[position]);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecentFragment.newInstance();
            case 1:
                return RecentFragment.newInstance();
            case 2:
                return RecentFragment.newInstance();
            case 3:
                return RecentFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TAB_TITLE_RES_ID.length;
    }
}