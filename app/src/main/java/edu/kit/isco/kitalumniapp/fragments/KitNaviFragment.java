package edu.kit.isco.kitalumniapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.kit.isco.kitalumniapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KitNaviFragment extends Fragment {


    public KitNaviFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kit_navi, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.webViewCampus);
        webView.loadUrl("http://www.kit.edu/campusplan/map.php");
        webView.getSettings().setJavaScriptEnabled(true);
        return rootView;
    }
}
