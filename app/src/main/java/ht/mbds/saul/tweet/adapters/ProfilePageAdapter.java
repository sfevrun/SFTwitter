package ht.mbds.saul.tweet.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ht.mbds.saul.tweet.fragments.UserFavoriteFragment;
import ht.mbds.saul.tweet.fragments.UserPhotoFragment;
import ht.mbds.saul.tweet.fragments.UserTweetFragment;

/**
 * Created by SAUL on 3/3/2018.
 */

public class ProfilePageAdapter  extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    String screen_name;
    public ProfilePageAdapter(FragmentManager fm, int NumOfTabs,String _screan_name) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.screen_name=_screan_name;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               UserTweetFragment tab1 = UserTweetFragment.newInstance(this.screen_name);// UserTweetFragment();
                return tab1;
            case 1:
                UserPhotoFragment tab2 = UserPhotoFragment.newInstance(this.screen_name);
                return tab2;
            case 2:
                UserFavoriteFragment tab3 = new UserFavoriteFragment();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}