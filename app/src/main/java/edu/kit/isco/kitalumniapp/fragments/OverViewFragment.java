package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import edu.kit.isco.kitalumniapp.OverviewListItem;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.OverviewAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {

    private OverviewAdapter adapter;
    private String header1 = "Latest News";
    private String header2 = "Next Event";
    private String header3 = "Newest Jobs";

    public OverViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //adapter = new OverviewAdapter(getActivity(), 0);
        //adapter.addItem(new OverviewListItem(header1, 3));
        //adapter.loadLatestNews();
        //adapter.addItem(new OverviewListItem(header2, 3));
        //adapter.loadLatestEvents();
        //adapter.addItem(new OverviewListItem(header3 ,3));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
        ListView listView = (ListView) view.findViewById(R.id.Overview);
        adapter = new OverviewAdapter(getActivity(), 0);
        adapter.loadLatestNews();
        adapter.loadLatestJobs();
        adapter.loadLatestEvents();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemType = adapter.getItemViewType(position);
                switch(itemType) {
                    case 0:
                        DataAccessNews news = (DataAccessNews) adapter.getItem(position);
                        Intent newsIntent = new Intent(view.getContext(), NewsDetailsVewActivity.class);
                        newsIntent.putExtra("fullText", news.getText());
                        newsIntent.putExtra("urlImage", news.getImageUrl());
                        newsIntent.putExtra("title", news.getTitle());
                        newsIntent.putExtra("date", news.getDate());
                        newsIntent.putExtra("url", news.getUrl());
                        startActivity(newsIntent);
                        break;
                    case 1:
                        DataAccessJob job = (DataAccessJob) adapter.getItem(position);
                        Intent jobIntent = new Intent(view.getContext(), JobsDetailsViewActivity.class);
                        jobIntent.putExtra("jobURL", job.getUrl());
                        jobIntent.putExtra("title", job.getTitle());
                        startActivity(jobIntent);
                        break;
                    case 2:
                        DataAccessEvent event = (DataAccessEvent) adapter.getItem(position);
                        Intent eventIntent = new Intent(view.getContext(), EventDetailsViewActivity.class);
                        eventIntent.putExtra("html", event.getAllText());
                        eventIntent.putExtra("title", event.getTitle());
                        startActivity(eventIntent);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
