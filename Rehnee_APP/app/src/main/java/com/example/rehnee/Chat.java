package com.example.rehnee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String Id, content = "";
    private EditText send_content;
    private Button send;
    private LinearLayout message;
    private ScrollView scrollView;

    private SQLite_Chat sqLite_chat;

    private DrawerLayout drawer;
    private General general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        general = new General();
        general.set_view(Chat.this, "Chat");
        drawer = general.toolbar(this);

        findid();

        sqLite_chat = new SQLite_Chat(Chat.this);

        show_content();
        sendScroll();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        send.setOnClickListener(sendOnClick);
    }

    private void findid() {
        send_content = (EditText) findViewById(R.id.send_content);
        send = (Button) findViewById(R.id.send);
        message = (LinearLayout) findViewById(R.id.message);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }

    Button.OnClickListener sendOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            SimpleDateFormat sdFormat;
            String content_date, content_time;
            Date date = new Date();

            if (general.check_network(Chat.this) == true) {
                if (!send_content.getText().toString().equals("")) {
                    sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                    content_date = sdFormat.format(date);

                    sdFormat = new SimpleDateFormat("HH:mm:ss");
                    content_time = sdFormat.format(date);

                    AsyncTask_Chat_send asyncTask_chat_send = new AsyncTask_Chat_send(Id, send_content.getText().toString(), content_date, content_time);
                    asyncTask_chat_send.execute();

                    sqLite_chat.insert(Id, "2", send_content.getText().toString(), content_date, content_time);

                    add_right(send_content.getText().toString(), content_date, content_time);
                    sendScroll();

                    content = "";
                    send_content.setText("");
                } else {
                    Toast.makeText(Chat.this, "請輸入內容", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Chat.this, "沒有連接網路無法傳送訊息", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void sendScroll() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        }).start();
    }

    private void show_content() {
        String[][] values;
        int x;

        values = sqLite_chat.query(Id);
        for (x = 0; x < values.length; x++) {
            content = content + values[x][2];
            if (values[x][1].equals("2")) {//病人的內容
                add_right(content, values[x][3], values[x][4]);
            } else {//醫師的內容
                add_left(content, values[x][3], values[x][4]);
            }
            content = "";
        }


    }

    private void add_left(String content, String date, String time) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chat_left, null);
        TextView dr_Id = rowView.findViewById(R.id.dr_Id);
        TextView dr_content = rowView.findViewById(R.id.dr_content);
        TextView dr_time = rowView.findViewById(R.id.dr_time);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 30, 0);

        dr_content.setText(content);
        dr_time.setText(date + " " + time);

        message.addView(rowView, layoutParams);
    }

    public void add_right(String content, String date, String time) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chat_right, null);
        TextView p_content = rowView.findViewById(R.id.p_content);
        TextView p_time = rowView.findViewById(R.id.p_time);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 30, 0);

        p_content.setText(content);
        p_time.setText(date + " " + time);

        message.addView(rowView, layoutParams);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return general.sidebar_click(this, menuItem, Id);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//當按下返回健
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            Intent intent = new Intent();
            intent.setClass(Chat.this, Menu.class);
            intent.putExtra("Id", Id);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//當按下返回健
}
