package edu.kit.isco.kitalumniapp.fragments;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import edu.kit.isco.kitalumniapp.R;

public class NewsDetailsVewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_vew);
        /*TextView titleTextView = (TextView) findViewById(R.id.newsTitleTextView);
        titleTextView.setText(getIntent().getStringExtra("title"));
        ImageView newsImage = (ImageView) findViewById(R.id.newsImageView);
        TextView fullTextView = (TextView) findViewById(R.id.newsFullTextView);
        fullTextView.setText(getIntent().getStringExtra("fullText"));
        TextView dateTextView = (TextView) findViewById(R.id.newsDateTextView);
        dateTextView.setText(getIntent().getStringExtra("date"));


        Ion.with(newsImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.default_news_image)
                .load(getIntent().getStringExtra("urlImage"));*/

        WebView webView = (WebView) findViewById(R.id.webViewNewsDetails);
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.getSettings().setJavaScriptEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_details_vew, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
