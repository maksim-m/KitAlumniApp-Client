package edu.kit.isco.kitalumniapp.adapter;

/**
 * Adapter for a expandable Listview
 * Created by Yannick on 15.02.15 | KW 7.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kit.isco.kitalumniapp.KitAtAGlanceChildItem;
import edu.kit.isco.kitalumniapp.KitAtAGlanceParentItem;
import edu.kit.isco.kitalumniapp.R;

/**
 * Adapter that shows parents (contacts) with their children
 * (email, website and phone number of the contacts) in an expandable ListView.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<KitAtAGlanceParentItem> kitAtAGlanceContacts;
    private HashMap<KitAtAGlanceParentItem, ArrayList<KitAtAGlanceChildItem>> _listDataChild;

    /**
     * Constructor of the Adapter
     * @param context Current context
     * @param kitAtAGlanceContacts List with all the contacts wich will be displayed
     */
    public ExpandableListAdapter(Context context, ArrayList<KitAtAGlanceParentItem> kitAtAGlanceContacts) {
        this._context = context;
        this.kitAtAGlanceContacts = kitAtAGlanceContacts;
        _listDataChild = new HashMap<KitAtAGlanceParentItem, ArrayList<KitAtAGlanceChildItem>>();

        /**
         * In each contact you can find an email address, website url or phone number.
         * But it is possible that some contacts doesn't have any.
         * So we first proof if one of the information could be null.
         * If not, create a child object and add it to the parent/child hash-map.
         */
        for (KitAtAGlanceParentItem kitAtAGlanceContact : kitAtAGlanceContacts) {
            ArrayList<KitAtAGlanceChildItem> _children = new ArrayList<KitAtAGlanceChildItem>();
            if (kitAtAGlanceContact.getMailAddress() != null) {
                _children.add(new KitAtAGlanceChildItem(kitAtAGlanceContact.getMailAddress(), 0));
            }
            if (kitAtAGlanceContact.getWebsite() != null) {
                _children.add(new KitAtAGlanceChildItem(kitAtAGlanceContact.getWebsite(), 1));
            }
            if (kitAtAGlanceContact.getPhoneNumber() != null) {
                _children.add(new KitAtAGlanceChildItem(kitAtAGlanceContact.getPhoneNumber(), 2));
            }
            _listDataChild.put(kitAtAGlanceContact, _children);
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this.kitAtAGlanceContacts.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        //return the Id which the child object contains.
        return ((KitAtAGlanceChildItem) getChild(groupPosition, childPosition)).getId();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        KitAtAGlanceChildItem current = (KitAtAGlanceChildItem) getChild(groupPosition, childPosition);
        final String childText = current.getContent();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        ImageView imageListChild = (ImageView) convertView.findViewById(R.id.childImage);

        //Each child should have an icon which it belongs to. For example: A child with
        // a email-address stored should have a letter as icon.
        switch (current.getId()) {
            case 0:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_email));
                break;
            case 1:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_web));
                break;
            case 2:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_phone));
                break;
            default:
                imageListChild.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_info));
                break;
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.infotext);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this.kitAtAGlanceContacts.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.kitAtAGlanceContacts.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.kitAtAGlanceContacts.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = ((KitAtAGlanceParentItem) getGroup(groupPosition)).getName();
        String headerDescription = ((KitAtAGlanceParentItem) getGroup(groupPosition)).getShortDescription();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.contact_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.contactName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        TextView lblListerHeaderDes = (TextView) convertView
                .findViewById(R.id.contactShortDescription);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListerHeaderDes.setText(headerDescription);

        ImageView imageParent = (ImageView) convertView.findViewById(R.id.contactImage);
        imageParent.setImageResource(R.drawable.ic_person_outline);


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
