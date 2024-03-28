package com.example.rehnee;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Bluetooth {

    private BluetoothAdapter mBTAdapter;
    private BluetoothSocket mBTSocket = null;
    public ConnectedThread mConnectedThread;

    private TextToSpeech textToSpeech;
    private SpeechRecognizer speechRecognizer;
    private Intent mSpeechIntent;

    private Handler handler;

    private String address = "20:16:02:18:43:00";
    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final static int REQUEST_ENABLE_BT = 1;
    private final static int MESSAGE_READ = 2;
    private final static int CONNECTING_STATUS = 3;

    private TextView connecting, processing, success, failure, current_angle_text, remaining_frequency_text;
    private Button link;
    private ImageView start;
    private Activity activity;
    private Context context;

    private int medical_angle, medical_frequency, remaining_frequency, in_one_time, medical_type;
    private String Id, receiveData = "", medical_date, finish_date, finish_time, correction_status;
    private long start_time = 0, end_time = 0;
    private boolean fail;
    private int[] type_status;

    public int speech_status;

    private General general;

    public Bluetooth(Activity activity, Context context, String Id) {
        this.activity = activity;
        this.context = context;
        this.Id = Id;

        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        general = new General();

        findid();
        handler();
    }

    private void findid() {
        connecting = (TextView) activity.findViewById(R.id.connecting);
        processing = (TextView) activity.findViewById(R.id.processing);
        success = (TextView) activity.findViewById(R.id.success);
        failure = (TextView) activity.findViewById(R.id.failure);

        current_angle_text = (TextView) activity.findViewById(R.id.current_angle_text);
        remaining_frequency_text = (TextView) activity.findViewById(R.id.remaining_frequency_text);
        link = (Button) activity.findViewById(R.id.link);
        start = (ImageView) activity.findViewById(R.id.start);
    }

    private void handler() {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == MESSAGE_READ) { //收到MESSAGE_READ 開始接收資料
                    String readMessage = null, correctData = "";
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                        readMessage = readMessage.substring(0, 1);//取得傳過來字串的第一個字元，其餘為雜訊

                        receiveData += readMessage; //拼湊每次收到的字元成字串
                        //System.out.println(receiveData);


                        if (receiveData.indexOf("(") == 0) {
                            if (receiveData.indexOf(")") == receiveData.length() - 1) {
                                Pattern pattern = Pattern.compile("[^0-9]");
                                Matcher matcher = pattern.matcher(receiveData);
                                correctData = matcher.replaceAll("").trim();

                                if (receiveData.length() - 2 == correctData.length() & correctData.length() != 0 & correctData != "") {
                                    compute(Integer.valueOf(correctData));
                                    receiveData = "";
                                } else {
                                    receiveData = "";
                                }

                            }
                        } else {
                            receiveData = "";
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                if (msg.what == CONNECTING_STATUS) {
                    //收到CONNECTING_STATUS 顯示以下訊息
                    if (msg.arg1 == 1) {//連接成功
                        status_textview_clean_color();
                        success.setTextColor(Color.parseColor("#1fb51f"));
                        if (general.check_network(activity) == true) {
                            textToSpeech.speak("連接完成請按開始按鈕或說出開始復健", TextToSpeech.QUEUE_FLUSH, null);
                            Toast.makeText(context, "連接完成請按開始按鈕或說出開始復健", Toast.LENGTH_SHORT).show();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (speech_status == 0) {
                                        speechRecognizer.startListening(mSpeechIntent);
                                    }
                                }
                            }, 5000);

                        } else {
                            textToSpeech.speak("連接完成請按開始按鈕", TextToSpeech.QUEUE_FLUSH, null);
                            Toast.makeText(context, "連接完成請按開始按鈕", Toast.LENGTH_SHORT).show();
                        }

                    } else if (msg.arg1 == 2) {//連接失敗
                        status_textview_clean_color();
                        failure.setTextColor(Color.parseColor("#e2101b"));
                        textToSpeech.speak("請確認護膝裝置是否開啟", TextToSpeech.QUEUE_FLUSH, null);
                        Toast.makeText(context, "請確認護膝裝置是否開啟", Toast.LENGTH_SHORT).show();
                    } else {//復健中
                        status_textview_clean_color();
                        success.setTextColor(Color.parseColor("#1fb51f"));
                        processing.setTextColor(Color.parseColor("#0000FF"));
                    }
                }
            }
        };
    }


    public void bluetooth_check(TextToSpeech tts, SpeechRecognizer speechRecognizer, Intent mSpeechIntent) {
        if (!mBTAdapter.isEnabled()) {//如果藍芽沒開啟
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);//跳出視窗
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            bluetooth_link(tts, speechRecognizer, mSpeechIntent);
        }
    }

    public void bluetooth_link(TextToSpeech textToSpeech, SpeechRecognizer speechRecognizer, Intent mSpeechIntent) {
        this.textToSpeech = textToSpeech;
        this.speechRecognizer = speechRecognizer;
        this.mSpeechIntent = mSpeechIntent;

        status_textview_clean_color();
        connecting.setTextColor(Color.parseColor("#444444"));
        new Thread() {
            public void run() {
                fail = false;
                //取得裝置MAC找到連接的藍芽裝置
                BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                try {
                    mBTSocket = createBluetoothSocket(device);//建立藍芽socket
                } catch (IOException e) {
                    fail = true;
                }

                try {
                    mBTSocket.connect(); //建立藍芽連線
                } catch (IOException e) {
                    try {
                        fail = true;
                        mBTSocket.close(); //關閉socket
                        //開啟執行緒 顯示訊息
                        handler.obtainMessage(CONNECTING_STATUS, 2, -1).sendToTarget();//告訴handler連接失敗
                    } catch (IOException e2) {
                        //insert code to deal with this
                        Toast.makeText(context, "Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                }

                if (fail == false) {
                    //開啟執行緒用於傳輸及接收資料
                    mConnectedThread = new ConnectedThread(mBTSocket);
                    mConnectedThread.start();
                    handler.obtainMessage(CONNECTING_STATUS, 1, -1).sendToTarget();//告訴handler護膝連接成功
                }

            }
        }.start();
    }

    public void bluetoothStart(int type, String date, int angle, int frequency, int[] input_status, String input_correction_status) {
        medical_type = type;
        medical_date = date;
        medical_angle = angle;
        medical_frequency = frequency;
        remaining_frequency = frequency;
        type_status = input_status;
        correction_status = input_correction_status;
        in_one_time = 0;

        if (correction_status.equals("1")) {
            textToSpeech.speak("校正完成請開始復健", TextToSpeech.QUEUE_FLUSH, null);
        }

        mConnectedThread.write(String.valueOf(type + 1));//開始傳輸

        start_time = System.currentTimeMillis();//開始時間

        //開啟新執行緒顯示連接裝置名稱
        handler.obtainMessage(CONNECTING_STATUS, 3, -1, "進行復健中...").sendToTarget();//告訴handler進行復健中
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
        //creates secure outgoing connection with BT device using UUID
    }

    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        //SystemClock.sleep(200);
                        //pause and wait for rest of data
                        bytes = mmInStream.available();

                        // how many bytes are ready to be read?
                        mmInStream.read(buffer, 0, bytes);

                        // record how many bytes we actually read
                        handler.obtainMessage(MESSAGE_READ, 1, -1, buffer).sendToTarget(); // Send the obtained bytes to the UI activity
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String input) {
            byte[] bytes = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mConnectedThread.write("0");
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private void compute(int current_angle) {//角度比較
        long spend_time;

        if (medical_type == 1) {//屈膝抬腿
            if (in_one_time == 0 & remaining_frequency > 0) {
                if (current_angle <= medical_angle) {
                    textToSpeech.speak("請回到原點", TextToSpeech.QUEUE_FLUSH, null);
                    remaining_frequency--;
                    in_one_time = 1;
                }
            } else {
                if (current_angle > 90) {
                    textToSpeech.speak("請繼續復健", TextToSpeech.QUEUE_FLUSH, null);
                    in_one_time = 0;
                }
            }
        } else if (medical_type == 2) {//直膝抬腿
            if (in_one_time == 0 & remaining_frequency > 0) {
                if (current_angle >= medical_angle) {
                    textToSpeech.speak("請回到原點", TextToSpeech.QUEUE_FLUSH, null);
                    remaining_frequency--;
                    in_one_time = 1;
                }
            } else {
                if (current_angle < 10) {
                    textToSpeech.speak("請繼續復健", TextToSpeech.QUEUE_FLUSH, null);
                    in_one_time = 0;
                }
            }
        } else if (medical_type == 3) {//靠牆半蹲
            if (in_one_time == 0 & remaining_frequency > 0) {
                if (current_angle >= medical_angle) {
                    textToSpeech.speak("請回到原點", TextToSpeech.QUEUE_FLUSH, null);
                    remaining_frequency--;
                    in_one_time = 1;
                }
            } else {
                if (current_angle < 10) {
                    textToSpeech.speak("請繼續復健", TextToSpeech.QUEUE_FLUSH, null);
                    in_one_time = 0;
                }
            }
        }


        remaining_frequency_text.setText(String.valueOf(remaining_frequency));//顯示剩餘次數


        if (remaining_frequency == 0) {//當完成此復健
            mConnectedThread.write("0");
            current_angle_text.setText("0");

            end_time = System.currentTimeMillis();
            spend_time = (end_time - start_time) / 1000;

            upload_data(String.valueOf(spend_time));//上傳復健記錄

            type_status[medical_type - 1] = 2;
            int x;
            for (x = 0; x < type_status.length; x++) {
                if (type_status[x] == 1) {
                    break;
                }
            }

            String type_name = "";
            if (medical_type == 1) {
                type_name =activity.getString(R.string.type_1);
            } else if (medical_type == 2) {
                type_name = activity.getString(R.string.type_2);
            } else if (medical_type == 3) {
                type_name = activity.getString(R.string.type_3);
            }

            if (x < type_status.length) {
                finish_one_dialog(String.valueOf(spend_time), type_name);//顯示完成此次復健畫面
            } else {
                finish_all_dialog(String.valueOf(spend_time), type_name);//顯示完成所有復健畫面
            }

        } else {
            current_angle_text.setText(String.valueOf(current_angle)); //將收到的字串呈現在畫面上
        }
    }//角度比較

    private void upload_data(String spend_time) {//上傳復健記錄
        SimpleDateFormat sdFormat;
        Date date = new Date();

        sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        finish_date = sdFormat.format(date);

        sdFormat = new SimpleDateFormat("HH:mm");
        finish_time = sdFormat.format(date);


        if (general.check_network(activity) == true) {//如果有網路
            AsyncTask_Upload asyncTask_upload = new AsyncTask_Upload(context, Id, medical_date, String.valueOf(medical_type),
                    String.valueOf(medical_angle), String.valueOf(medical_frequency),
                    finish_date, finish_time, spend_time);
            asyncTask_upload.execute();
        }
        SQLite_Record sqLite_record = new SQLite_Record(activity, context);
        sqLite_record.insert(Id, medical_date, String.valueOf(medical_type), String.valueOf(medical_angle), String.valueOf(medical_frequency), finish_date, finish_time, spend_time);

    }//上傳復健記錄

    private void status_textview_clean_color() {
        connecting.setTextColor(Color.parseColor("#DDDDDD"));
        processing.setTextColor(Color.parseColor("#DDDDDD"));
        success.setTextColor(Color.parseColor("#DDDDDD"));
        failure.setTextColor(Color.parseColor("#DDDDDD"));
    }

    private void finish_one_dialog(String spend_time, String type_name) {
        textToSpeech.speak("此復建動作完成，是否要繼續進行復健?", TextToSpeech.QUEUE_FLUSH, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("此復建動作完成，是否要繼續進行復健?");
        alertDialog.setMessage("醫囑時間：" + medical_date + "\n" +
                "復健類型：" + type_name + "\n" +
                "復健內容：角度：" + medical_angle + "度　次數：" + medical_frequency + "次" + "\n\n" +
                "復健日期：" + finish_date + "\n" +
                "復健時間：" + finish_time + "\n" +
                "花費時間：" + spend_time + "秒");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                        mConnectedThread.cancel();
                        textToSpeech.shutdown();

                        Intent intent = new Intent();
                        intent.setClass(context, Select_type.class);
                        intent.putExtra("Id", Id);
                        intent.putExtra("type_status", type_status);
                        intent.putExtra("first_time", "0");
                        intent.putExtra("correction_status", "0");
                        activity.startActivity(intent);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "否",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mConnectedThread.cancel();
                        Intent intent = new Intent();
                        intent.setClass(context, Menu.class);
                        intent.putExtra("Id", Id);
                        activity.startActivity(intent);
                        activity.finish();//關閉activity
                    }
                });

        alertDialog.show();//示對話框
    }

    private void finish_all_dialog(String spend_time, String type_name) {
        textToSpeech.speak("所有復健已經完成", TextToSpeech.QUEUE_FLUSH, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("所有復健已經完成");
        alertDialog.setMessage("醫囑時間：" + medical_date + "\n" +
                "復健類型：" + type_name + "\n" +
                "復健內容：角度：" + medical_angle + "度　次數：" + medical_frequency + "次" + "\n\n" +
                "復健日期：" + finish_date + "\n" +
                "復健時間：" + finish_time + "\n" +
                "花費時間：" + spend_time + "秒");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "完成",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mConnectedThread.cancel();
                        textToSpeech.shutdown();

                        Intent intent = new Intent();
                        intent.setClass(context, Menu.class);
                        intent.putExtra("Id", Id);
                        activity.startActivity(intent);
                        activity.finish();//關閉activity
                    }
                });
        alertDialog.show();//示對話框
    }

}
