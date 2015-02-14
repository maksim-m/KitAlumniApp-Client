package edu.kit.isco.kitalumniapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.Contact;
import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.RoundedImageView;

/**
 * Created by Yannick on 09.02.2015.
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {
    public ContactsAdapter(Context context, ArrayList<Contact> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.contactName);
        TextView mail = (TextView) convertView.findViewById(R.id.contactMail);
        TextView phone = (TextView) convertView.findViewById(R.id.contactPhone);
        TextView shortDescription = (TextView) convertView.findViewById(R.id.contactShortDescription);
        ImageView image = (ImageView) convertView.findViewById(R.id.contactImage);

        Bitmap bm = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.ic_home);
        RoundedImageView roundedImage = new RoundedImageView(bm);

        // Populate the data into the template view using the data object
        name.setText(user.name);
        mail.setText(user.mailAddress);
        phone.setText(user.phoneNumber);
        shortDescription.setText(user.shortDescription);
        image.setImageDrawable(roundedImage);


        // Return the completed view to render on screen
        return convertView;
    }
}
