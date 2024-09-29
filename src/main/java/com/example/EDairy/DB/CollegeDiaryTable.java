package com.example.EDairy.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.EDairy.model.Note;

import java.util.ArrayList;
import java.util.List;

public class CollegeDiaryTable {
    private SQLiteDatabase database;

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void insert(@NonNull ContentValues values){
        database.insert(DBHelper.Schema.TABLE_NAME, null, values);
    }

    public void update(int nodeId,@NonNull ContentValues values){
        String selection = DBHelper.Schema._ID + " =? ";
        String[] selectionArgs = {Long.toString(nodeId)};
        database.update(DBHelper.Schema.TABLE_NAME,values,selection,selectionArgs);
    }

    public void delete(int noteId){
        String selection = DBHelper.Schema._ID + " =? ";
        String[] selectionArgs = {Long.toString(noteId)};
        database.delete(DBHelper.Schema.TABLE_NAME,selection, selectionArgs);
    }

    public List<Note> read(){
        List<Note> noteList = new ArrayList<>();
        String[] projection = {DBHelper.Schema._ID,DBHelper.Schema.COLUMN_TITLE,DBHelper.Schema.COLUMN_DESCRIPTION,
                DBHelper.Schema.COLUMN_SEMESTER_NUMBER,DBHelper.Schema.COLUMN_FUTURE_TASKS,DBHelper.Schema.COLUMN_DATE,};
        Cursor cursor = database.query(DBHelper.Schema.TABLE_NAME,projection, null, null,
                null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(DBHelper.Schema._ID));
                    String topic = cursor.getString(cursor.getColumnIndex(DBHelper.Schema.COLUMN_TITLE));
                    String desc = cursor.getString(cursor.getColumnIndex(DBHelper.Schema.COLUMN_DESCRIPTION));
                    int semesterNumber = cursor.getInt(cursor.getColumnIndex(DBHelper.Schema.COLUMN_SEMESTER_NUMBER));
                    String tasks = cursor.getString(cursor.getColumnIndex(DBHelper.Schema.COLUMN_FUTURE_TASKS));
                    int date = cursor.getInt(cursor.getColumnIndex(DBHelper.Schema.COLUMN_DATE));
                    Note note = new Note(id,topic, desc,date, semesterNumber,tasks);
                    noteList.add(note);
                }while (cursor.moveToNext());
                cursor.close();
            }
        }
        return noteList;
    }
}
