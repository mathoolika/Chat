package com.mathoolikavasigaran.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button connectBtn;
    Button sendBtn;
    Button searchBtn;
    ListView listView;
    TextView message;
    TextView status;
    EditText space;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI() {
        connectBtn = findViewById(R.id.connect);
        sendBtn = findViewById(R.id.send);
        searchBtn = findViewById(R.id.search);
        listView = findViewById(R.id.list);
        message = findViewById(R.id.message);
        status = findViewById(R.id.status);
        space = findViewById(R.id.writespace);


    }
}