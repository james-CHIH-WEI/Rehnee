package com.example.rehnee;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class AsyncTask_Login extends AsyncTask<String, Void, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, password,StringURL;
    public AsyncResponse delegate;

    public AsyncTask_Login(String Id, String password, AsyncResponse delegate) {
        this.Id = Id;
        this.password = password;
        this.delegate = delegate;
    }

    public interface AsyncResponse {
        void processFinish(String result);
    }

    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("Login");

        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(password, "UTF-8");
            url = new URL(StringURL + data);
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
        delegate.processFinish(result);
    }


}

