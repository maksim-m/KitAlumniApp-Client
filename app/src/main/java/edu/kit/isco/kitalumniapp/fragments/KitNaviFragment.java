package edu.kit.isco.kitalumniapp.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.kit.isco.kitalumniapp.R;


/**
 * This Class
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
        final ProgressDialog pd = ProgressDialog.show(getActivity(), "Loading", "Loading", true);
        View rootView = inflater.inflate(R.layout.fragment_kit_navi, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.webViewCampus);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });
        webView.loadUrl("http://www.kit.edu/campusplan/map.php");
        webView.getSettings().setJavaScriptEnabled(true);
        return rootView;
    }
}
