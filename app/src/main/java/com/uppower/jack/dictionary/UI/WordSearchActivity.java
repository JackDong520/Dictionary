package com.uppower.jack.dictionary.UI;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uppower.jack.dictionary.R;
import com.uppower.jack.dictionary.http.OkHttp;
import com.uppower.jack.dictionary.javabeans.WordsEntity;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import okhttp3.OkHttpClient;

public class WordSearchActivity extends AppCompatActivity {


    private Button searchButton;
    private EditText searchEdit;
    private Handler myHandler;
    private String url = "http://192.168.6.141:8080/getWord"; //接受服务的restful接口
    private TextView textView;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addEvent();
        initUtil();
        myHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 200:
                        WordsEntity wordsEntity = (WordsEntity) msg.getData().get("word");
                        System.out.println(wordsEntity.toString());
                        textView.setText(wordsEntity.getWord().toString());
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initUtil() {
        gson = new Gson();
    }

    private void addEvent() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String word = searchEdit.getText().toString();
                System.out.println("xxx");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttp okHttp = new OkHttp();
                        try {
                            System.out.println("进来了");
                            WordsEntity wordEntity = gson.fromJson(okHttp.sendPostRequest(url, word), WordsEntity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("word", wordEntity);
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
    }

    private void initView() {
        searchButton = (Button) findViewById(R.id.search_button);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        textView = (TextView) findViewById(R.id.textView);
    }
}