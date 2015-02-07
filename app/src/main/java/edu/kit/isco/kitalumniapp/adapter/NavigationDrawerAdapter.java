package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.NavigationDrawerItem;
import edu.kit.isco.kitalumniapp.R;

/**
 * Created by stelian on 30.01.2015.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavigationDrawerItem> navDrawerItems;

    public NavigationDrawerAdapter(Context context, ArrayList<NavigationDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;

    }


    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.navdrawer_textview, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.drawer_text);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        return convertView;
    }
}
