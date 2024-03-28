package com.example.rehnee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText ID, Password;
    private Button sign_in;
    private TextView message, forget;
    private ProgressBar progressBar;
    private LinearLayout Bar_layout;

    private String Id, password, status;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = this.getIntent();
        status = intent.getStringExtra("status");

        findid();
        getpermission();
        createLanguageTTS();

        sign_in.setOnClickListener(sign_inOnClick);
        forget.setOnClickListener(forgetOnClick);
    }

    private void getpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大於等於 23(Android 6.0)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            }
        }
    }


    private void findid() {
        ID = (EditText) findViewById(R.id.ID);
        Password = (EditText) findViewById(R.id.Password);
        sign_in = (Button) findViewById(R.id.sign_in);
        forget = (TextView) findViewById(R.id.forget);
        message = (TextView) findViewById(R.id.message);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Bar_layout = (LinearLayout) findViewById(R.id.Bar_layout);
    }

    private Button.OnClickListener sign_inOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (status != null) {
                if (status.equals("from_FCM")) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, Chat.class);
                    intent.putExtra("Id", ID.getText().toString());
                    startActivity(intent);
                }
            } else {
                if (ID.getText().toString().matches("") || Password.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "請填寫完整", Toast.LENGTH_SHORT).show();
                } else {
                    Id = ID.getText().toString();
                    password = Password.getText().toString();
                    General general = new General();

                    if (general.check_network(MainActivity.this) == true) {//如果有網路
                        progressBar(1);
                        sign_in.setEnabled(false);
                        forget.setEnabled(false);
                        AsyncTask_Login asyncTask = new AsyncTask_Login(Id, password, new AsyncTask_Login.AsyncResponse() {
                            @Override
                            public void processFinish(String result) {
                                Pattern pattern = Pattern.compile("[^0-9]");
                                Matcher matcher = pattern.matcher(result);
                                result = matcher.replaceAll("").trim();

                                if (result.equals("1")) {//如果帳號密碼正確 ("1"代表正確)
                                    Update_data update_data = new Update_data(MainActivity.this, MainActivity.this, Id, password);
                                    update_data.sqlite_patient_check_insert();//如果(手機資料庫)沒有此帳號密碼，就新增到(手機資料庫)裡面
                                    update_data.get_network_medical_order();//取得(網路資料庫)的復健醫囑
                                    update_data.get_network_record();//取得(網路資料庫)的復健記錄
                                    update_data.upload_Token();//傳送手機token到網路資料庫
                                    update_data.get_network_faq();//取得(網路資料庫)的FAQ
                                    goto_Menu();//切換畫面到 Menu
                                    Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                                } else if (result.equals("0")) {
                                    Toast.makeText(MainActivity.this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "連線問題，請確認網路是否正常", Toast.LENGTH_SHORT).show();
                                }
                                progressBar(0);
                                sign_in.setEnabled(true);
                                forget.setEnabled(true);
                            }
                        });
                        asyncTask.execute();
                    } else {
                        warning();//提醒! 是否要在離線模式下執行
                    }
                }
            }

            /*Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    forget.performClick();
                }
            });*/
        }
    };

    private TextView.OnClickListener forgetOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            General general = new General();
            if (general.check_network(MainActivity.this) == true) {//如果有網路
                goto_Forget_password();
            } else {
                Toast.makeText(MainActivity.this, "無法使用，請確認網路是否連接", Toast.LENGTH_SHORT).show();
            }
            //test();
            //tts.speak("復健完成", TextToSpeech.QUEUE_FLUSH, null);
        }
    };

    private void createLanguageTTS() {
        if (tts == null) {
            tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int arg0) {
                    // TTS 初始化成功
                    if (arg0 == TextToSpeech.SUCCESS) {
                        // 指定的語系: 中文
                        Locale locale = Locale.CHINESE;

                        // 目前指定的【語系+國家】TTS, 已下載離線語音檔, 可以離線發音
                        if (tts.isLanguageAvailable(locale) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            tts.setLanguage(locale);
                        }
                    }
                }
            }
            );
        }
    }

    private void goto_Menu() {//切換畫面到 Menu

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Menu.class);
        intent.putExtra("Id", ID.getText().toString());
        startActivity(intent);
    }//切換畫面到 Menu

    private void goto_Forget_password() {//切換畫面到 Forget_password
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Forget_password.class);
        startActivity(intent);
    }//切換畫面到 Forget_password

    protected void onRestart() {
        super.onRestart();
        ID.setText("");
        Password.setText("");
        progressBar(0);
    }

    private void progressBar(int status) {
        if (status == 1) {
            progressBar.setVisibility(View.VISIBLE);
            message.setVisibility(View.VISIBLE);
            Bar_layout.setVisibility(View.VISIBLE);
            dimBackground(1.0f, 0.5f);//調整螢幕亮度(變暗)
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            message.setVisibility(View.INVISIBLE);
            Bar_layout.setVisibility(View.INVISIBLE);
            dimBackground(0.5f, 1.0f);//調整螢幕亮度(變亮)
        }

    }

    private void dimBackground(final float from, final float to) {//調整螢幕亮度
        final Window window = getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }//調整螢幕亮度

    public boolean onKeyDown(int keyCode, KeyEvent event) {//當按下返回健
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//當按下返回健

    public void ConfirmExit() {//退出確認
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("離開");
        alertDialog.setMessage("確定要離開?");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "否",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //不退出不用執行任何操作
                    }
                });
        alertDialog.show();//示對話框
    }//退出確認

    /*---------------------------------------------沒網路---------------------------------------------*/
    public void warning() {//提醒! 是否要在離線模式下執行
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("提醒!");
        dialog.setMessage("目前未連接網路，是否要在離線模式執行，但如果該帳號未使用過將無法執行離線模式 （離線模式無法接收到最新醫囑與上傳記錄）");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {//退出按鈕
            public void onClick(DialogInterface dialog, int i) {
                if (sqlite_patient_query() == true) {//(手機資料庫) 如果有找到帳號密碼
                    goto_Menu();//切換畫面到 Rehabilitation
                } else {
                    Toast.makeText(MainActivity.this, "帳號或密碼錯誤 或 帳號密碼未使用過", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用執行任何操作
            }
        });
        dialog.show();//示對話框
    }//提醒! 是否要在離線模式下執行

    private boolean sqlite_patient_query() {//(手機資料庫) 查詢帳號密碼
        String[][] values;
        boolean check = false;
        int x;

        SQLite_Patient sqLite_patient = new SQLite_Patient(MainActivity.this, MainActivity.this);
        values = sqLite_patient.query(Id);
        for (x = 0; x < values.length; x++) {
            if (values[0][0].equals(Id) & values[x][1].equals(password)) {//如果帳號密碼正確
                check = true;
                break;
            } else {
                check = false;
            }
        }

        return check;
    }//(手機資料庫) 查詢帳號密碼


}
