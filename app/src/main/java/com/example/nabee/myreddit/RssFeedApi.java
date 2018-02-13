package com.example.nabee.myreddit;

import com.example.nabee.myreddit.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nabee on 2/13/2018.
 */

public interface RssFeedApi {

    String BASE_URL = "https://www.reddit.com/r/";

    @GET("Showerthoughts/.rss")
    Call<Feed> getFeed();
}
