package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.kit.isco.kitalumniapp.Child;
import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.ContactsAdapter;
import edu.kit.isco.kitalumniapp.adapter.ExpandableListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public ContactFragment() {
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
        ArrayList<Contact> arrayOfUsers = new ArrayList<Contact>();
        // Create the adapter to convert the array to views
        //ContactsAdapter adapter = new ContactsAdapter(getActivity(), arrayOfUsers);
        prepareListData();
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);


        //adapter.add(new Contact("Muster Mann", "Standard Typ. Kennt jeder. Ansonsten kommt hier eine kurze Beschreibung rein. Bla Bla und so", "01234 56789", "muster@man.org"));

        // Attach the adapter to a ListView

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        //ListView listView = (ListView) view.findViewById(R.id.contactListView);
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.contactListView);
        //ListView listView = (ListView) this.getActivity().findViewById(R.id.contactListView);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Nothing here ever fires
                Toast.makeText(getActivity().getApplicationContext(), "pos" + childPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        listView.setAdapter(adapter);
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


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        ArrayList<Child> children = new ArrayList<Child>();

        // Adding child data
        listDataHeader.add("Max Mustermann");
        listDataHeader.add("Xana Musterfrau");
        listDataHeader.add("Hans Peter");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
