package com.example.dailycost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class AwaitActivity extends AppCompatActivity {

    private TextView text;
    int time = 3;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_await);

        text = findViewById(R.id.txt_count);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (time>0){
                    text.setText(time -- + " S");
                    handler.sendEmptyMessageDelayed(100,1000);
                }else{
                    Intent intent = new Intent(AwaitActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.sendEmptyMessage(100);
    }
}
