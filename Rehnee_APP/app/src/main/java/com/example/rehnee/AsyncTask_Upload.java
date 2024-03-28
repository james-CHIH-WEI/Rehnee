package com.example.rehnee;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class AsyncTask_Upload extends AsyncTask<String, Integer, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, medical_date,medical_type,medical_angle,medical_frequency, finish_date, finish_time, spend_time, StringURL;
    private Context context;

    private ProgressDialog progressDialog;


    public AsyncTask_Upload(Context context, String Id, String medical_date, String medical_type, String medical_angle, String medical_frequency, String finish_date, String finish_time, String spend_time) {
        this.context = context;
        this.Id = Id;
        this.medical_date = medical_date;
        this.medical_type=medical_type;
        this.medical_angle=medical_angle;
        this.medical_frequency=medical_frequency;
        this.finish_date = finish_date;
        this.finish_time = finish_time;
        this.spend_time = spend_time;
    }

    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Update...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("Upload");

        publishProgress(0);
        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8")
                    + "&" + "key2=" + URLEncoder.encode(medical_date, "UTF-8")
                    + "&" + "key3=" + URLEncoder.encode(medical_type, "UTF-8")
                    + "&" + "key4=" + URLEncoder.encode(medical_angle, "UTF-8")
                    + "&" + "key5=" + URLEncoder.encode(medical_frequency, "UTF-8")
                    + "&" + "key6=" + URLEncoder.encode(finish_date, "UTF-8")
                    + "&" + "key7=" + URLEncoder.encode(finish_time, "UTF-8")
                    + "&" + "key8=" + URLEncoder.encode(spend_time, "UTF-8");
            url = new URL(StringURL + data);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.getInputStream();

            urlConnection.disconnect();

            publishProgress(100);
            return "ok";

        } catch (IOException e) {
            return "連接失敗 (" + e + ")";
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // TODO Auto-generated method stub
        progressDialog.setProgress(progress[0]);
    }

    // onPostExecute() 會在doInBackground()結束後被執行，參數值result為doInBackground()的回傳值
    @Override
    protected void onPostExecute(String result) {
        if (result.equals("ok")) {
            progressDialog.dismiss();
        }
    }
}
