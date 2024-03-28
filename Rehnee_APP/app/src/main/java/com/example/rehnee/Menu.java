package com.example.rehnee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button Rehabilitation, Record, Chat,FAQ;
    private String Id;
    private ImageView logo;

    private DrawerLayout drawer;
    private General general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        general = new General();
        general.set_view(Menu.this, "Menu");
        drawer = general.toolbar(this);


        findid();
        logo.bringToFront();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Rehabilitation.setOnClickListener(RehabilitationOnClick);
        Record.setOnClickListener(RecordOnClick);
        Chat.setOnClickListener(ChatOnClick);
        FAQ.setOnClickListener(FAQOnClick);
    }

    private void findid() {
        Rehabilitation = (Button) findViewById(R.id.Rehabilitation);
        Record = (Button) findViewById(R.id.Record);
        Chat = (Button) findViewById(R.id.Chat);
        FAQ = (Button) findViewById(R.id.FAQ);
        logo = (ImageView) findViewById(R.id.logo);
    }

    Button.OnClickListener RehabilitationOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Menu.this, Select_type.class);
            intent.putExtra("Id", Id);
            intent.putExtra("first_time", "1");
            startActivity(intent);
        }
    };

    Button.OnClickListener RecordOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Menu.this, Record.class);
            intent.putExtra("Id", Id);
            startActivity(intent);
        }
    };

    Button.OnClickListener ChatOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Menu.this, Chat.class);
            intent.putExtra("Id", Id);
            startActivity(intent);
        }
    };

    Button.OnClickListener FAQOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Menu.this, FAQ.class);
            startActivity(intent);
        }
    };


    public boolean onKeyDown(int keyCode, KeyEvent event) {//當按下返回健
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//當按下返回健

    public void ConfirmExit() {//退出確認
        AlertDialog alertDialog = new AlertDialog.Builder(Menu.this).create();
        alertDialog.setTitle("登出");
        alertDialog.setMessage("確定要登出?");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(Menu.this, MainActivity.class);
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
    }//退出確認

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
}
