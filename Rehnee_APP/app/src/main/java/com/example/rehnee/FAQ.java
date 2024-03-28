package com.example.rehnee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class FAQ extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String Id;
    private int start = 0;

    private Button  temp_title;
    private TextView temp_content;
    private LinearLayout operating, problem;
    private DrawerLayout drawer;
    private General general;
    private SQLite_FAQ sqLite_faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        general = new General();
        general.set_view(FAQ.this, "FAQ");
        drawer = general.toolbar(this);

        sqLite_faq = new SQLite_FAQ(FAQ.this);

        findid();
        show();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void findid() {
        operating = (LinearLayout) findViewById(R.id.operating);
        problem = (LinearLayout) findViewById(R.id.problem);
    }

    public void show() {
        String[][] values;
        int x;
        values = sqLite_faq.query();
        for (x = 0; x < values.length; x++) {
            if (values[x][0].equals("1")) {
                add_view_operating(values[x][1], values[x][2].replace("@#","\n"));
            } else if (values[x][0].equals("2")) {
                add_view_problem(values[x][1], values[x][2].replace("@#","\n"));
            }
        }
    }

    private void add_view_operating(String title, String content) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.faq_data, null);
        Button faq_title = rowView.findViewById(R.id.faq_title);
        TextView faq_content = rowView.findViewById(R.id.faq_content);

        faq_content.setVisibility(View.GONE);

        faq_title.setText("▶ " + title);
        faq_content.setText(content);

        operating.addView(rowView);
    }

    private void add_view_problem(String title, String content) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.faq_data, null);
        Button faq_title = rowView.findViewById(R.id.faq_title);
        TextView faq_content = rowView.findViewById(R.id.faq_content);

        faq_content.setVisibility(View.GONE);

        faq_title.setText("▶ " + title);
        faq_content.setText(content);

        problem.addView(rowView);
    }

    public void content_click(View v) {
        if (start == 0) {//如果他是第一次按下
            start = 1;
            temp_title = ((View) v.getParent()).findViewById(R.id.faq_title);
            temp_title.setText("▼ " + temp_title.getText().toString().substring(2));
            temp_content = ((View) v.getParent()).findViewById(R.id.faq_content);
            temp_content.setVisibility(View.VISIBLE);
        } else {
            if (temp_content == ((View) v.getParent()).findViewById(R.id.faq_content)) {//如果按下與上次同一個
                if (temp_content.getVisibility() == View.VISIBLE) {
                    temp_title = ((View) v.getParent()).findViewById(R.id.faq_title);
                    temp_title.setText("▶ " + temp_title.getText().toString().substring(2));
                    temp_content.setVisibility(View.GONE);
                } else {
                    temp_title = ((View) v.getParent()).findViewById(R.id.faq_title);
                    temp_title.setText("▼ " + temp_title.getText().toString().substring(2));
                    temp_content.setVisibility(View.VISIBLE);
                }

            } else {//如果按下與上次不同一個
                temp_title.setText("▶ " + temp_title.getText().toString().substring(2));
                temp_content.setVisibility(View.GONE);


                temp_title = ((View) v.getParent()).findViewById(R.id.faq_title);
                temp_title.setText("▼ " + temp_title.getText().toString().substring(2));
                temp_content = ((View) v.getParent()).findViewById(R.id.faq_content);
                temp_content.setVisibility(View.VISIBLE);
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
        return general.sidebar_click(this, menuItem, Id);
    }
}
