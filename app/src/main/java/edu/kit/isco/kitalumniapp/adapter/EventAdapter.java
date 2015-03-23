package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;


/**
 * This Class ist connecting the title and
 * the date from each Event.
 * Created by Kristina on 10.2.2015.
 */
public class EventAdapter extends ArrayAdapter<DataAccessEvent> {
    // String with URL that lead to the database, where
    // ist the  saved information about the events.
    private final String EVENT_SERVICE_URL;


    Context context;
    int layoutEventResId;
    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessEvent>> loading;
    private LayoutInflater layoutInflater;


    public EventAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutEventResId = resource;
        //this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EVENT_SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url) + "events/";
        ArrayList<DataAccessEvent> eventsFromDb = (ArrayList<DataAccessEvent>) new DBHandlerClient(context).getAllEvents();
        Collections.reverse(eventsFromDb);
        for (DataAccessEvent e : eventsFromDb) {
            add(e);
        }
        notifyDataSetChanged();
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

    public View getView(int position, View convertView, ViewGroup parent) {
        EventsHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_events, null);
            holder = new EventsHolder();

            holder.eventTitle = (TextView) convertView.findViewById(R.id.eventTitle);
            holder.eventDate = (TextView) convertView.findViewById(R.id.eventDate);
            convertView.setTag(holder);
        } else {
            holder = (EventsHolder) convertView.getTag();
        }

        DataAccessEvent event = getItem(position);
        holder.eventTitle.setText(event.getTitle());

        holder.eventDate.setText(event.getShortInfo());

        TextView eventTitle = (TextView) convertView.findViewById(R.id.eventTitle);
        eventTitle.setText(event.getTitle());
        TextView eventShortDescription = (TextView) convertView.findViewById(R.id.eventDate);
        eventShortDescription.setText(event.getShortInfo());
        return convertView;
    }

    private ArrayList<DataAccessEvent> getItems() {
        ArrayList<DataAccessEvent> result = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            result.add(getItem(i));
        }
        return result;
    }

    public void update() {
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
                .load(EVENT_SERVICE_URL)
                .as(new TypeToken<List<DataAccessEvent>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessEvent>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessEvent> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (result == null)  {
                            return;
                        }
                        clear();
                        Collections.reverse(result);
                        // add the events
                        for (int i = 0; i < result.size(); i++) {
                            add(result.get(i));
                        }
                        notifyDataSetChanged();
                        new Runnable() {
                            @Override
                            public void run() {
                                new DBHandlerClient(context).updateEvents(getItems());
                            }
                        }.run();
                    }
                });
    }

    /**
     * The EventsHolder implementation allows to avoid
     * the findViewById() method in the EventsAdapter.
     */
    static class EventsHolder {
        TextView eventTitle;
        TextView eventDate;
    }

}
