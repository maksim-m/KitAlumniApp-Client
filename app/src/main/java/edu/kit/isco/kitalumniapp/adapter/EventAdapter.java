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
import java.util.List;
import edu.kit.isco.kitalumniapp.R;


import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;


/**
 * Created by Kristina on 10.2.2015 г..
 */
public class EventAdapter extends ArrayAdapter<DataAccessEvent> {
    // String with URL that lead to the database, where
    // ist the  saved information about the events.
    private final String EVENT_SERVICE_URL;


    Context context;
    private LayoutInflater layoutInflater;
    int layoutEventResId;
    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessEvent>> loading;


    public EventAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutEventResId = resource;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        EVENT_SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url) + "events/";
    }
    static class EventsHolder {
        TextView eventTitle;
        TextView eventDate;}


        public View getView(int position, View convertView, ViewGroup parent) {
            EventsHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_events, null);
            holder = new EventsHolder();

            holder.eventTitle  = (TextView) convertView.findViewById(R.id.eventTitle);
            holder.eventDate = (TextView) convertView.findViewById(R.id.eventDate);
            convertView.setTag(holder);
        }
         else {
            holder = (EventsHolder) convertView.getTag();
        }

        DataAccessEvent event = getItem(position);
            holder.eventTitle.setText(event.getTitle());

            holder.eventDate.setText(event.getDate().toString());

            TextView eventTitle = (TextView) convertView.findViewById(R.id.eventTitle);
        eventTitle.setText(event.getTitle());
        TextView eventShortDescription = (TextView) convertView.findViewById(R.id.eventDate);
        eventShortDescription.setText(event.getShort_info());

        // we're near the end of the list adapter, so load more items
        if (position >= getCount() - 3) {
            //loadPrevious();
        }
        return convertView;
    }
    public void loadLatest() {
        // don't attempt to load more if a load is already in progress
        if (loading != null && !loading.isDone() && !loading.isCancelled()) {
            return;
        }

        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        loading = Ion.with(getContext())
                .load(EVENT_SERVICE_URL)
                .as(new TypeToken<List<DataAccessEvent>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessEvent>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessEvent> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        // add the events
                        for (int i = 0; i < result.size(); i++) {
                            add(result.get(i));
                        }
                        notifyDataSetChanged();
                    }
                });
    }
    private void loadPrevious() {

    }

}
