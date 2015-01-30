package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
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
public class KitAtAGlanceFragment extends Fragment {

    public KitAtAGlanceFragment() {
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
        //return inflater.inflate(R.layout.fragment_kit_at_aglance, container, false);

        //Create Webview, which contain a path to GoogleDocs which open
        // the PdfUrl to the recomment document
        String myPdfUrl = "http://www.kit.edu/mediathek/print_forschung/Broschuere_KIT_Ueberblick_en.pdf";
        View rootView = inflater.inflate(R.layout.fragment_kit_navi, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.webViewCampus);
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + myPdfUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        return rootView;
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
