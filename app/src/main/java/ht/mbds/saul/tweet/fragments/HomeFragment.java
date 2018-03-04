package ht.mbds.saul.tweet.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ht.mbds.saul.tweet.adapters.TweetAdapter;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;
import ht.mbds.saul.tweet.serviceApi.TwitterClient;

/**
 * Created by SAUL on 3/3/2018.
 */

public class HomeFragment extends TemelineFragment{
    TwitterClient client;
     ProgressDialog pd;
  //  TemelineFragment timelineFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client= TwitterApp.getRestClient();
        populate(0);
    }
    public  void populate(int page){
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        client.getTimeLime(page,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                pd.dismiss();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", response.toString());
                addItems(response);
                pd.dismiss();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                pd.dismiss();
                Log.d("DEBUG", responseString);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
                pd.dismiss();
                // super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
