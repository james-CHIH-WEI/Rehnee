package com.example.rehnee;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class AsyncTask_Token extends AsyncTask<String, Void, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, Instance_Id,StringURL;


    public AsyncTask_Token(String Id, String Instance_Id) {
        this.Id = Id;
        this.Instance_Id = Instance_Id;
    }

    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("Token");

        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(Instance_Id, "UTF-8");
            url = new URL(StringURL + data);

            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");

            urlConnection.getInputStream();

            urlConnection.disconnect();
            return "ok";

        } catch (IOException e) {
            return "連接失敗 (" + e + ")";
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    // onPostExecute() 會在doInBackground()結束後被執行，參數值result為doInBackground()的回傳值
    @Override
    protected void onPostExecute(String result) {

    }


}