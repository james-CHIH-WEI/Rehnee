package com.example.rehnee;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Rehabilitation extends AppCompatActivity {

    private Button link, show_picture;
    private ImageView start, type_picture;
    private ProgressBar progressBar;
    private LinearLayout Bar_layout, type_show;
    private TextView processing, success, medical_date_text, medical_type_text, medical_angle_text, medical_frequency_text, message, type_name;
    private final static int REQUEST_ENABLE_BT = 1;

    private int type, angle, frequency;
    public int speech_status = 0;
    private String Id, medical_date, correction_status;
    private int[] type_status;

    private Bluetooth bluetooth;
    private TextToSpeech textToSpeech;

    private SpeechRecognizer speechRecognizer;
    private Intent mSpeechIntent;

    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitation);

        findid();
        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");
        type_status = intent.getIntArrayExtra("type_status");
        medical_date = intent.getStringExtra("medical_date");
        type = Integer.valueOf(intent.getStringExtra("type"));
        angle = Integer.valueOf(intent.getStringExtra("angle"));
        frequency = Integer.valueOf(intent.getStringExtra("frequency"));
        correction_status = intent.getStringExtra("correction_status");
        //System.out.println(type_status[0]+"   "+type_status[1]+"   "+type_status[2]);
        bluetooth = new Bluetooth(Rehabilitation.this, Rehabilitation.this, Id);

        createLanguageTTS();//設定語音功能
        set_RecognizerIntent();//設定語音辨識
        bluetooth.bluetooth_check(textToSpeech, speechRecognizer, mSpeechIntent);

        medical_date_text.setText(medical_date);
        if (type == 1) {
            medical_type_text.setText(getString(R.string.type_1));
        } else if (type == 2) {
            medical_type_text.setText(getString(R.string.type_2));
        } else if (type == 3) {
            medical_type_text.setText(getString(R.string.type_3));
        }
        medical_angle_text.setText(String.valueOf(angle));
        medical_frequency_text.setText(String.valueOf(frequency));

        link.setOnClickListener(linkOnClick);
        start.setOnTouchListener(startOnTouch);
        start.setOnClickListener(startOnClick);

        show_picture.setOnClickListener(show_pictureOnClick);
        type_picture.setOnClickListener(type_pictureOnClick);
    }

    private void findid() {
        processing = (TextView) findViewById(R.id.processing);
        success = (TextView) findViewById(R.id.success);
        medical_date_text = (TextView) findViewById(R.id.medical_date_text);
        medical_type_text = (TextView) findViewById(R.id.medical_type_text);
        medical_angle_text = (TextView) findViewById(R.id.medical_angle_text);
        medical_frequency_text = (TextView) findViewById(R.id.medical_frequency_text);
        show_picture = (Button) findViewById(R.id.show_picture);

        link = (Button) findViewById(R.id.link);
        start = (ImageView) findViewById(R.id.start);

        message = (TextView) findViewById(R.id.message);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Bar_layout = (LinearLayout) findViewById(R.id.Bar_layout);

        type_name = (TextView) findViewById(R.id.type_name);
        type_picture = (ImageView) findViewById(R.id.type_picture);
        type_show = (LinearLayout) findViewById(R.id.type_show);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                bluetooth.bluetooth_link(textToSpeech, speechRecognizer, mSpeechIntent);
            }
        }
    }

    private Button.OnClickListener linkOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (success.getTextColors().getDefaultColor() == Color.parseColor("#1fb51f") | processing.getTextColors().getDefaultColor() == Color.parseColor("#0000FF")) {
                Toast.makeText(Rehabilitation.this, "已完成連接", Toast.LENGTH_SHORT).show();
            } else {
                bluetooth.bluetooth_check(textToSpeech, speechRecognizer, mSpeechIntent);
            }
        }
    };

    private Button.OnClickListener startOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (success.getTextColors().getDefaultColor() == Color.parseColor("#1fb51f") & processing.getTextColors().getDefaultColor() == Color.parseColor("#DDDDDD")) {
                speechRecognizer.cancel();
                speech_status = 1;
                bluetooth.speech_status = 1;
                if (correction_status == null) {
                    correction();//開始校正
                } else {
                    bluetooth.bluetoothStart(type, medical_date, angle, frequency, type_status, "0");//0代表沒有在做校正
                }
            } else if (processing.getTextColors().getDefaultColor() == Color.parseColor("#0000FF")) {
                Toast.makeText(Rehabilitation.this, "已經開始復健", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Rehabilitation.this, "請先連接護膝", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Button.OnTouchListener startOnTouch = new Button.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {  //按下的時候改變背景及顏色
                start.setImageResource(R.drawable.startdown);
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {  //起來的時候恢復背景與顏色
                start.setImageResource(R.drawable.startup);
            }

            return false;
        }
    };

    private Button.OnClickListener show_pictureOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (type == 1) {
                type_picture.setImageResource(R.drawable.action1);
            } else if (type == 2) {
                type_picture.setImageResource(R.drawable.action2);
            } else if (type == 3) {
                type_picture.setImageResource(R.drawable.action3);
            }
            type_name.setVisibility(View.VISIBLE);
            type_picture.setVisibility(View.VISIBLE);
            type_show.setVisibility(View.VISIBLE);
            link.setEnabled(false);
            start.setEnabled(false);
        }
    };

    private Button.OnClickListener type_pictureOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            type_name.setVisibility(View.INVISIBLE);
            type_picture.setVisibility(View.INVISIBLE);
            type_show.setVisibility(View.INVISIBLE);
            link.setEnabled(true);
            start.setEnabled(true);
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ConfirmExit() {//退出確認
        AlertDialog alertDialog = new AlertDialog.Builder(Rehabilitation.this).create();
        alertDialog.setTitle("注意復健未完成");
        alertDialog.setMessage("確定要離開?");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (bluetooth.mConnectedThread != null) {
                            bluetooth.mConnectedThread.cancel();
                        }
                        if (textToSpeech != null) {
                            textToSpeech.shutdown();
                        }

                        speechRecognizer.stopListening();
                        speechRecognizer.destroy();

                        Intent intent = new Intent();
                        intent.setClass(Rehabilitation.this, Menu.class);
                        intent.putExtra("Id", Id);
                        startActivity(intent);
                        finish();//關閉activity
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "否",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //不退出不用執行任何操作
                    }
                });
        alertDialog.show();//示對話框
    }

    private void correction() {
        alertDialog = new AlertDialog.Builder(Rehabilitation.this).create();
        alertDialog.setTitle("校正");

        General general = new General();
        if (general.check_network(Rehabilitation.this) == true) {
            textToSpeech.speak("注意按下或說出準備完成後進行校正，請將腳呈現90度不動5秒鐘", TextToSpeech.QUEUE_FLUSH, null);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (speech_status == 1) {
                        speechRecognizer.startListening(mSpeechIntent);
                    }
                }
            }, 7000);
            alertDialog.setMessage("注意按下或說出準備完成後進行校正，請將腳呈現90度不動5秒鐘");
        } else {
            textToSpeech.speak("注意按下後進行校正，請將腳呈現90度不動5秒鐘", TextToSpeech.QUEUE_FLUSH, null);
            alertDialog.setMessage("注意按下後進行校正，請將腳呈現90度不動5秒鐘");
        }

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "準備完成",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        bluetooth.mConnectedThread.write("1");//校正

                        progressBar(1);
                        link.setEnabled(false);
                        start.setEnabled(false);

                        textToSpeech.speak("校正中請不要移動", TextToSpeech.QUEUE_FLUSH, null);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bluetooth.bluetoothStart(type, medical_date, angle, frequency, type_status, "1");//1代表有在做校正
                                progressBar(0);
                                link.setEnabled(true);
                                start.setEnabled(true);
                            }
                        }, 5000);
                        speechRecognizer.cancel();
                        speech_status = 2;
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        speech_status = 0;
                        speechRecognizer.cancel();
                    }
                });
        alertDialog.show();//示對話框
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

    private void createLanguageTTS() {//設定語音功能
        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(Rehabilitation.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int arg0) {
                    // TTS 初始化成功
                    if (arg0 == TextToSpeech.SUCCESS) {
                        // 指定的語系: 中文
                        Locale locale = Locale.CHINESE;

                        // 目前指定的【語系+國家】TTS, 已下載離線語音檔, 可以離線發音
                        if (textToSpeech.isLanguageAvailable(locale) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            textToSpeech.setLanguage(locale);
                        }
                    }
                }
            }
            );
        }
    }

    private void set_RecognizerIntent() {//設定語音辨識
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new MyRecognizerListener());
        mSpeechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "zh-TW");
        mSpeechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.example.Rehnee");
        mSpeechIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        mSpeechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
    }

    private class MyRecognizerListener implements RecognitionListener {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d("", "on ready for speech");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d("", "speach begining");
        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            if (error == SpeechRecognizer.ERROR_CLIENT || error == SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS) {
                Log.d("", "client error");
            }
            //else ask to repeats
            else {
                Log.d("", "other error");
                onStart();
                speechRecognizer.startListening(mSpeechIntent);
            }
        }

        @Override
        public void onResults(Bundle results) {
            int x;
            if (results != null) {
                ArrayList<String> matches = null;
                matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    Log.d("", "results are " + matches.toString());
                    if (speech_status == 0) {
                        for (x = 0; x < matches.size(); x++) {
                            if (matches.get(x).contains("開始復健")) {
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        start.performClick();
                                    }
                                });
                                break;
                            }
                        }

                        if (x == matches.size()) {
                            speechRecognizer.startListening(mSpeechIntent);
                        }
                    } else if (speech_status == 1) {
                        for (x = 0; x < matches.size(); x++) {
                            if (matches.get(x).contains("準備完成")) {
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
                                    }
                                });
                                break;
                            } else if (matches.get(x).contains("取消")) {
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
                                    }
                                });
                                break;
                            }
                        }

                        if (x == matches.size()) {
                            speechRecognizer.startListening(mSpeechIntent);
                        }
                    }

                }

            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.d("", "partial results");
        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    }
}
