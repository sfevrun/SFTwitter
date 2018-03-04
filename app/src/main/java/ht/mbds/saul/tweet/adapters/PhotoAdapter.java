package ht.mbds.saul.tweet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.models.Tweet;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by SAUL on 3/4/2018.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    public void setClicklistener(OnItemClickListenerInterface clicklistener) {
        this.clicklistener = clicklistener;
    }

    private OnItemClickListenerInterface clicklistener;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imPhoto;


        public ViewHolder(View view){
            super(view);
            imPhoto=(ImageView) view.findViewById(R.id.imPhoto);
             view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clicklistener!=null){
                clicklistener.onClick(view,getAdapterPosition());
            }
        }
    }


    private List<Tweet> photos;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public PhotoAdapter(Context context, List<Tweet> _photos) {
        photos = _photos;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_photo, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Tweet photo = photos.get(position);
        ImageView imPhoto=viewHolder.imPhoto;
        String _thumballmedia=photo.getImTweet();
        if(!TextUtils.isEmpty(_thumballmedia)){
            Glide.with(getContext())
                    .load(_thumballmedia)
                   .bitmapTransform(new RoundedCornersTransformation(getContext(), 15, 10))
                    .into(imPhoto);
        }
    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        try {
            return photos.size();
        }catch (Exception e){
            return 0;
        }

    }
}