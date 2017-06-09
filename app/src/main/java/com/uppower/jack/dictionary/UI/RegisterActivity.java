package com.uppower.jack.dictionary.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uppower.jack.dictionary.R;
import com.uppower.jack.dictionary.http.OkHttp;
import com.uppower.jack.dictionary.javabeans.DictEntity;
import com.uppower.jack.dictionary.javabeans.UserEntity;

import java.io.IOException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private Button loginButton;
    private EditText pwdEidt;
    private EditText userEdit;
    private TextView textview;
    private OkHttp okHttp = new OkHttp();
    private String url = "http://192.168.1.109:8080/register";
    private Handler myHandler;
    public static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
        sContext = getApplicationContext();

        myHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 200:
                        if (msg.getData().get("registerCode").equals("0"))
                            textview.setText("注册失败");
                        if (msg.getData().get("registerCode").equals("1"))
                            textview.setText("注册成功");
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initEvent() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String registerCode = okHttp.registerRequest(url, new UserEntity(0,
                                    userEdit.getText().toString(), pwdEidt.getText().toString()));
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("registerCode", registerCode);
                            Message message = new Message();
                            message.setData(bundle);
                            message.what = 200;
                            myHandler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this ,LonginActivity.class));
            }
        });
    }

    private void initView() {
        registerButton = (Button) findViewById(R.id.registerButton);
        pwdEidt = (EditText) findViewById(R.id.pwdEdit);
        userEdit = (EditText) findViewById(R.id.userEdit);
        textview = (TextView) findViewById(R.id.textview);
        loginButton = (Button) findViewById(R.id.loginButton);
    }
}
