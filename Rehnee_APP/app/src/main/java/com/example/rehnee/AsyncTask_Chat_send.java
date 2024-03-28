package com.example.rehnee;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class AsyncTask_Chat_send extends AsyncTask<String, Void, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, send_content, content_date, content_time, StringURL;


    public AsyncTask_Chat_send(String Id, String send_content, String content_date, String content_time) {
        this.Id = Id;
        this.send_content = send_content;
        this.content_date = content_date;
        this.content_time = content_time;
    }

    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("Chat_send");

        try {
            String data = "?" +
                    "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" +
                    "key2=" + URLEncoder.encode(send_content, "UTF-8") + "&" +
                    "key3=" + URLEncoder.encode(content_date, "UTF-8") + "&" +
                    "key4=" + URLEncoder.encode(content_time, "UTF-8");
            url = new URL(StringURL + data);
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");

            urlConnection.getInputStream();

            urlConnection.disconnect();
            return "";

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
