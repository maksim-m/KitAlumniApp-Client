package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.KitAtAGlanceAdapter;

/**
 * This Class holds the List with one Informationtext and multiple PDFs,
 * using the Information from KitAtAGlanceAdapter.
 * A simple {@link Fragment} subclass.
 *
 */
public class KitAtAGlanceFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;
    private String directory_name;

    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

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
        // Create the adapter to convert the array to views
        KitAtAGlanceAdapter adapter = new KitAtAGlanceAdapter(getActivity());
        // Attach the adapter to a ListView
        View view = inflater.inflate(R.layout.fragment_kit_at_aglance, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.glanceView);
        //Fill adapter with content
        prepareData(adapter);

        directory_name = getResources().getString(R.string.directory_name);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                if (i != 0) {

                    TextView tv = (TextView)view.findViewById(R.id.pdfShortDescription);
                    String url = tv.getText().toString();
                    //String url = (String)listView.getItemAtPosition(i);
                    download(url, true, true);

                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View view, int i, long l) {
                if (i == 0) {
                    return false;
                }

                TextView tv = (TextView)view.findViewById(R.id.pdfShortDescription);
                String url = tv.getText().toString();

                Intent sendIntent = new Intent();
                sendIntent.setType("text/plain");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(sendIntent);

                /*
                //Use this code snippet if you want to send the complete pdf
                //instead of just sending the url.
                File pdf = download(url, false, false);
                if (pdf != null) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdf));
                    sendIntent.setType("application/pdf");
                    startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
                }
                */
                return true;
            }
        });

        listView.setAdapter(adapter);
        return view;
    }

    /**
     * Generate data and add it to the adapter.
      * @param adapter
     */
    private void prepareData(KitAtAGlanceAdapter adapter) {
        adapter.addInfoText("Karlsruhe Institute of Technology", (String)getResources().getText(R.string.glance_info_text));
        adapter.addPdf("KIT at a glance","http://www.kit.edu/mediathek/print_forschung/Broschuere_KIT_Ueberblick_en.pdf");
        adapter.addPdf("Facts and Figures", "http://www.kit.edu/mediathek/print_forschung/Flyer_KIT_en.pdf");
        adapter.addPdf("Portrait: Energy Center","http://www.kit.edu/mediathek/print_forschung/Broschuere_Energie_en.pdf");
        adapter.addPdf("Portrait: Climate and Environment Center","http://www.kit.edu/mediathek/print_forschung/Broschuere_Klima_Umwelt_en.pdf");
        adapter.addPdf("Portrait: Humans and Technology Focus","http://www.kit.edu/mediathek/print_forschung/Broschuere_Mensch_Technik_en.pdf");
        adapter.addPdf("Campus Alpine","http://www.kit.edu/mediathek/print_looKIT/Mit_Atmosphaerenforschern_des_KIT_an_der_Zugspitze.pdf");
        adapter.addPdf("KIT at CERN","http://www.kit.edu/mediathek/print_looKIT/Mit_KIT-Wissenschaftlern_auf_Teilchensuche_am_CERN.pdf");
        adapter.addPdf("Competence E – Association for Electromobility","http://www.kit.edu/mediathek/print_forschung/Flyer_Competence_E_en.pdf");

    }

    /**
     * Subclass to download a file in background.
     */
    private class DownloadFile extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = ProgressDialog.show(getActivity(), "Download","Downloading PDF...", true);
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(getResources().getString(R.string.download));
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            final int  MEGABYTE = 1024 * 1024;
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, directory_name);
            folder.mkdir();

            File pdfFile = new File(folder, fileName);
            int count = 0;

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            try {

                URL url = new URL(fileUrl);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
                int totalSize = urlConnection.getContentLength();

                byte[] buffer = new byte[MEGABYTE];
                int bufferLength = 0;
                long total = 0;
                while((bufferLength = inputStream.read(buffer))>0 ){
                    total += bufferLength;
                    publishProgress("" + (int) ((total * 100) / totalSize));
                    fileOutputStream.write(buffer, 0, bufferLength);
                }
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return fileName;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            pDialog.dismiss();
            /*
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            // Setting Dialog Title
            alertDialog.setTitle("PDF Download");
            // Setting Dialog Message
            alertDialog.setMessage("Download Complete");
            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog,
                                    final int which) {
                }
            });
            // Showing Alert Message
            alertDialog.show();
            */
            if (file_url != null) {
                view(file_url);
            }
        }
    }


    /**
     * Download PDF from param URL and save it in folder /directory_name/
     * If a PDF with the URL already exists then open it instead.
     * @param url the URL to the file which should be downloaded.
     * @param viewPDF true when the file should be displayed directly after downloading.
     * @param showProcess true when a dialog should show the process. Otherwise do in background.
     */
    public File download(String url, boolean viewPDF, boolean showProcess)
    {
        String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
        String fileName = URLUtil.guessFileName(url, null, fileExtenstion);
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory_name + "/" + fileName);
        if (pdfFile.exists() && viewPDF) {
            view(fileName);
        } else if (!pdfFile.exists() && isNetworkAvailable()) {
            new DownloadFile().execute(url, fileName);
        } else if (!isNetworkAvailable() && !pdfFile.exists()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_connecting), Toast.LENGTH_SHORT).show();
            pdfFile = null;
        }
        return pdfFile;
    }

    /**
     * Show PDF with fileName in installed PDF-Viewer
     * @param fileName Name of the PDF
     */
    public void view(String fileName)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory_name + "/" + fileName);  // -> filename = example.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(getActivity(), getResources().getString(R.string.no_application), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
