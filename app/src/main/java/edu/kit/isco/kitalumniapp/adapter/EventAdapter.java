package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessEvent;

/**
 * Created by Kristina on 10.2.2015 г..
 */
public class EventAdapter extends ArrayAdapter<DataAccessEvent> {

    Context context;
    int layoutEventResId;

    public EventAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutEventResId = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        EventHolder holder = null;

        if(convertView==null){
            LayoutInflater inflater=((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutEventResId, parent, false);

            holder = new EventHolder();
            holder.eventTitle = (TextView)convertView.findViewById(R.id.eventTitle);
            holder.eventShortDescription = (TextView)convertView.findViewById(R.id.eventShortDescription);
            }
        else{
            holder = (EventHolder) convertView.getTag();
        }

        // DB-Info


        return convertView;

    }
    static class EventHolder{
        TextView eventTitle;
        TextView eventShortDescription;
    }
}
