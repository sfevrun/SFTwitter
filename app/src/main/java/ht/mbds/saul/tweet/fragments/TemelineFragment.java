package ht.mbds.saul.tweet.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.adapters.TweetAdapter;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.EndlessRecyclerViewScrollListener;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;

public class TemelineFragment extends Fragment {
    TweetAdapter adapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_liste,container,false);


        rvTweet=(RecyclerView)view.findViewById(R.id.rcTweet);
        tweets=new ArrayList<>();
        adapter=new TweetAdapter(getActivity(),tweets);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rvTweet.setLayoutManager(layoutManager);
        rvTweet.addItemDecoration( new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        //   adapter.setClicklistener(this);
        adapter.notifyDataSetChanged();
        rvTweet.setAdapter(adapter);


        return  view;
    }
    public void addItems(JSONArray response){
       // pd.dismiss();
        for (int i=0;i<response.length();i++){
            try{
                Tweet tw=Tweet.fromJSON(response.getJSONObject(i));
                tweets.add(tw);
                adapter.notifyItemInserted(tweets.size()-1);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
      //  swipeContainer.setRefreshing(false);
    }
    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}
