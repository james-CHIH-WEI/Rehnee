package com.example.rehnee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class General {
    private String StringURL;

    public String url(String name) {
        //StringURL = "http://10.20.18.121/AndroidStudio_php/Rehnee/";//Lab
        //StringURL = "http://192.168.0.100/AndroidStudio_php/Rehnee/";//住家
        //StringURL = "http://192.168.0.13/AndroidStudio_php/Rehnee/";//家
        StringURL = "http://192.168.43.33/AndroidStudio_php/Rehnee/";//手機
        if (name == "Change") {
            StringURL = StringURL + "Change.php";
        } else if (name == "Check_email") {
            StringURL = StringURL + "Check_email.php";
        } else if (name == "Send_mail") {
            StringURL = StringURL + "Send_mail.php";
        } else if (name == "Login") {
            StringURL = StringURL + "Login.php";
        } else if (name == "Medical_order") {
            StringURL = StringURL + "Medical_order.php";
        } else if (name == "Record") {
            StringURL = StringURL + "Record.php";
        } else if (name == "Upload") {
            StringURL = StringURL + "Upload.php";
        } else if (name == "Verification_code") {
            StringURL = StringURL + "Verification_code.php";
        } else if (name == "Token") {
            StringURL = StringURL + "Token.php";
        }else if (name == "Chat_send") {
            StringURL = StringURL + "Chat_send.php";
        }else if (name == "FAQ") {
            StringURL = StringURL + "FAQ.php";
        }
        return StringURL;
    }

    public boolean check_network(Activity activity) {//檢查網路是否可以使用
        boolean check = false;

        ConnectivityManager mConnectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();

        if (networkInfo != null)//有連線
        {
            if (networkInfo.isAvailable() == true) {//網路是可以用的
                check = true;
            }
        }
        return check;
    }//檢查網路是否可以使用


    /*---------------------------------------介面---------------------------------------*/
    public DrawerLayout toolbar(AppCompatActivity activity) {
        androidx.appcompat.widget.Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        return drawer;
    }

    public void set_view(Activity activity,String input_layout) {
        View rowView = null;
        activity.setContentView(R.layout.frame);
        RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.input_layout);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(input_layout=="Menu"){
            rowView = inflater.inflate(R.layout.activity_menu, null);
        }else if(input_layout=="Change"){
            rowView = inflater.inflate(R.layout.activity_change, null);
        }else if(input_layout=="Chat"){
            rowView = inflater.inflate(R.layout.activity_chat, null);
        }else if(input_layout=="Record"){
            rowView = inflater.inflate(R.layout.activity_record, null);
        }else if(input_layout=="FAQ"){
            rowView = inflater.inflate(R.layout.activity_faq, null);
        }

        relativeLayout.addView(rowView);
    }

    public boolean sidebar_click(Activity activity, MenuItem menuItem, String Id) {
        switch (menuItem.getItemId()) {
            case R.id.change:
                if (check_network(activity) == true) {//如果有網路
                    Intent intent = new Intent();
                    intent.setClass(activity, Change.class);
                    intent.putExtra("Id", Id);
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(activity, "抱歉! 沒有網路無法變更密碼", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_out:
                Intent intent = new Intent();
                intent.setClass(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
        }
        return true;
    }
}
