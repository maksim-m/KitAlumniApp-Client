package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        View rootView = inflater.inflate(R.layout.fragment_kit_at_aglance, container, false);
        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.myround);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.dunkelblau_m);
        //RoundedImageView roundedImage = new RoundedImageView(bm);
        imageView1.setImageBitmap(getCircleBitmap(bm));


        return rootView;

        //add a ProgressDialog. It will show up, when the webview load the pdf and hide,
        // when the pdf is finished loading
        /*
        final ProgressDialog pd = ProgressDialog.show(getActivity(),  "Loading", "Loading", true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //Create Webview, which contain a path to GoogleDocs which open
        // the PdfUrl to the recomment document
        String myPdfUrl = "http://www.kit.edu/mediathek/print_forschung/Broschuere_KIT_Ueberblick_en.pdf";


        View rootView = inflater.inflate(R.layout.fragment_kit_navi, container, false);

        WebView webView = (WebView) rootView.findViewById(R.id.webViewCampus);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + myPdfUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        return rootView;*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
