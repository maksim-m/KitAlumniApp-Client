package edu.kit.isco.kitalumniapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.util.Objects;

import edu.kit.isco.kitalumniapp.R;
import edu.kit.isco.kitalumniapp.adapter.KitAtAGlanceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KitAtAGlanceFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;

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
        ArrayList<String> arrayOfPdfs = new ArrayList<String>();
        // Create the adapter to convert the array to views
        KitAtAGlanceAdapter adapter = new KitAtAGlanceAdapter(getActivity());

        //adapter.add(new Contact("Muster Mann", "Standard Typ. Kennt jeder. Ansonsten kommt hier eine kurze Beschreibung rein. Bla Bla und so", "01234 56789", "muster@man.org"));

        // Attach the adapter to a ListView

        View view = inflater.inflate(R.layout.fragment_kit_at_aglance, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.glanceView);
        //ListView listView = (ListView) this.getActivity().findViewById(R.id.contactListView);

        prepareData(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                //Toast.makeText(getActivity(), "myPos " + i, Toast.LENGTH_SHORT).show();
                if (i != 0) {
                    String url = (String)listView.getItemAtPosition(i);
                    download(url);

                    //Object objects = listView.getItemAtPosition(i);
                    //System.out.println(objects);
                    //pDialog = ProgressDialog.show(getActivity(), "dialog title","dialog message", true);
                }
            }
        });
        listView.setAdapter(adapter);
        return view;
    }

    private void prepareData(KitAtAGlanceAdapter adapter) {
        adapter.addInfoText("The KIT","With more than 9,000 employees and an annual budget of about EUR 785 million, KIT is one of the biggest research and education institutions worldwide and has the potential of reaching a top position in selected research areas on an international level. The objective is to turn KIT into an institution of top research, excellent scientific education, and a prominent location of academic life, life-long learning, comprehensive advanced training, unrestricted exchange of know-how, and sustainable innovation culture.");
        adapter.addPdf("KIT Overview", "http://www.kit.edu/mediathek/print_forschung/Broschuere_KIT_Ueberblick_en.pdf");
        adapter.addPdf("Facts and Figures", "http://www.kit.edu/mediathek/print_forschung/Flyer_KIT_en.pdf");
        adapter.addPdf("Particles and Forces in the Universe", "http://www.kit.edu/mediathek/print_forschung/Broschuere_KCETA_en.pdf");
        adapter.addPdf("Energy for Tomorrow","http://www.kit.edu/mediathek/print_forschung/Broschuere_Energie_en.pdf");
        adapter.addPdf("For an Environment Worth Living","http://www.kit.edu/mediathek/print_forschung/Broschuere_Klima_Umwelt_en.pdf");
        adapter.addPdf("Solutions for Tomorrow’s Mobility","http://www.kit.edu/mediathek/print_forschung/Broschuere_Mobilitaetssysteme_en.pdf");
        adapter.addPdf("Reflecting Technology – Designing Society","http://www.kit.edu/mediathek/print_forschung/Broschuere_Mensch_Technik_en.pdf");
        adapter.addPdf("Power Stores for Renewable Energies and Electric Drive Trains for E-Vehicles","http://www.kit.edu/mediathek/print_forschung/Flyer_Competence_E_en.pdf");
        adapter.addPdf("An der Spitze","http://www.kit.edu/mediathek/print_looKIT/Mit_Atmosphaerenforschern_des_KIT_an_der_Zugspitze.pdf");
        adapter.addPdf("KIT at CERN: The Man Inside","http://www.kit.edu/mediathek/print_looKIT/Mit_KIT-Wissenschaftlern_auf_Teilchensuche_am_CERN.pdf");
        adapter.addPdf("KIT-Water Resources Management System","http://www.kit.edu/mediathek/print_looKIT/Mit_KIT-Bauingenieuren_in_Hoehlenkraftwerken_auf_Java.pdf");
    }

    private class DownloadFile extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = ProgressDialog.show(getActivity(), "Download","Downloading PDF...", true);

        }

        @Override
        protected String doInBackground(String... strings) {
            final int  MEGABYTE = 1024 * 1024;
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "KITAlumniApp");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);
            int count = 0;

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            //FileDownloader.downloadFile(fileUrl, pdfFile);
            try {

                URL url = new URL(fileUrl);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                //urlConnection.setRequestMethod("GET");
                //urlConnection.setDoOutput(true);
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

            return null;
        }


        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            pDialog.dismiss();
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
        }
    }

    public void download(String url)
    {

        String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
        String fileName = URLUtil.guessFileName(url, null, fileExtenstion);
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/KITAlumniApp/" + fileName);
        if (pdfFile.exists()) {
            view(fileName);
            //Toast.makeText(getActivity(), url + ":" + fileName, Toast.LENGTH_SHORT).show();
        } else {
            new DownloadFile().execute(url, fileName);
            //Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }

    }

    public void view(String fileName)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/KITAlumniApp/" + fileName);  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
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
