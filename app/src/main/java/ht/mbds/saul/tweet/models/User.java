package ht.mbds.saul.tweet.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SAUL on 2/23/2018.
 */

public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    private  String tagLine;
    private int folowersCount;
    private int followingCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public User(){

    }

    public User(JSONObject jsonObject) throws JSONException {
        this.uid=jsonObject.getLong("id");
        this.name=jsonObject.getString("name");
        this.screenName=jsonObject.getString("screen_name");
        this.profileImageUrl=jsonObject.getString("profile_image_url");
        this.tagLine=jsonObject.getString("description");
        this.folowersCount=jsonObject.getInt("followers_count");
        this.followingCount=jsonObject.getInt("friends_count");
    }

    public static User fromJsonObject(JSONObject jsonObject) throws JSONException {
        User user=new User();
        user.uid=jsonObject.getLong("id");
        user.name=jsonObject.getString("name");
        user.screenName=jsonObject.getString("screen_name");
        user.profileImageUrl=jsonObject.getString("profile_image_url");
        user.tagLine=jsonObject.getString("description");
        user.folowersCount=jsonObject.getInt("followers_count");
        user.followingCount=jsonObject.getInt("friends_count");
        return user;
    }


    public static ArrayList<User> fromJSONArray(JSONArray array){
        ArrayList<User> results=new ArrayList<>();

        for(int i=0;i<array.length();i++){
            try{
                results.add(new User(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        return results;
    }
}
