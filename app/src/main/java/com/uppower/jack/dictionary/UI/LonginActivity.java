package com.uppower.jack.dictionary.UI;

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

import com.uppower.jack.dictionary.R;
import com.uppower.jack.dictionary.http.OkHttp;
import com.uppower.jack.dictionary.javabeans.DictEntity;
import com.uppower.jack.dictionary.javabeans.UserEntity;

import java.io.IOException;

public class LonginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText userEdit;
    private EditText pwdEdit;
    private Button searchButton;
    private TextView textview;
    private Handler myHandler;
    private Button registerButton;
    private OkHttp okHttp = new OkHttp();
    private String url = "http://192.168.1.109:8080/login";
    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);
        initView();
        initEvent();
        myHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 200:
                        if (msg.getData().get("registerCode").equals("0"))
                            textview.setText("登陆失败");
                        if (msg.getData().get("registerCode").equals("1")) {
                            textview.setText("登陆成功");
                            startActivity(new Intent(LonginActivity.this, WordSearchActivity.class));
                        }
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initEvent() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String registerCode = null;
                        try {
                            registerCode = okHttp.registerRequest(url, new UserEntity(0,
                                    userEdit.getText().toString(), pwdEdit.getText().toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("registerCode", registerCode);
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = 200;
                        myHandler.sendMessage(message);
                    }
                }).start();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LonginActivity.this, RegisterActivity.class));
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(code);
                if (code == 1) {
                    startActivity(new Intent(LonginActivity.this, WordSearchActivity.class));
                }
            }
        });
    }


    private void initView() {
        loginButton = (Button) findViewById(R.id.loginButton);
        userEdit = (EditText) findViewById(R.id.userEdit);
        pwdEdit = (EditText) findViewById(R.id.pwdEdit);
        textview = (TextView) findViewById(R.id.textview);
        registerButton = (Button) findViewById(R.id.registerButton);
        searchButton = (Button) findViewById(R.id.search_word);
    }
}
