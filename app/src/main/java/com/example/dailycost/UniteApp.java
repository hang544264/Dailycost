package com.example.dailycost;

import android.app.Application;

import db.DBManager;

//  表示全局应用的类

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        初始化数据对象
        DBManager.initDB(getApplicationContext());
    }
}
