package com.example.rehnee;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AsyncTask_Verification_code extends AsyncTask<String, Integer, String> {


    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, verification_code, StringURL;
    private Activity activity;
    private Context context;




    public AsyncTask_Verification_code(Activity activity, Context context, String Id, String verification_code) {
        this.activity = activity;
        this.context = context;
        this.Id = Id;
        this.verification_code = verification_code;
    }



    protected String doInBackground(String... urls) {
        General general = new General();
        StringURL = general.url("Verification_code");

        publishProgress(0);
        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(verification_code, "UTF-8");
            url = new URL(StringURL + data);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String result = br.readLine();
            br.close();

            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(result);
            result = matcher.replaceAll("").trim();

            urlConnection.disconnect();



            return result;

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