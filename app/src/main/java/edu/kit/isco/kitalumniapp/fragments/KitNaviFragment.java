package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.kit.isco.kitalumniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {KitNaviFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {KitNaviFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KitNaviFragment extends Fragment {


    public KitNaviFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_kit_navi, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.webViewCampus);
        webView.loadUrl("http://www.kit.edu/campusplan/map.php");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
