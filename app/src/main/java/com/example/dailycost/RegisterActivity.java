package com.example.dailycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText login_name;
    private EditText login_pwd;
    private Button login_ok;
    private Button login_weChat;
    private ImageButton login_eye;
    boolean showPwd = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intViews();
    }

    private void intViews() {
        login_name = findViewById(R.id.login_name);
        login_pwd = findViewById(R.id.login_pwd);
        login_ok = findViewById(R.id.login_ok);
        login_eye = findViewById(R.id.login_eye);
        login_weChat = findViewById(R.id.login_weChat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_eye:
                if (!showPwd) {
                    showPwd = true;
                    login_eye.setImageResource(R.mipmap.ih_show);
                    //显示密码
                    login_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    showPwd = false;
                    login_eye.setImageResource(R.mipmap.ih_hide);
                    //隐藏密码
                    login_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.login_ok:
                String name = login_name.getText().toString();
                String pwd = login_pwd.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
                } else if (pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("flag", 1);
                    intent.putExtra("name", name);
                    intent.setClass(RegisterActivity.this, SettingActivity.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
            case R.id.reg_error:
                Intent intent = new Intent();
                intent.putExtra("flag", 0);
                intent.setClass(RegisterActivity.this, SettingActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}


