package ht.mbds.saul.tweet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ht.mbds.saul.tweet.R;

/**
 * Created by SAUL on 2/23/2018.
 */

public class ViewHolderNoImage extends RecyclerView.ViewHolder{
    //  ImageView imageView;
    TextView tvBody;
    TextView tvTitle;
    ImageView imProfile;
  //  TextView tvScreen_name;
    TextView tvTime;

    TextView  reply_count_text;
    TextView  retweet_count_text;
    TextView  like_count_text;

    public TextView getReply_count_text() {
        return reply_count_text;
    }

    public void setReply_count_text(TextView reply_count_text) {
        this.reply_count_text = reply_count_text;
    }

    public TextView getRetweet_count_text() {
        return retweet_count_text;
    }

    public void setRetweet_count_text(TextView retweet_count_text) {
        this.retweet_count_text = retweet_count_text;
    }

    public TextView getLike_count_text() {
        return like_count_text;
    }

    public void setLike_count_text(TextView like_count_text) {
        this.like_count_text = like_count_text;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView getTvBody() {
        return tvBody;
    }

    public void setTvBody(TextView tvBody) {
        this.tvBody = tvBody;
    }

    public ImageView getImProfile() {
        return imProfile;
    }

    public void setImProfile(ImageView imProfile) {
        this.imProfile = imProfile;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ViewHolderNoImage(View view) {
        super(view);
        imProfile=(ImageView) view.findViewById(R.id.imProfile);
        tvTitle=(TextView) view.findViewById(R.id.tvTitle);
        tvBody=(TextView) view.findViewById(R.id.tvBody);

        tvTime=(TextView) view.findViewById(R.id.tvTime);
     //   tvScreen_name=(TextView) view.findViewById(R.id.tvScreen_name);

        reply_count_text=(TextView) view.findViewById(R.id.reply_count_text);
        retweet_count_text=(TextView) view.findViewById(R.id.retweet_count_text);

        like_count_text=(TextView) view.findViewById(R.id.like_count_text);


    }
}
