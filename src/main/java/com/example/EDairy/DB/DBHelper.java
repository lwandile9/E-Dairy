package com.example.EDairy.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String NAME = "eDiaryDatabase";

    public DBHelper(Context context){
        super(context, NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateSqlStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getDeleteSqlStatement());
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }

    private String getCreateSqlStatement(){
        return "CREATE TABLE " + Schema.TABLE_NAME + " (" +
                Schema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Schema.COLUMN_TITLE + " TEXT," +
                Schema.COLUMN_DATE + " INTEGER," +
                Schema.COLUMN_SEMESTER_NUMBER + " INTEGER," +
                Schema.COLUMN_FUTURE_TASKS + " TEXT," +
                Schema.COLUMN_DESCRIPTION + " TEXT)";
    }

    private String getDeleteSqlStatement(){
        return "DROP TABLE IF EXISTS " + Schema.TABLE_NAME;
    }

    public static class Schema implements BaseColumns {
        public static final String TABLE_NAME = "bostonDairy";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_SEMESTER_NUMBER = "semesterNumber";
        public static final String COLUMN_FUTURE_TASKS = "futureTasks";
    }
}
