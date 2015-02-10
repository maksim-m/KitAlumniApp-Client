package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.EventAdapter;
import edu.kit.isco.kitalumniapp.dbServices.DBHandlerClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListViewFragment extends Fragment {
    EventAdapter eventAdapter;
    DBHandlerClient dbHandler;

    public EventListViewFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_event_list_view, container, false);
        eventAdapter = new EventAdapter(rootView.getContext(), R.id.eventsListView);

        //dbHandler = new DBHandlerClient(rootView.getContext());
        //eventAdapter.addAll(dbHandler.getAllEvents());
        return rootView;
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
