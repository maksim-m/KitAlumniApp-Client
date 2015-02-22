package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
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
 * This Class ist connecting the Caption, the Image and
 * the short description from each News.
 * Created by Max on 09.02.2015.
 */
public class NewsAdapter extends ArrayAdapter<DataAccessNews> {

    private final String SERVICE_URL;
    int layoutResId;
    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessNews>> loading;
    private Context context;
    private LayoutInflater layoutInflater;

    /**
     * @param context
     * @param resource
     */
    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResId = resource;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url) + "news/";
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * The View is inflated from the XML layout file. The parent View applies default
     * layout parameters.
     *
     * @param position:    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView: Converts the XML-view to display the correct data.
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_news, null);
            holder = new NewsHolder();
            holder.newsImage = (ImageView) convertView.findViewById(R.id.newsImage);
            holder.newsCaption = (TextView) convertView.findViewById(R.id.newsCaption);
            holder.newsShortDescription = (TextView) convertView.findViewById(R.id.newsShortDescription);
            convertView.setTag(holder);
        } else {
            holder = (NewsHolder) convertView.getTag();
        }
        DataAccessNews news = getItem(position);
        holder.newsCaption.setText(news.getTitle());
        holder.newsShortDescription.setText(news.getShortDescription());

        //Putting an image into an ImageView.
        Ion.with(holder.newsImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.default_news_image)
                .load(news.getImageUrl());
        // We're near the end of the list adapter, so load more items.
        if (position >= getCount() - 3) {
            loadPrevious();
        }
        return convertView;
    }

    public void loadLatest() {
        // don't attempt to load more if a load is already in progress
        if (loading != null && !loading.isDone() && !loading.isCancelled()) {
            return;
        }
        /**
         * Ion is a general purpose networking library,
         * that's a successor to UrlImageViewHelper.
         * This request loads a URL as JsonArray
         * and invokes a callback on completion.
         */
        loading = Ion.with(getContext())
                .load(SERVICE_URL + "latest/")
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

    private void loadPrevious() {

    }

    /**
     * The NewsHolder implementation allows to avoid
     * the findViewById() method in the NewsAdapter.
     */
    static class NewsHolder {
        ImageView newsImage;
        TextView newsCaption;
        TextView newsShortDescription;
    }
}
