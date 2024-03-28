package com.example.rehnee;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

class AsyncTask_FAQ extends AsyncTask<String, Void, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String StringURL;
    private Context context;

    public AsyncTask_FAQ(Context context) {
        this.context = context;
    }


    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("FAQ");

        try {
            url = new URL(StringURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");

            // 擷取url的網頁資料，並將回傳資料放入BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String value = br.readLine();
            br.close();

            urlConnection.disconnect();
            return value;

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

