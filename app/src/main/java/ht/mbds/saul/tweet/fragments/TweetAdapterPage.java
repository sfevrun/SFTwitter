package ht.mbds.saul.tweet.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by SAUL on 3/3/2018.
 */

public class TweetAdapterPage   extends FragmentStatePagerAdapter {
    String tabTitle []=new String[]{"HOME","MENTIONS"};
    int mNumOfTabs;

    public TweetAdapterPage(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                MentionDataFragment tab2 = new MentionDataFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}