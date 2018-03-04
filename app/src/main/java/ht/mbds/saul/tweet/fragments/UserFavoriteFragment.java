package ht.mbds.saul.tweet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ht.mbds.saul.tweet.serviceApi.TwitterApp;

/**
 * Created by SAUL on 3/3/2018.
 */

public class UserFavoriteFragment extends FavoriteFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   client= TwitterApp.getRestClient();
        //  populate(0);
    }
}
