package edu.kit.isco.kitalumniapp.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.isco.kitalumniapp.OverviewListItem;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;

/**
 * Created by Andre on 22.02.2015.
 */
public class OverviewAdapter extends ArrayAdapter {

    ArrayList<OverviewListItem> objects = new ArrayList<OverviewListItem>();

    private static final int TYPE_NEWS = 0;
    private static final int TYPE_JOBS = 1;
    private static final int TYPE_EVENTS = 2;
    private static final int TYPE_HEADER = 3;
    private LayoutInflater mInflater;
    Future<List<DataAccessNews>> loadNews;
    Future<List<DataAccessJob>> loadJobs;
    Future<List<DataAccessEvent>> loadEvents;
    int resource;
    Context context;
    private final String SERVICE_URL;

    public OverviewAdapter (Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url);
    }

    public int getItemViewType(int position) {
        int type = -1;
        if (objects.get(position).getObject().getClass().equals(DataAccessNews.class)) {
            type = TYPE_NEWS;
        } else if (objects.get(position).getObject().getClass().equals(DataAccessJob.class)) {
            type = TYPE_JOBS;
        } else if (objects.get(position).getObject().getClass().equals(DataAccessEvent.class)) {
            type = TYPE_EVENTS;
        } else {
            type = TYPE_HEADER;
        }
        return type;
    }

    public void addItem(OverviewListItem item) {
        objects.add(item);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position).getObject();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int rowType = getItemViewType(position);

        if (convertView == null) {
            switch (rowType) {
                case TYPE_NEWS:
                    convertView = mInflater.inflate(R.layout.list_view_item_news, null);
                    ImageView image = (ImageView) convertView.findViewById(R.id.newsImage);
                    TextView textViewNews1 = (TextView) convertView.findViewById(R.id.newsCaption);
                    TextView textViewNews2 = (TextView) convertView.findViewById(R.id.newsShortDescription);
                    DataAccessNews news = (DataAccessNews) getItem(position);
                    Ion.with(image)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.default_news_image)
                            .load(news.getImageUrl());
                    textViewNews1.setText(news.getTitle());
                    textViewNews2.setText(news.getShortDescription());
                    break;
                case TYPE_EVENTS:
                    convertView = mInflater.inflate(R.layout.list_view_item_events, null);
                    TextView textViewEvents1 = (TextView) convertView.findViewById(R.id.eventTitle);
                    TextView textViewEvents2 = (TextView) convertView.findViewById(R.id.eventDate);
                    DataAccessEvent event = (DataAccessEvent) getItem(position);
                    textViewEvents1.setText(event.getTitle());
                    textViewEvents2.setText(DateFormat.format("dd.MM.yyyy hh:mm", Long.parseLong(event.getDate())).toString());
                    break;
                case TYPE_JOBS:
                    convertView = mInflater.inflate(R.layout.list_view_item_jobs, null);
                    TextView textViewJobs1 = (TextView) convertView.findViewById(R.id.jobsCaption);
                    TextView textViewJobs2 = (TextView) convertView.findViewById(R.id.jobsShortDescription);
                    DataAccessJob job = (DataAccessJob) getItem(position);
                    textViewJobs1.setText(job.getTitle());
                    textViewJobs2.setText(job.getShortInfo());
                    break;
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.overview_header, null);
                    TextView textViewHeader = (TextView) convertView.findViewById(R.id.section_header);
                    textViewHeader.setText((String) objects.get(position).getObject());
                    break;
                default:
                    break;
            }
        }
        return convertView;
    }

    public void loadLatestNews() {
        // don't attempt to load more if a load is already in progress
        if (loadNews != null && !loadNews.isDone() && !loadNews.isCancelled()) {
            return;
        }
        /**
         * Ion is a general purpose networking library,
         * that's a successor to UrlImageViewHelper.
         * This request loads a URL as JsonArray
         * and invokes a callback on completion.
         */
        loadNews = Ion.with(getContext())
                .load(SERVICE_URL + "news/latest/")
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
                        addItem(new OverviewListItem("Latest News", 3));
                        if (result != null) {
                            // add the news
                            Collections.reverse(result);
                            for (int i = 0; i < 3 && i < result.size(); i++) {
                                addItem(new OverviewListItem(result.get(i), TYPE_NEWS));
                            }
                            notifyDataSetChanged();
                        }

                    }
                });
    }

    public void loadLatestJobs() {
        // don't attempt to load more if a load is already in progress
        if (loadJobs != null && !loadJobs.isDone() && !loadJobs.isCancelled()) {
            return;
        }
        /**
         * Ion is a general purpose networking library,
         * that's a successor to UrlImageViewHelper.
         * This request loads a URL as JsonArray
         * and invokes a callback on completion.
         */
        loadJobs = Ion.with(getContext())
                .load(SERVICE_URL + "jobs/latest/")
                .as(new TypeToken<List<DataAccessJob>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessJob>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessJob> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getContext(), "Error loading jobs.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        addItem(new OverviewListItem("Newest Jobs", 3));
                        if (result != null) {
                            // add the jobs
                            for (int i = 0; i < 3 && i < result.size(); i++) {
                                addItem(new OverviewListItem(result.get(i), TYPE_JOBS));
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
    }

    public void loadLatestEvents() {
        // don't attempt to load more if a load is already in progress
        if (loadEvents != null && !loadEvents.isDone() && !loadEvents.isCancelled()) {
            return;
        }
        /**
         * Ion is a general purpose networking library,
         * that's a successor to UrlImageViewHelper.
         * This request loads a URL as JsonArray
         * and invokes a callback on completion.
         */
        loadEvents = Ion.with(getContext())
                .load(SERVICE_URL + "events/")
                .as(new TypeToken<List<DataAccessEvent>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessEvent>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessEvent> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getContext(), "Error loading events.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // add the event
                        addItem(new OverviewListItem("Next Event", 3));
                        if (result != null) {
                            addItem(new OverviewListItem(result.get(0), TYPE_EVENTS));
                        }
                        notifyDataSetChanged();
                    }
                });
    }

}
