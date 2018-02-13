package com.example.nabee.myreddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.nabee.myreddit.model.Entry.Entry;
import com.example.nabee.myreddit.model.Entry.filterTags;
import com.example.nabee.myreddit.model.Feed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://www.reddit.com/r/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RssFeedApi feedApi = retrofit.create(RssFeedApi.class);
        Call<Feed> call = feedApi.getFeed();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: feed:" + response.body().getEntryList());
                Log.d(TAG, "onResponse: server response: "+ response.toString());
                List<Entry> entryList2 = response.body().getEntryList();
                //Log.d(TAG, "onResponse: entryList1 ");
                for(int i = 0; i<entryList2.size();i++){
                filterTags filterxml1 = new filterTags(entryList2.get(0).getContent(),"<a href=");
                    List<String> postContent = filterxml1.init();

                    filterTags filterxml2 = new filterTags(entryList2.get(0).getContent(),"<a href=");

                    try {
                        postContent.add(filterxml2.init().get(0));
                    }
                    catch ( NullPointerException e ){
                        postContent.add(null);
                        Log.e(TAG, "onResponse: NULL POINTER EXCEPTION THUMBNAIL"+e.getMessage() );
                    }
                    catch ( IndexOutOfBoundsException e ){
                        postContent.add(null);
                        Log.e(TAG, "onResponse: INDEX OUT OF BOUNDS EXCEPTION THUMBNAIL"+e.getMessage() );
                    }

                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: UNABLE TO RETREIVE RSS FEED" + t.getMessage() );
                Toast.makeText(MainActivity.this, "Could not retrieve feed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
