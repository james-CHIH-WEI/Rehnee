package com.example.rehnee;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class AsyncTask_Send_mail extends AsyncTask<String, Integer, String> {

    URL url = null;
    HttpURLConnection urlConnection = null;
    private String Id, email, StringURL, StringURL2;
    private Activity activity;
    private Context context;

    private EditText forget_id, forget_email, verification;
    private Button forget_email_send, forget_send;

    private ProgressDialog progressDialog;

    public AsyncTask_Send_mail(Activity activity, Context context, String Id, String email) {
        this.activity = activity;
        this.context = context;
        this.Id = Id;
        this.email = email;

        findid();
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
        StringURL = general.url("Check_email");
        StringURL2 = general.url("Send_mail");

        String result;

        for (int x = 0; x < 2; x++) {
            if (x == 0) {
                result = Check_patient_data();
                Pattern pattern = Pattern.compile("[^0-9]");
                Matcher matcher = pattern.matcher(result);
                result = matcher.replaceAll("").trim();
                if (!result.equals("2")) {
                    return result;
                }
            } else {
                Send_mail();
            }
        }
        return "ok";

    }


    @Override
    protected void onProgressUpdate(Integer... progress) {
        // TODO Auto-generated method stub
        progressDialog.setProgress(progress[0]);
    }

    // onPostExecute() 會在doInBackground()結束後被執行，參數值result為doInBackground()的回傳值
    @Override
    protected void onPostExecute(String result) {
        if (result.equals("0")) {
            Toast.makeText(context, "帳號錯誤", Toast.LENGTH_SHORT).show();
        } else if (result.equals("1")) {
            Toast.makeText(context, "電子信箱錯誤", Toast.LENGTH_SHORT).show();
        } else if (result.equals("ok")) {
            verification.setEnabled(true);
            forget_send.setEnabled(true);
            forget_id.setEnabled(false);
            forget_email.setEnabled(false);
            forget_email_send.setEnabled(false);
        }
        progressDialog.dismiss();

    }

    private String Check_patient_data() {
        publishProgress(0);
        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(email, "UTF-8");
            url = new URL(StringURL + data);
            urlConnection = (HttpURLConnection) url.openConnection();

            // 擷取url的網頁資料，並將回傳資料放入BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String value = br.readLine();
            br.close();

            urlConnection.disconnect();

            publishProgress(50);
            return value;

        } catch (IOException e) {
            return "連接失敗 (" + e + ")";
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    private String Send_mail() {
        try {
            String data = "?" + "key1=" + URLEncoder.encode(Id, "UTF-8") + "&" + "key2=" + URLEncoder.encode(email, "UTF-8");
            url = new URL(StringURL2 + data);
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

    private void findid() {
        forget_id = (EditText) activity.findViewById(R.id.forget_id);
        forget_email = (EditText) activity.findViewById(R.id.forget_email);
        verification = (EditText) activity.findViewById(R.id.verification);
        forget_email_send = (Button) activity.findViewById(R.id.forget_email_send);
        forget_send = (Button) activity.findViewById(R.id.forget_send);
    }
}

