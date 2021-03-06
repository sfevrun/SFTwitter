package ht.mbds.saul.tweet.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;
import ht.mbds.saul.tweet.serviceApi.TwitterClient;

/**
 * Created by SAUL on 3/3/2018.
 */

public class UserTweetFragment extends TemelineFragment {
    ProgressDialog pd;
    public static final String TAG = "UserTimelineFragment";
    private TwitterClient client;
    private static final String SCREEN_NAME = "screen_name";

    private String screen_name;

    public static UserTweetFragment newInstance(String screenname) {
        UserTweetFragment fragment = new UserTweetFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SCREEN_NAME,screenname);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
       screen_name = getArguments().getString(SCREEN_NAME);
        Toast.makeText(getActivity(),"Li vide :"+ screen_name,Toast.LENGTH_LONG).show();
        populateUserTimeline();

    }


    private void populateUserTimeline() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        if(!isOnline()){
         //   Toast.makeText(mCtx,"Internet is not available", Toast.LENGTH_LONG).show();
          //  Log.i(TAG, " populateTimeline : Internet not available");
        //    Log.i(TAG, " populateTimeline : size "+tweetList.size());
            pd.dismiss();
        }

        else
        {
            client.getUserTimeline(0, screen_name,new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.i(TAG, response.toString());
                    addItems(response);
                    Log.d("DEBUG",response.toString());
                    pd.dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    Log.i(TAG, errorResponse.toString());
                    pd.dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.i(TAG, responseString);
                    throwable.printStackTrace();
                    pd.dismiss();
                }

                });
        }
    }


}
