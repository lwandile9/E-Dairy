package com.example.EDairy;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.EDairy.DB.DBHelper;
import com.example.EDairy.DB.DBHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteApplication extends Application {
    private ExecutorService executorService;
    private SQLiteDatabase liteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(4);
        initDatabase();
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    private void initDatabase(){
        if (liteDatabase == null){
            executorService.execute(()->{
                DBHelper dbHelper = new DBHelper(this);
                liteDatabase = dbHelper.getWritableDatabase();

            });
        }
    }

    public SQLiteDatabase getLiteDatabase(){
        return liteDatabase;
    }

}
