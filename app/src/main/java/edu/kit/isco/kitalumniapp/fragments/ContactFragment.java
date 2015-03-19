package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.ExpandableListAdapter;

/**
 * This Class holds the List with all Contacts,
 * using the Information from ExpandableListAdapter.
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactFragment extends Fragment {

    /**
     * List of contact-objects.
     *
     * @since 1.0
     */
    ArrayList<Contact> contacts;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Contact> arrayOfUsers = new ArrayList<Contact>();
        //create list with contacts
        contacts = new ArrayList<Contact>();
        //fill contacts-list with Data
        prepareData();

        // Create the adapter to convert the array to views
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(), contacts);

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        //ListView listView = (ListView) view.findViewById(R.id.contactListView);
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.contactListView);
        //ListView listView = (ListView) this.getActivity().findViewById(R.id.contactListView);

        //create a clickListener to click on the children in the List.
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String txt = ((TextView) v.findViewById(R.id.infotext)).getText().toString();

                //Each child have a different behavior. So when you click on a child which contains
                // a website url, you will be asked to go there with the browser you want.
                switch ((int) id) {
                    case 0:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", txt, null));
                        startActivity(Intent.createChooser(emailIntent, getActivity().getString(R.string.sendEmail)));
                        break;
                    case 1:
                        if (!txt.startsWith("http://") && !txt.startsWith("https://"))
                            txt = "http://" + txt;
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                        websiteIntent.setData(Uri.parse(txt));
                        startActivity(Intent.createChooser(websiteIntent, getActivity().getString(R.string.openSite)));
                        break;
                    case 2:
                        //boolean hasPhone = getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:" + txt));
                        try {
                            startActivity(phoneIntent);
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "This is not a phone!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        listView.setAdapter(adapter);
        return view;
    }

    /**
     * Generate data which will displayed in the fragment. They will be added directly
     * to the contact-list.
     */
    private void prepareData() {

        contacts.add(new Contact("International Scholars & Welcome Office (IScO)", "Dr. Petra Roth\n" +
                "Leiterin\n" +
                "Karlsruher Institut für Technologie (KIT)\n" +
                "Adenauerring 2\n" +
                "76131 Karlsruhe\n" +
                "\n" +
                "Campus Süd\n" +
                "Gebäude 50.20  ", "+49 721 608-44946", "researchalumni@intl.kit.edu", "http://www.intl.kit.edu/intl/isco.php"));
        contacts.add(new Contact("Oliver Kaas", "Referent für internationale Forschermobilität\n" +
                "Raum: Raum: 002, Gebäude 50.25, CS","+49 721 608 45323", "oliver.kaas@kit.edu", "https://www.intl.kit.edu/iforscher/3356_7179.php"));
        contacts.add(new Contact("Discover the Karlsruhe TechnologyRegion!", "", null, null, "http://welcome.technologieregion-karlsruhe.de/en/"));


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
