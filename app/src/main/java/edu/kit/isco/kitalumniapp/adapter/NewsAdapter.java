package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Collections;
import java.util.List;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;

/**
 * Created by Max on 09.02.2015.
 */
public class NewsAdapter extends ArrayAdapter<DataAccessNews> {

    private static final String TAG = "NewsAdapter";
    private final String REST_SERVICE_URL;
    private final String NEWS_SERVICE_URL;
    private Context context;
    private LayoutInflater layoutInflater;
    int layoutResId;
    // This "Future" tracks loadingOfLatest operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessNews>> loadingOfLatest;
    Future<List<DataAccessNews>> loadingOfPrevious;

    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResId = resource;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        REST_SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url);
        NEWS_SERVICE_URL = REST_SERVICE_URL + "news/";
    }



    static class NewsHolder {
        ImageView newsImage;
        TextView newsCaption;
        TextView newsShortDescription;
        TextView newsId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_news, null);
            holder = new NewsHolder();
            holder.newsImage = (ImageView) convertView.findViewById(R.id.newsImage);
            holder.newsCaption  = (TextView) convertView.findViewById(R.id.newsCaption);
            holder.newsShortDescription = (TextView) convertView.findViewById(R.id.newsShortDescription);
            holder.newsId = (TextView) convertView.findViewById(R.id.newsId);
            convertView.setTag(holder);
        } else {
            holder = (NewsHolder) convertView.getTag();
        }
        DataAccessNews news = getItem(position);

        holder.newsCaption.setText(news.getTitle());

        holder.newsShortDescription.setText(news.getShortDescription());

        holder.newsId.setText(Long.toString(news.getId()));

        Ion.with(holder.newsImage)
           .placeholder(R.drawable.placeholder)
           .error(R.drawable.default_news_image)
           //.crossfade(true)
           .load(news.getImageUrl());

        // we're near the end of the list adapter, so load more items
        if (position >= getCount() - 3) {
            loadPrevious(getItem(getCount() - 1).getId());
        }

        return convertView;
    }

    public void loadLatest() {
        // don't attempt to load more if a load is already in progress
        if (loadingOfLatest != null && !loadingOfLatest.isDone() && !loadingOfLatest.isCancelled()) {
            return;
        }

        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        loadingOfLatest = Ion.with(getContext())
                .load(NEWS_SERVICE_URL + "latest/")
                .as(new TypeToken<List<DataAccessNews>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessNews>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessNews> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getContext(), "Error loading news.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // add the news
                        Collections.reverse(result);
                        for (int i = 0; i < result.size(); i++) {
                            add(result.get(i));
                        }
                        notifyDataSetChanged();
                    }
                });
    }

    private void loadPrevious(long id) {
        // don't attempt to load more if a load is already in progress
        if (loadingOfPrevious != null && !loadingOfPrevious.isDone() && !loadingOfPrevious.isCancelled()) {
            return;
        }

        if (id <= 1) {
            return;
        }

        String url = NEWS_SERVICE_URL + "previous";
        url = url + "?id=" + id;
        url = url + "&count=" + 30;
        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        final String newsUrl = url;
        Log.d(TAG, "Loading more news from \"" + newsUrl + "\"...");
        loadingOfLatest = Ion.with(getContext())
                .load(newsUrl)
                .as(new TypeToken<List<DataAccessNews>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessNews>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessNews> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getContext(), "Error loading news.", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Error loading news from \"" + newsUrl + "\". Exception message: " + e.getLocalizedMessage());
                            return;
                        }
                        // add the news
                        if (result != null) {
                            Log.d(TAG, "Loaded " + result.size() + " more news from \"" + newsUrl + "\".");
                            Collections.reverse(result);
                            for (int i = 0; i < result.size(); i++) {
                                add(result.get(i));
                            }
                            notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Result list from \"" + newsUrl + "\" was null.");
                        }
                    }
                });
    }
}
