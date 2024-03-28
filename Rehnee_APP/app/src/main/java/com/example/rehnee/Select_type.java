package com.example.rehnee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Select_type extends AppCompatActivity {

    private Button action1, action2, action3, determine;
    private TextView indication_text;
    private ImageView type_picture;
    private String Id, first_time, medical_date, select = "0", correction_status;
    private String[][] contents = new String[0][3];
    private int[] type_status = new int[3];
    private String[] type_indication =new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");
        first_time = intent.getStringExtra("first_time");
        type_status = intent.getIntArrayExtra("type_status");
        correction_status = intent.getStringExtra("correction_status");

        findid();

        type_indication[0]=getString(R.string.type_1_indication);
        type_indication[1]=getString(R.string.type_2_indication);
        type_indication[2]=getString(R.string.type_3_indication);

        if (first_time.equals("1")) {
            type_status = new int[3];
        }
        sqlite_medical_order_query();


        final Button[] action = {action1, action2, action3};
        boolean first_one = true;
        for (int x = 0; x < type_status.length; x++) {
            if (type_status[x] == 1) {
                action[x].setVisibility(View.VISIBLE);
                indication_text.setText(type_indication[x]);
                if (first_one == true) {
                    action[x].setTextColor(Color.parseColor("#FF0000"));
                    Handler handler = new Handler();
                    final int finalX = x;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            action[finalX].performClick();
                        }
                    });
                    first_one = false;
                }
            } else if (type_status[x] == 2) {
                action[x].setVisibility(View.VISIBLE);
                action[x].setText(action[x].getText().toString() + "(已完成)");
                action[x].setEnabled(false);
            }
        }

        action1.setOnClickListener(action1OnClick);
        action2.setOnClickListener(action2OnClick);
        action3.setOnClickListener(action3OnClick);
        determine.setOnClickListener(determineOnClick);
    }

    private void findid() {
        action1 = (Button) findViewById(R.id.action1);
        action2 = (Button) findViewById(R.id.action2);
        action3 = (Button) findViewById(R.id.action3);
        indication_text=(TextView)findViewById(R.id.indication_text);
        determine = (Button) findViewById(R.id.determine);
        type_picture = (ImageView) findViewById(R.id.type_picture);
    }

    private Button.OnClickListener action1OnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            type_picture.setImageResource(R.drawable.action1);
            set_all_button_color_back();
            action1.setTextColor(Color.parseColor("#FF0000"));
            select = "1";
            indication_text.setText(type_indication[0]);
        }
    };

    private Button.OnClickListener action2OnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            type_picture.setImageResource(R.drawable.action2);
            set_all_button_color_back();
            action2.setTextColor(Color.parseColor("#FF0000"));
            select = "2";
            indication_text.setText(type_indication[1]);
        }
    };

    private Button.OnClickListener action3OnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            type_picture.setImageResource(R.drawable.action3);
            set_all_button_color_back();
            action3.setTextColor(Color.parseColor("#FF0000"));
            select = "3";
            indication_text.setText(type_indication[2]);
        }
    };

    private Button.OnClickListener determineOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (select.equals("0")) {
                Toast.makeText(Select_type.this, "請選擇復健類型", Toast.LENGTH_SHORT).show();
            } else {
                for (int x = 0; x < contents.length; x++) {
                    if (contents[x][0].equals(select)) {
                        goto_Rehabilitation(contents[x][0], contents[x][1], contents[x][2]);
                        break;
                    }
                }
            }

        }
    };

    private void sqlite_medical_order_query() {//(手機資料庫) 查詢復健醫囑
        String[][] values;
        int x;
        SQLite_Medical_order sqLite_medical_order = new SQLite_Medical_order(Select_type.this, Select_type.this);
        values = sqLite_medical_order.query(Id);

        medical_date = values[values.length - 1][1];
        for (x = 0; x < values[values.length - 1][2].split("-").length; x++) {
            contents = Arrays.copyOf(contents, contents.length + 1);//把陣列長度加一
            contents[x] = values[values.length - 1][2].split("-")[x].split(",");
            if (first_time.equals("1")) {
                type_status[Integer.valueOf(contents[x][0]) - 1] = 1;
            }
        }
    }//(手機資料庫) 查詢復健醫囑

    private void goto_Rehabilitation(String type, String angle, String frequency) {
        Intent intent = new Intent();
        intent.setClass(Select_type.this, Rehabilitation.class);
        intent.putExtra("Id", Id);
        intent.putExtra("type_status", type_status);
        intent.putExtra("medical_date", medical_date);
        intent.putExtra("type", type);
        intent.putExtra("angle", angle);
        intent.putExtra("frequency", frequency);
        intent.putExtra("correction_status", correction_status);
        startActivity(intent);
    }

    private void set_all_button_color_back() {
        if (action1.isEnabled()) {
            action1.setTextColor(Color.parseColor("#000000"));
        }
        if (action2.isEnabled()) {
            action2.setTextColor(Color.parseColor("#000000"));
        }
        if (action3.isEnabled()) {
            action3.setTextColor(Color.parseColor("#000000"));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//當按下返回健
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }//當按下返回健

    public void ConfirmExit() {//退出確認
        AlertDialog alertDialog = new AlertDialog.Builder(Select_type.this).create();
        alertDialog.setTitle("離開");
        alertDialog.setMessage("確定要離開?");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(Select_type.this, Menu.class);
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
    }//退出確認

}
