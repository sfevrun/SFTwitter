package ht.mbds.saul.tweet.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;


//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.fragments.TemelineFragment;
import ht.mbds.saul.tweet.fragments.TweetAdapterPage;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.EndlessRecyclerViewScrollListener;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;
import ht.mbds.saul.tweet.serviceApi.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements MessageDialogFragment.ComposerTweet {
    TweetAdapterPage tweetAdapterPage;
    private SwipeRefreshLayout swipeContainer;
    TemelineFragment timelineFragment;
    private EndlessRecyclerViewScrollListener scrollListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeline);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("MENTION"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);

        tweetAdapterPage = new TweetAdapterPage(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(tweetAdapterPage);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline,menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //  openDialog();
      //  showEditDialog();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MessageDialogFragment dialogFragment = MessageDialogFragment.newInstance("Some Title");
        dialogFragment.show(fm, "fragment_message_dialoge");
    }
    @Override
    public void onSubmitTweet(final String tweet) {
        TwitterClient client = TwitterApp.getRestClient();
        Toast.makeText(this,tweet.toString(),Toast.LENGTH_LONG).show();
        client.postTweet(tweet.toString(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", "tweet: " + response.toString());
                try {
                    final Tweet mTweet = new Tweet(response);
                    TimelineActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", "tweet: " + errorResponse.toString());
            //    super.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });
    }


    public void onProfileV(MenuItem item) {
        Intent i=new Intent(this,ProfileActivity.class);
        startActivity(i);
    }
}
