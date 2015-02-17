package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.dbObjects.DataAccessNews;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailsViewFragment extends Fragment {

    public NewsDetailsViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newsView = inflater.inflate(R.layout.fragment_news_details_view, container, false);
        TextView titleTextView = (TextView) newsView.findViewById(R.id.newsTitleTextView);
        titleTextView.setText(getArguments().getString("title"));
        ImageView newsImage = (ImageView) newsView.findViewById(R.id.newsImageView);
        TextView fullTextView = (TextView) newsView.findViewById(R.id.newsFullTextView);
        fullTextView.setText(getArguments().getString("fullText"));
        TextView dateTextView = (TextView) newsView.findViewById(R.id.newsDateTextView);
        dateTextView.setText(getArguments().getString("date"));

        Ion.with(newsImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.default_news_image)
                .load(getArguments().getString("urlImage"));

        return newsView;
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
