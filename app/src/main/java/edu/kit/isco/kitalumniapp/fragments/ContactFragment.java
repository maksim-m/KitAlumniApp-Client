package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.ContactsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

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
        ContactsAdapter adapter = new ContactsAdapter(getActivity(), arrayOfUsers);

        adapter.add(new Contact("Muster Mann", "Standard Typ. Kennt jeder. Ansonsten kommt hier eine kurze Beschreibung rein. Bla Bla und so", "01234 56789", "muster@man.org"));

        // Attach the adapter to a ListView

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ListView listView = (ListView) view.findViewById(R.id.contactListView);
        //ListView listView = (ListView) this.getActivity().findViewById(R.id.contactListView);
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
}
