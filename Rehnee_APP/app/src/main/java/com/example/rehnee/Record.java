package com.example.rehnee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Record extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout value;
    private Button all_date, find_date, line_chart, last_button;
    private TextView no_value, temp_content;
    private ScrollView scrollView;
    private Spinner select_type;
    private String Id;
    private int status = 0, start = 0, now_select_type = 0;
    //private String[] type_name = {getString(R.string.type_1), getString(R.string.type_2), getString(R.string.type_3)};
    private String[] type_name = new String[3];
    private String[][] select_values;
    private DrawerLayout drawer;
    private General general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");

        general = new General();
        general.set_view(Record.this, "Record");
        drawer = general.toolbar(this);

        type_name[0] = getString(R.string.type_1);
        type_name[1] = getString(R.string.type_2);
        type_name[2] = getString(R.string.type_3);

        findid();

        init_spinner();

        select_values = sqlite_record_query();
        show(select_values);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        all_date.setOnClickListener(all_dateOnClick);
        find_date.setOnClickListener(find_dateOnClick);
        line_chart.setOnClickListener(line_chartOnClick);
        select_type.setOnItemSelectedListener(select_typeOnItemClick);
    }

    private void findid() {
        value = (LinearLayout) findViewById(R.id.value);
        all_date = (Button) findViewById(R.id.all_date);
        find_date = (Button) findViewById(R.id.find_date);
        line_chart = (Button) findViewById(R.id.line_chart);
        no_value = (TextView) findViewById(R.id.no_value);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        select_type = (Spinner) findViewById(R.id.select_type);
    }

    Button.OnClickListener all_dateOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            find_date.setText("特定日期");
            select_type.setSelection(0);
            select_values = sqlite_record_query();
            show(select_values);
        }
    };

    Button.OnClickListener find_dateOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(Record.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Date date = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//設定日期格式
                    try {
                        date = sdf.parse((year + "-" + (monthOfYear + 1) + "-" + dayOfMonth));//將字串格式轉乘日期格式
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    find_date.setText(sdf.format(date));//顯示日期

                    select_values = new String[0][0];
                    for (int x = 0; x < sqlite_record_query().length; x++) {
                        if (sqlite_record_query()[x][5].equals(find_date.getText().toString())) {
                            if (select_type.getSelectedItemId() == 0 | sqlite_record_query()[x][2].equals(String.valueOf(select_type.getSelectedItemId()))) {
                                select_values = Arrays.copyOf(select_values, select_values.length + 1);//把陣列長度加一
                                select_values[select_values.length - 1] = new String[8];
                                System.arraycopy(sqlite_record_query()[x], 0, select_values[select_values.length - 1], 0, sqlite_record_query()[x].length);
                            }
                        }
                    }

                    show(select_values);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();//在日期選擇器按下新的日期時

        }
    };

    Button.OnClickListener line_chartOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Record.this, Line_chart.class);
            intent.putExtra("Id", Id);
            startActivity(intent);
        }
    };

    private void init_spinner() {
        int x;
        int[] use_type = {0, 0, 0};

        String[][] values = sqlite_record_query();
        List<String> type = new ArrayList<>();
        type.add("所有種類");

        for (x = 0; x < values.length; x++) {
            if (values[x][2].equals("1")) {
                use_type[0] = 1;
            } else if (values[x][2].equals("2")) {
                use_type[1] = 1;
            } else if (values[x][2].equals("3")) {
                use_type[2] = 1;
            }
        }
        for (x = 0; x < use_type.length; x++) {
            if (use_type[x] == 1) {
                type.add(type_name[x]);
            }
        }

        ArrayAdapter<String> typeList = new ArrayAdapter<>(Record.this, android.R.layout.simple_spinner_dropdown_item, type);
        select_type.setAdapter(typeList);

    }

    private AdapterView.OnItemSelectedListener select_typeOnItemClick = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int now_select_type, long l) {
            int x, type_index = 0;
            String[][] values = sqlite_record_query();

            System.out.println(select_type.getSelectedItem().toString() + "    " + now_select_type);
            for (x = 0; x < type_name.length; x++) {
                if (type_name[x].equals(select_type.getSelectedItem().toString())) {
                    type_index = x + 1;
                    break;
                }
            }

            select_values = new String[0][0];
            for (x = 0; x <values.length; x++) {
                if (now_select_type == 0 | values[x][2].equals(String.valueOf(type_index))) {
                    if (sqlite_record_query()[x][5].equals(find_date.getText().toString()) | find_date.getText().toString().equals("特定日期")) {
                        select_values = Arrays.copyOf(select_values, select_values.length + 1);//把陣列長度加一
                        select_values[select_values.length - 1] = new String[8];
                        System.arraycopy(sqlite_record_query()[x], 0, select_values[select_values.length - 1], 0, sqlite_record_query()[x].length);
                    }
                }
            }
            show(select_values);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private String[][] sqlite_record_query() {
        String[][] values;
        SQLite_Record sqLite_record = new SQLite_Record(Record.this, Record.this);
        values = sqLite_record.query(Id);
        return values;
    }

    private void show(String[][] values) {
        int x;
        boolean last_one = false;
        value.removeAllViews();
        for (x = values.length - 1; x >= 0; x--) {
        //for (x = 0; x < values.length; x++) {
            if (x == 0) {
                last_one = true;
            }
            //(medical_date, medical_type, medical_angle, medical_frequency, finish_date, finish_time, spend_time)
            add_view(values[x][1], values[x][2], values[x][3], values[x][4], values[x][5], values[x][6], values[x][7], last_one);
        }

        if (select_values.length == 0) {
            no_value.setText("\n\n\n目前無資料");
        } else {
            no_value.setText("");
        }
    }

    private void add_view(String medical_date, String medical_type, String medical_angle, String medical_frequency, String finish_date, String finish_time, String spend_time, boolean last_one) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.record_data, null);
        Button r_title = rowView.findViewById(R.id.r_title);
        TextView r_content = rowView.findViewById(R.id.r_content);

        if (last_one == true) {//記住最後一個的ViewId
            last_button = rowView.findViewById(R.id.r_title);
        }

        if (status == 0) {
            r_title.setTextColor(Color.parseColor("#ffffff"));
            r_title.setBackgroundResource(R.drawable.button_blue_down);
            status = 1;
        } else {
            r_title.setTextColor(Color.parseColor("#4a596e"));
            r_title.setBackgroundResource(R.drawable.button_blue_on);
            status = 0;
        }
        r_content.setVisibility(View.GONE);

        medical_type = type_name[Integer.valueOf(medical_type) - 1];

        r_title.setText(finish_date);
        r_content.setText(Html.fromHtml("<b>►醫囑開立日期：</b>" + medical_date + "<br/>" +
                "<b>►復健類型：</b>" + medical_type + "<br/>" +
                "<b>►醫囑內容：</b>每次" + medical_angle + "度，重複做" + medical_frequency + "次" + "<br/>" +
                "<b>►復健完成日期：</b>" + finish_date + "<br/>" +
                "<b>►復健完成時間：</b>" + finish_time + "<br/>" +
                "<b>►復健總花費時間：</b>" + spend_time + "秒"));

        value.addView(rowView);
    }

    public void content_click(View v) {//這是監聽按鈕的程式
        if (start == 0) {//如果他是第一次按下
            start = 1;
            temp_content = ((View) v.getParent()).findViewById(R.id.r_content);
            temp_content.setVisibility(View.VISIBLE);
        } else {
            if (temp_content == ((View) v.getParent()).findViewById(R.id.r_content)) {//如果按下與上次同一個
                if (temp_content.getVisibility() == View.VISIBLE) {
                    temp_content.setVisibility(View.GONE);
                } else {
                    temp_content.setVisibility(View.VISIBLE);
                }

            } else {//如果按下與上次不同一個
                temp_content.setVisibility(View.GONE);
                temp_content = ((View) v.getParent()).findViewById(R.id.r_content);
                temp_content.setVisibility(View.VISIBLE);
            }
        }

        if (last_button == ((View) v.getParent()).findViewById(R.id.r_title)) {
            sendScroll();
        }


    }

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
