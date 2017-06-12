package com.example.monikamittal.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class fragmentPageAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    fragmentPageAdapter(FragmentManager fm,Context context)
    {
        super(fm);
        mContext=context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position)
        {
            case 0:return mContext.getString(R.string.CATEGORY_NUMBERS);
            case 1:return mContext.getString(R.string.CATEGORY_COLORS);
            case 2:return mContext.getString(R.string.CATEGORY_FAMILY);
            case 3:return mContext.getString(R.string.CATEGORY_PHRASES);
            default:return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:return new NumbersFragment();
            case 1:return new ColorsFragment();
            case 2:return new FamilyFragment();
            case 3:return new PhrasesFragment();
            default: return new NumbersFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
