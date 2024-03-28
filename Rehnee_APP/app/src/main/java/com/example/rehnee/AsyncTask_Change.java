package com.example.rehnee;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class AsyncTask_Change extends AsyncTask<String, Integer, String> {


    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, password, StringURL;
    private Context context;

    private ProgressDialog progressDialog;


    public AsyncTask_Change(Context context, String Id, String password) {
        this.context = context;
        this.Id = Id;
        this.password = password;
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
        StringURL = general.url("Change");

        publishProgress(0);
        try {
            System.out.println(Id+"    "+password);
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(password, "UTF-8");
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
        progressDialog.dismiss();
    }

}