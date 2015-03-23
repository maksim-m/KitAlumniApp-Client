package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;

/**
 * This Class ist connecting the Caption and
 * the short description from each Job.
 * Created by Kristina on 11.2.2015 .
 */
public class JobsAdapter extends ArrayAdapter<DataAccessJob> {

    private static final String TAG = "JobAdapter";
    private final String JOBS_SERVICE_URL;
    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    Future<List<DataAccessJob>> loading;
    Future<List<DataAccessJob>> loadingOfPrevious;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResId;

    /**
     * @param context
     * @param resource
     */
    public JobsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResId = resource;
        //this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        JOBS_SERVICE_URL = context.getResources().getString(R.string.rest_service_base_url) + "jobs/";
        ArrayList<DataAccessJob> jobsFromDb = (ArrayList<DataAccessJob>) new DBHandlerClient(context).getAllJobs();
        Collections.reverse(jobsFromDb);
        for (DataAccessJob j : jobsFromDb) {
            add(j);
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobsHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_item_jobs, null);
            holder = new JobsHolder();
            holder.jobsCaption = (TextView) convertView.findViewById(R.id.jobsCaption);
            holder.jobsShortDescription = (TextView) convertView.findViewById(R.id.jobsShortDescription);
            convertView.setTag(holder);
        } else {
            holder = (JobsHolder) convertView.getTag();
        }
        DataAccessJob jobs = getItem(position);
        holder.jobsCaption.setText(jobs.getTitle());

        holder.jobsShortDescription.setText(jobs.getShortInfo());

        // we're near the end of the list adapter, so load more items
        if (position >= getCount() - 1) {
            loadPrevious(getItem(getCount() - 1).getId());
        }

        return convertView;
    }

    private void loadLatest() {
        loadLatest(-1);
    }

    private void loadLatest(long id) {
        // don't attempt to load more if a load is already in progress
        if (loading != null && !loading.isDone() && !loading.isCancelled()) {
            return;
        }

        String url = JOBS_SERVICE_URL + "latest/";
        url = url + "?id=" + id;
        final String jobsUrl = url;

        /**
         * Ion is a general purpose networking library,
         * that's a successor to UrlImageViewHelper.
         * This request loads a URL as JsonArray
         * and invokes a callback on completion.
         */
        loading = Ion.with(getContext())
                .load(jobsUrl)
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

                        if (result == null) {
                            return;
                        }
                        // add the jobs
                        Collections.reverse(result);
                        for (int i = 0; i < result.size(); i++) {
                            insert(result.get(i), 0);
                        }
                        notifyDataSetChanged();
                        new Runnable() {
                            @Override
                            public void run() {
                                new DBHandlerClient(context).updateJobs(getItems());
                            }
                        }.run();
                    }
                });
    }

    private ArrayList<DataAccessJob> getItems() {
        ArrayList<DataAccessJob> result = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            result.add(getItem(i));
        }
        return result;
    }

    public void update() {
        if (getCount() == 0) {
            loadLatest();
        } else {
            loadLatest(getItem(0).getId());
        }
    }

    private void loadPrevious(long id) {
        // don't attempt to load more if a load is already in progress
        if (loadingOfPrevious != null && !loadingOfPrevious.isDone() && !loadingOfPrevious.isCancelled()) {
            return;
        }

        if (id <= 1) {
            return;
        }

        String url = JOBS_SERVICE_URL + "previous";
        url = url + "?id=" + id;
        url = url + "&count=" + 30;
        // This request loads a URL as JsonArray and invokes
        // a callback on completion.
        final String jobsUrl = url;
        Log.d(TAG, "Loading more jobs from \"" + jobsUrl + "\"...");
        loadingOfPrevious = Ion.with(getContext())
                .load(jobsUrl)
                .as(new TypeToken<List<DataAccessJob>>() {
                })
                .setCallback(new FutureCallback<List<DataAccessJob>>() {
                    @Override
                    public void onCompleted(Exception e, List<DataAccessJob> result) {
                        // this is called back onto the ui thread, no Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            //Toast.makeText(getContext(), "Error loading jobs.", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Error loading jobs from \"" + jobsUrl + "\". Exception message: " + e.getLocalizedMessage());
                            return;
                        }
                        // add the news
                        if (result != null) {
                            Log.d(TAG, "Loaded " + result.size() + " more jobs from \"" + jobsUrl + "\".");
                            Collections.reverse(result);
                            for (int i = 0; i < result.size(); i++) {
                                add(result.get(i));
                            }
                            notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Result list from \"" + jobsUrl + "\" was null.");
                        }
                    }
                });
    }

    /**
     * The JobsHolder implementation allows to avoid
     * the findViewById() method in the JobsAdapter.
     */
    static class JobsHolder {
        TextView jobsCaption;
        TextView jobsShortDescription;
    }


}
