package com.example.rehnee;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update_data {
    private String Id, password;
    private Activity activity;
    private Context context;

    private SQLite_Patient sqLite_patient;
    private SQLite_Medical_order sqLite_medical_order;
    private SQLite_Record sqLite_record;
    private SQLite_FAQ sqLite_faq;

    public Update_data(Activity activity, Context context, String Id, String password) {
        this.activity = activity;
        this.context = context;
        this.Id = Id;
        this.password = password;

        sqLite_patient = new SQLite_Patient(activity, context);
        sqLite_medical_order = new SQLite_Medical_order(activity, context);
        sqLite_record = new SQLite_Record(activity, context);
        sqLite_faq = new SQLite_FAQ(context);
    }

    /*---------------------------------------------有網路---------------------------------------------*/

    /*-------帳號密碼-------*/
    public boolean check_network_login() {//檢查(網路資料庫)的帳號密碼是否正確
        final boolean[] check = {false};

        AsyncTask_Login asyncTask = new AsyncTask_Login(Id, password, new AsyncTask_Login.AsyncResponse() {
            @Override
            public void processFinish(String result) {
                Pattern pattern = Pattern.compile("[^0-9]");
                Matcher matcher = pattern.matcher(result);
                result = matcher.replaceAll("").trim();

                if (result.equals("1")) {//如果帳號密碼正確 ("1"代表正確)
                    check[0] = true;
                    Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show();
                } else {
                    check[0] = false;
                    Toast.makeText(context, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                }
            }

        });
        asyncTask.execute();

        return check[0];
    }//檢查(網路資料庫)的帳號密碼是否正確

    public void sqlite_patient_check_insert() {//如果(手機資料庫)沒有此帳號密碼，就新增到SQLite裡面
        String[][] values;
        int x;

        values = sqLite_patient.query(Id);

        if (values.length == 0) {
            sqLite_patient.insert(Id, password);
        } else {
            for (x = 0; x < values.length; x++) {
                if (values[x][0].equals(Id)) {
                    sqLite_patient.update(Id, password);
                    break;
                }
            }
            if (x == values.length) {//如果SQLite裡面沒有這筆帳號密碼
                sqLite_patient.insert(Id, password);
            }
        }

    }//如果(手機資料庫)沒有此帳號密碼，就新增到SQLite裡面

    /*-------取得復健醫囑-------*/
    public void get_network_medical_order() {
        String[][] values = new String[0][2];
        String[] value;
        String result;
        int x;

        AsyncTask_Medical_order asyncTask_medical_order = new AsyncTask_Medical_order(Id);
        try {
            result = asyncTask_medical_order.execute().get();
            //content = result.split(" ");
            value = result.split("/");
            for (x = 0; x < value.length - 1; x++) {
                values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
                values[values.length - 1] = value[x].split(" ");
            }
            sqlite_medical_order_insert_update(values);//(手機資料庫) 上傳或更新

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }//取的(網路資料庫)的醫囑

    public void sqlite_medical_order_insert_update(String[][] asynctask_values) {//(手機資料庫) 上傳或更新
        String[][] sqLite_values;
        int x;

        sqLite_values = sqLite_medical_order.query(Id);

        if (sqLite_values.length == 0) {
            for (x = 0; x < asynctask_values.length; x++) {
                sqLite_medical_order.insert(Id, asynctask_values[x][0], asynctask_values[x][1]);
            }
        } else {
            for (x = sqLite_values.length; x < asynctask_values.length; x++) {
                sqLite_medical_order.insert(Id, asynctask_values[x][0], asynctask_values[x][1]);
            }
        }

    }//(手機資料庫) 上傳或更新

    /*-------取得復健記錄-------*/
    public void get_network_record() {
        String[] value;
        String[][] values = new String[0][5];
        String result;
        int x;

        AsyncTask_Record asyncTask_record = new AsyncTask_Record(Id);
        try {
            result = asyncTask_record.execute().get();

            value = result.split("/");
            for (x = 0; x < value.length - 1; x++) {
                values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
                values[values.length - 1] = value[x].split(" ");
            }
            sqlite_record_insert_update(values);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sqlite_record_insert_update(String[][] asynctask_values) {
        String[][] sqLite_values;
        int x;

        sqLite_values = sqLite_record.query(Id);

        if (sqLite_values.length == 0) {//(使用者第一次使用或者是新APP)如果(手機資料庫)沒有資料，下載(網路資料庫)的記錄到(手機資料庫)
            for (x = 0; x < asynctask_values.length; x++) {
                sqLite_record.insert(Id, asynctask_values[x][0], asynctask_values[x][1],asynctask_values[x][2],
                        asynctask_values[x][3], asynctask_values[x][4], asynctask_values[x][5], asynctask_values[x][6]);
                //分別為(Id, medical_date, medical_type, medical_angle, medical_frequency, finish_date, finish_time, spend_time)
            }

        } else {
            if (asynctask_values.length != 0) {
                for (x = asynctask_values.length; x < sqLite_values.length; x++) {
                    AsyncTask_Upload asyncTask_upload = new AsyncTask_Upload(context, sqLite_values[x][0],
                            sqLite_values[x][1], sqLite_values[x][2], sqLite_values[x][3],
                            sqLite_values[x][4], sqLite_values[x][5], sqLite_values[x][6], sqLite_values[x][7]);
                    //輸出欄位為(Id , medical_date, medical_type, medical_angle, medical_frequency, finish_date, finish_time, spend_time)
                    asyncTask_upload.execute();
                }
            }
        }
    }

    /*-------推播權杖-------*/
    public void upload_Token() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("www", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        Log.d("www", token);

                        AsyncTask_Token asyncTask_token = new AsyncTask_Token(Id, token);
                        try {
                            asyncTask_token.execute().get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /*-------取得復健記錄-------*/
    public void get_network_faq() {
        String[] value;
        String[][] values = new String[0][3];
        String result;
        int x;

        AsyncTask_FAQ asyncTask_faq = new AsyncTask_FAQ(context);
        try {
            result = asyncTask_faq.execute().get();

            value = result.split("/");
            for (x = 0; x < value.length - 1; x++) {
                values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
                values[values.length - 1] = value[x].split(" ");
            }
            update_SQL(values);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update_SQL(String[][] asynctask_values) {
        int x;
        sqLite_faq.clean_table();
        for (x = 0; x < asynctask_values.length; x++) {
            sqLite_faq.insert(asynctask_values[x][0], asynctask_values[x][1], asynctask_values[x][2]);
        }
    }

}
