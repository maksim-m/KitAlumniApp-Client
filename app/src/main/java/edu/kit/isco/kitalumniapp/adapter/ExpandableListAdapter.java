package edu.kit.isco.kitalumniapp.adapter;

/**
 * Created by Yannick on 15.02.15 | KW 7.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.Child;
import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    //private HashMap<String, List<String>> _listDataChild;
    private ArrayList<Contact> contacts;
    private HashMap<Contact, ArrayList<Child>> _listDataChild;

    private List<Contact> _listContact;

    public ExpandableListAdapter(Context context, ArrayList<Contact> contacts) {
        this._context = context;
        this.contacts = contacts;
        _listDataChild = new HashMap<Contact, ArrayList<Child>>();

        for (Contact contact: contacts) {
            ArrayList<Child> _children = new ArrayList<Child>();
            if (contact.getMailAddress() != null) {
                _children.add(new Child(contact.getMailAddress(), 0));
            }
            if (contact.getWebsite() != null) {
                _children.add(new Child(contact.getWebsite(), 1));
            }
            if (contact.getPhoneNumber() != null) {
                _children.add(new Child(contact.getPhoneNumber(), 2));
            }
            _listDataChild.put(contact, _children);
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this.contacts.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ((Child)getChild(groupPosition, childPosition)).getId();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Child current = (Child) getChild(groupPosition, childPosition);
        final String childText = current.getContent();



        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        ImageView imageListChild = (ImageView) convertView.findViewById(R.id.childImage);

        switch (current.getId()) {
            case 0:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_work));
                break;
            case 1:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_web));
                break;
            case 2:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_location));
                break;
            default:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_work));
                break;
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.infotext);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this.contacts.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.contacts.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.contacts.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = ((Contact) getGroup(groupPosition)).getName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.contact_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.contactName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
