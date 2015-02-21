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

import java.util.List;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;

/**
 * Created by Kristina on 11.2.2015 г..
 */
public class JobsAdapter extends ArrayAdapter<DataAccessJob> {

    private final String JOBS_SERVICE_URL;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResId;
    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessJob>> loading;

    public JobsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResId = resource;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        JOBS_SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url) + "jobs/";
    }
    static class JobsHolder {
        TextView jobsCaption;
        TextView jobsShortDescription;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobsHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_jobs, null);
            holder = new JobsHolder();
            holder.jobsCaption  = (TextView) convertView.findViewById(R.id.jobsCaption);
            holder.jobsShortDescription = (TextView) convertView.findViewById(R.id.jobsShortDescription);
            convertView.setTag(holder);
        }
        else{
            holder = (JobsHolder) convertView.getTag();
        }
        DataAccessJob jobs = getItem(position);
        holder.jobsCaption.setText(jobs.getTitle());

        holder.jobsShortDescription.setText(jobs.getShortInfo());

        // we're near the end of the list adapter, so load more items
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

        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        loading = Ion.with(getContext())
                .load(JOBS_SERVICE_URL + "latest/")
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
                        // add the jobs
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
