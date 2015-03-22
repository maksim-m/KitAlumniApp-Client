package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.Future;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.JobsAdapter;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessJob;

/**
 * This Class holds the List with all jobs,
 * using the Information from JobsAdapter.
 * A simple {@link Fragment} subclass.
 */
public class JobsListViewFragment extends Fragment {
    private JobsAdapter jobsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public JobsListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jobs_list_view, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.jobsSwipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                jobsAdapter.update();
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3500);
            }
        });
        // sets the colors used in the refresh animation
        swipeRefreshLayout.setColorSchemeResources(R.color.kit_cyan_blau, R.color.kit_darker_green);
        jobsAdapter = new JobsAdapter(getActivity(), 0);
        final ListView listView = (ListView) view.findViewById(R.id.jobsListView);
        listView.setAdapter(jobsAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (listView != null && listView.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataAccessJob job = jobsAdapter.getItem(position);
                Intent intent = new Intent(view.getContext(), JobsDetailsViewActivity.class);
                intent.putExtra("jobURL", job.getUrl());
                intent.putExtra("title", job.getTitle());
                startActivity(intent);
            }
        });
        jobsAdapter.update();
        return view;

    }


    //return inflater.inflate(R.layout.fragment_jobs_list_view, container, false);


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.destroyDrawingCache();
            swipeRefreshLayout.clearAnimation();
        }
    }
}
