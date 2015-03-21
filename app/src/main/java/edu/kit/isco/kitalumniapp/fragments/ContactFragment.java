package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.contactListView);

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
                        //proof telephony-function before intent
                        if (!isTelephonyEnabled()) {
                            Toast.makeText(getActivity(), "No SIM-Card installed!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:" + txt));
                        startActivity(phoneIntent);
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
        contacts.add(new Contact(getResources().getString(R.string.isco_name), getResources().getString(R.string.isco_short_description), getResources().getString(R.string.isco_phone), getResources().getString(R.string.isco_mail), getResources().getString(R.string.isco_web)));
        contacts.add(new Contact(getResources().getString(R.string.ok_name), getResources().getString(R.string.ok_short_description), getResources().getString(R.string.ok_phone), getResources().getString(R.string.ok_mail), getResources().getString(R.string.ok_web)));
        contacts.add(new Contact(getResources().getString(R.string.tr_name), getResources().getString(R.string.tr_short_description), null, null, getResources().getString(R.string.tr_web)));
    }

    /**
     * Check if you can make phone calls with the device.
     * The device could be a tablet or has no SIM-Card installed.
     * @return true when there is telephony enabled
     */
    private boolean isTelephonyEnabled(){
        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getSimState()== TelephonyManager.SIM_STATE_READY;
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
