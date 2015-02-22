package edu.kit.isco.kitalumniapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.settings.Tag;

/**
 * This Class sets the text in every checkedTextView.
 * Created by Kristina on 7.2.2015 г..
 */
public class CheckboxTagAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Tag> tagList;

    public CheckboxTagAdapter(Context context, ArrayList<Tag> tagList) {
        this.context = context;
        this.tagList = tagList;
    }

    @Override
    public int getCount() {
        return tagList.size();
    }

    @Override
    public Object getItem(int position) {
        return tagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * The View is inflated from the XML layout file. The parent View applies default
     * layout parameters.
     *
     * @param position:    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView: Converts the XML-view to display the correct data.
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater view = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = view.inflate(R.layout.tag_info, null);
        }

        final CheckedTextView checkedTextView = (CheckedTextView)
                convertView.findViewById(R.id.checkedTextViewTag);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedTextView.isChecked()) {
                    checkedTextView.setChecked(false);
                } else
                    checkedTextView.setChecked(true);
            }
        });
        checkedTextView.setText(tagList.get(position).getName());

        return convertView;
    }
}



