package com.uppower.jack.dictionary.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.uppower.jack.dictionary.R;

public class WordSearchActivity extends AppCompatActivity {


    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        searchButton = (Button) findViewById(R.id.search_button);
    }
}
