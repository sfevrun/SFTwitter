package ht.mbds.saul.tweet.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.adapters.ProfilePageAdapter;
import ht.mbds.saul.tweet.fragments.TweetAdapterPage;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;
import ht.mbds.saul.tweet.serviceApi.TwitterClient;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class ProfileActivity extends AppCompatActivity {
    ProfilePageAdapter profilePageAdapter;
    private ImageView im_profil;

    private ImageView im_profile_user;
    String itemTitle;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    TextView title;
    TextView following;
    TextView idfolowers;
    TextView idscren_name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("TWEETS"));
        tabLayout.addTab(tabLayout.newTab().setText("PHOTOS"));
        tabLayout.addTab(tabLayout.newTab().setText("FAVORITES"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);

        profilePageAdapter = new ProfilePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(profilePageAdapter);

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




     //   ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    //    String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);


    //    final ImageView image = (ImageView) findViewById(R.id.image);
         title = (TextView) findViewById(R.id.title);
        idscren_name = (TextView) findViewById(R.id.idscren_name);


        idfolowers = (TextView) findViewById(R.id.idfolowers);


        following = (TextView) findViewById(R.id.following);


        im_profil=(ImageView) findViewById(R.id.imProfile);
        im_profile_user=(ImageView) findViewById(R.id.im_profile_user);
        TwitterClient client = TwitterApp.getRestClient();

        Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        if(tweet!=null){
            Log.d("DEBUG", "user: " + tweet.getScreenName().toString());
            title.setText(tweet.getName().toString());
          idfolowers.setText(""+tweet.getFriends_count());
           following.setText(""+tweet.getFollowers_count());
            idscren_name.setText(String.format(Locale.US, "@%s", tweet.getScreenName()));
        Glide.with(getContext())
                    .load(tweet.getProfile_background_image_url())
                    //   .override(100, 100)
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 10))

                    .into(im_profil);

            Glide.with(getContext())
                    .load(tweet.getProfileImageUrl())
                    //   .override(100, 100)
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 10))
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(im_profile_user);
            im_profile_user.bringToFront();
//            Toast.makeText(this,""+tweet.getScreenName().toString(),Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Li vide",Toast.LENGTH_LONG).show();
            client.getAuthUser(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG", "user: " + response.toString());

                    try {
                        title.setText(response.getString("name").toString());
                        idfolowers.setText(response.getString("friends_count").toString() + " Following");
                        following.setText(response.getString("followers_count").toString() + " Followers");
                        idscren_name.setText(String.format(Locale.US, "@%s", response.getString("screen_name")));
                        Glide.with(getContext())
                                .load(response.getString("profile_background_image_url"))
                                //   .override(100, 100)
                                .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 10))

                                .into(im_profil);

                        Glide.with(getContext())
                                .load(response.getString("profile_image_url_https"))
                                //   .override(100, 100)
                                .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 10))
                                .bitmapTransform(new CropCircleTransformation(getContext()))
                                .into(im_profile_user);
                        im_profile_user.bringToFront();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
//       ("Fevrun");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Fevrun");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));




    }
}
