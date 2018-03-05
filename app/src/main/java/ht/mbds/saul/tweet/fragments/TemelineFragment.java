package ht.mbds.saul.tweet.fragments;

import android.content.Context;
import android.content.Intent;
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
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.Utils.ItemClickRecycle;
import ht.mbds.saul.tweet.activities.ProfileActivity;
import ht.mbds.saul.tweet.adapters.TweetAdapter;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.EndlessRecyclerViewScrollListener;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;

public class TemelineFragment extends Fragment  implements TweetAdapter.ProfileClickedListener {
    TweetAdapter adapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_liste,container,false);


        rvTweet=(RecyclerView)view.findViewById(R.id.rcTweet);
        tweets=new ArrayList<>();
        adapter=new TweetAdapter(getActivity(),tweets,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rvTweet.setLayoutManager(layoutManager);
        rvTweet.addItemDecoration( new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        //   adapter.setClicklistener(this);
        adapter.notifyDataSetChanged();
        rvTweet.setAdapter(adapter);


        ItemClickRecycle.addTo(rvTweet).setOnItemClickListener(new ItemClickRecycle.OnItemClickListener() {
                      @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //  Intent detail = new Intent(TimelineActivity.this,DetailTweetActivity.class);
                //  startActivity(detail);
                Tweet twt = tweets.get(position);
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("tweet", Parcels.wrap(twt));
                //  intent.putExtra("tweet", twt);
                startActivity(intent);
                //     Toast.makeText(getApplicationContext(),"You have selected pst3", Toast.LENGTH_LONG).show();


            }
        });

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

    @Override
    public void onProfileClicked(Tweet tweet) {

    }

    @Override
    public void onRetweetClicked(Tweet tweet, int position) {

    }

    @Override
    public void onReplyClicked(Tweet tweet) {

    }

    @Override
    public void onFavClicked(Tweet tweet, int position) {

    }

    @Override
    public void onUserNameClicked(String userName) {

    }
}
