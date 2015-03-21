package edu.kit.isco.kitalumniapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;

/**
 * This class adapt the objetc from the KitAtAGlanceFragment to a List
 * Created by Yannick on 18.02.15 | KW 8.
 *
 */
public class KitAtAGlanceAdapter extends BaseAdapter {

    public static final int SHORT_INFORMATION = 0;
    public static final int DOWNLOAD = 1;

    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> information = new ArrayList<String>();
    private TreeSet<Integer> text_layout = new TreeSet<Integer>();
    private LayoutInflater mInflater;

    public KitAtAGlanceAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.name.size();
    }

    @Override
    public Object getItem(int position) {
        return this.name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //Contact user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        int rowType = getItemViewType(position);

        if (convertView == null) {

            //There are two types of items and they should displayed in different layouts.
            switch (rowType) {
                case SHORT_INFORMATION:
                    convertView = mInflater.inflate(R.layout.list_view_item_glance, null);
                    TextView title = (TextView) convertView.findViewById(R.id.glanceName);
                    TextView description = (TextView) convertView.findViewById(R.id.glanceDescription);
                    title.setText(name.get(position));
                    description.setText(information.get(position));
                    break;
                case DOWNLOAD:
                    convertView = mInflater.inflate(R.layout.list_view_item_pdf, null);
                    TextView title2 = (TextView) convertView.findViewById(R.id.pdfName);
                    TextView url = (TextView) convertView.findViewById(R.id.pdfShortDescription);
                    title2.setText(information.get(position));
                    url.setText(name.get(position));
                    break;
            }

        }
        return convertView;
    }


    /**
     * Add a PDF which should be shown in the list.
     * A PDF item also can be downloaded.
     * @param name Name of the PDF
     * @param url URL which point to the PDF
     */
    public void addPdf(String name, String url) {
        this.information.add(name);
        this.name.add(url);
    }

    /**
     *  Add a InfoText to the list which should be shown.
     *  A InfoText is just to show and not to be downloaded.
     * @param title The title of the item
     * @param information The information which will be displayed under the title
     */
    public void addInfoText(String title, String information) {
        this.information.add(information);
        this.name.add(title);
        this.text_layout.add(this.information.size() - 1);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return text_layout.contains(position) ? SHORT_INFORMATION : DOWNLOAD;
    }


}

