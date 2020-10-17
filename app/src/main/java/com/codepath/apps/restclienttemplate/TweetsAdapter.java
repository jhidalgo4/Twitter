package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;
    //pass in list of tweets

    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }


    //inflate the layout for the rows
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //bind values
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data at pos
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
        //bind the tweet at the view holder
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //Clean all elements inside the recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    //add list of items
    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }



    //defin viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody, tvScreenName, tvTime, tvHandle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTime = itemView.findViewById(R.id.tvTimeStamp);
            tvHandle = itemView.findViewById(R.id.tvHandleAt);
        }

        public void bind(Tweet tweet){
            tvBody.setText(tweet.body);
            tvHandle.setText("@"+tweet.user.screenName);
            tvTime.setText(tweet.getTimeStamp(tweet.createdAt) );
            tvScreenName.setText(tweet.user.name);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }
    }
}
