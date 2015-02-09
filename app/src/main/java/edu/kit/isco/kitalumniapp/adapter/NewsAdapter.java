package edu.kit.isco.kitalumniapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;

/**
 * Created by Max on 09.02.2015.
 */
public class NewsAdapter extends ArrayAdapter<DataAccessNews> {

    Context context;
    int layoutResId;

    public NewsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutResId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ( (Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResId, parent, false);

            holder = new NewsHolder();
            holder.newsImage = (ImageView) convertView.findViewById(R.id.newsImage);
            holder.newsTitle = (TextView) convertView.findViewById(R.id.newsCaption);
            holder.newsShortDescription = (TextView) convertView.findViewById(R.id.newsShortDescription);
        } else {
            holder = (NewsHolder) convertView.getTag();
        }

        // ...

        return convertView;
    }

    static class NewsHolder {
        ImageView newsImage;
        TextView newsTitle;
        TextView newsShortDescription;
    }
}
