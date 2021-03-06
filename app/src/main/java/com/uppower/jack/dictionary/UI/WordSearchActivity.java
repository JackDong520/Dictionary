package com.uppower.jack.dictionary.UI;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uppower.jack.dictionary.R;
import com.uppower.jack.dictionary.http.OkHttp;
import com.uppower.jack.dictionary.javabeans.DictEntity;

import org.junit.Test;

import java.io.IOException;

public class WordSearchActivity extends AppCompatActivity {


    private Button searchButton;
    private Button loginButton;
    private EditText searchEdit;
    private Handler myHandler;
    private String url = "http://192.168.1.109:8080/getWord"; //接受服务的restful接口
    private TextView meaningText;
    private Gson gson;
    private Button toRegisterButton;

    private OkHttp okHttp = new OkHttp();

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
                        DictEntity dictEntity = (DictEntity) msg.getData().get("word");
                        meaningText.setText(dictEntity.getMeaning());
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DictEntity dictEntity = gson.fromJson(okHttp.sendWordPostRequest(url, word), DictEntity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("word", dictEntity);
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
        meaningText = (TextView) findViewById(R.id.wordMeaning);

    }
    @Test
    public void test() throws IOException {
        DictEntity dictEntity =gson.fromJson(new OkHttp().sendWordPostRequest(url,"take") ,DictEntity.class) ;

        System.out.println(dictEntity.getMeaning());
      //  System.out.println(gson.fromJson(okHttp.sendWordPostRequest(url, "take"), DictEntity.class).getMeaning());
    }


}