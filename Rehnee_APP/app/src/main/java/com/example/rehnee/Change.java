package com.example.rehnee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Change extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText Old_password, New_password, New_password_check;
    private Button change_determine, change_back;

    private String Id;

    private General general;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        general = new General();
        general.set_view(Change.this, "Change");
        drawer = general.toolbar(this);

        findid();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        change_determine.setOnClickListener(change_determineOnClick);
        change_back.setOnClickListener(change_backOnClick);
    }

    private void findid() {
        Old_password = (EditText) findViewById(R.id.Old_password);
        New_password = (EditText) findViewById(R.id.New_password);
        New_password_check = (EditText) findViewById(R.id.New_password_check);
        change_determine = (Button) findViewById(R.id.change_determine);
        change_back = (Button) findViewById(R.id.change_back);
    }

    Button.OnClickListener change_determineOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            check_password_update();
        }
    };

    Button.OnClickListener change_backOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            goto_Menu();
            Toast.makeText(Change.this, "取消", Toast.LENGTH_SHORT).show();
        }
    };

    private void goto_Menu() {
        Intent intent = new Intent();
        intent.setClass(Change.this, Menu.class);
        intent.putExtra("Id", Id);
        startActivity(intent);
    }


    private void check_password_update() {
        String[][] values;
        String old_password, new_password, new_password_check;
        int x;

        old_password = Old_password.getText().toString();
        new_password = New_password.getText().toString();
        new_password_check = New_password_check.getText().toString();

        SQLite_Patient sqLite_patient = new SQLite_Patient(Change.this, Change.this);

        values = sqLite_patient.query(Id);

        if (Old_password.getText().toString().matches("") || New_password.getText().toString().matches("") || New_password_check.getText().toString().matches("")) {
            Toast.makeText(Change.this, "請填寫完整", Toast.LENGTH_SHORT).show();
        } else {
            if (new_password.equals(new_password_check)) {
                for (x = 0; x < values.length; x++) {
                    if (values[x][0].equals(Id) & values[x][1].equals(old_password)) {
                        sqLite_patient.update(Id, new_password);
                        AsyncTask_Change asyncTask_change = new AsyncTask_Change(Change.this, Id, new_password);
                        asyncTask_change.execute();
                        Toast.makeText(Change.this, "密碼更改成功", Toast.LENGTH_SHORT).show();
                        goto_Menu();
                        break;
                    }
                }
                if (x == values.length) {//如果SQLite裡面沒有這筆帳號密碼
                    Toast.makeText(Change.this, "(舊帳號)錯誤", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Change.this, "(新密碼)  與  (密碼確認)  不一致", Toast.LENGTH_SHORT).show();
            }
        }


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
        switch (menuItem.getItemId()) {
            case R.id.change:
                if (general.check_network(Change.this) == true) {//如果有網路
                    Intent intent = new Intent();
                    intent.setClass(this, Change.class);
                    intent.putExtra("Id", Id);
                   startActivity(intent);
                } else {
                    Toast.makeText(this, "抱歉! 沒有網路無法變更密碼", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_out:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
