package com.example.rehnee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Forget_password extends AppCompatActivity {

    private EditText forget_id, forget_email, verification;
    private Button forget_email_send, forget_send, forget_back;
    private String Id, email, verification_code;

    private EditText forget_password, forget_password_check;
    private Button forget_send_new, forget_back_new;
    private String password, password_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        findid();
        verification.setEnabled(false);
        forget_send.setEnabled(false);

        forget_email_send.setOnClickListener(forget_email_sendOnClick);
        forget_send.setOnClickListener(forget_sendOnClick);
        forget_back.setOnClickListener(forget_backOnClick);
    }

    private void findid() {
        forget_id = (EditText) findViewById(R.id.forget_id);
        forget_email = (EditText) findViewById(R.id.forget_email);
        verification = (EditText) findViewById(R.id.verification);
        forget_email_send = (Button) findViewById(R.id.forget_email_send);
        forget_send = (Button) findViewById(R.id.forget_send);
        forget_back = (Button) findViewById(R.id.forget_back);
    }

    /*--------------------------------------畫面一--------------------------------------*/
    Button.OnClickListener forget_email_sendOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (forget_id.getText().toString().matches("") || forget_email.getText().toString().matches("")) {
                Toast.makeText(Forget_password.this, "請填寫完整", Toast.LENGTH_SHORT).show();
            } else {
                Id = forget_id.getText().toString();
                email = forget_email.getText().toString();
                AsyncTask_Send_mail asyncTask_send_mail =
                        new AsyncTask_Send_mail(Forget_password.this, Forget_password.this, Id, email);
                asyncTask_send_mail.execute();
            }
        }
    };

    Button.OnClickListener forget_sendOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String result;
            if (verification.getText().toString().matches("")) {
                Toast.makeText(Forget_password.this, "請填寫完整", Toast.LENGTH_SHORT).show();
            } else {

                verification_code = verification.getText().toString();
                AsyncTask_Verification_code asyncTask_verification_code =
                        new AsyncTask_Verification_code(Forget_password.this, Forget_password.this, Id, verification_code);
                try {
                    result = asyncTask_verification_code.execute().get();
                    if (result.equals("1")) {
                        setContentView(R.layout.activity_forget_password_new);
                        Toast.makeText(Forget_password.this, "請輸入新密碼", Toast.LENGTH_SHORT).show();
                        findid_new();

                        forget_send_new.setOnClickListener(forget_send_newOnClick);
                        forget_back_new.setOnClickListener(forget_back_newOnClick);

                    } else {
                        Toast.makeText(Forget_password.this, "驗證碼錯誤", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    };

    Button.OnClickListener forget_backOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            goto_MainActivity();
        }
    };
    /*--------------------------------------畫面二--------------------------------------*/

    private void findid_new() {
        forget_password = (EditText) findViewById(R.id.forget_password);
        forget_password_check = (EditText) findViewById(R.id.forget_password_check);
        forget_send_new = (Button) findViewById(R.id.forget_send_new);
        forget_back_new = (Button) findViewById(R.id.forget_back_new);
    }

    Button.OnClickListener forget_send_newOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            password = forget_password.getText().toString();
            password_check = forget_password_check.getText().toString();
            if (password.equals(password_check)) {
                AsyncTask_Change asyncTask_change = new AsyncTask_Change(Forget_password.this, Id, password);
                asyncTask_change.execute();
                Toast.makeText(Forget_password.this, "密碼更改成功", Toast.LENGTH_SHORT).show();
                goto_MainActivity();
            } else {
                Toast.makeText(Forget_password.this, "(新密碼)  與  (密碼確認)  不一致", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Button.OnClickListener forget_back_newOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            goto_MainActivity();
        }
    };

    private void goto_MainActivity() {
        Intent intent = new Intent();
        intent.setClass(Forget_password.this, MainActivity.class);
        startActivity(intent);
    }


}
