package com.example.EDairy.DB;

import android.content.ContentValues;

import androidx.annotation.NonNull;

import com.example.EDairy.model.Note;

import static com.example.EDairy.DB.DBHelper.*;

public class ValuesBuilder {
    private ValuesBuilder(){
    }
    public static class Builder{
        private final ContentValues contentValues;

        public Builder(@NonNull Note note){
            contentValues = new ContentValues();
            contentValues.put(Schema.COLUMN_TITLE, note.getTitle());
            contentValues.put(Schema.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(Schema.COLUMN_DATE, note.getDate());
            contentValues.put(Schema.COLUMN_SEMESTER_NUMBER, note.getSemesterNumber());
            contentValues.put(Schema.COLUMN_FUTURE_TASKS, note.getFutureTasks());
        }

        public Builder() {
            contentValues = new ContentValues();
        }

        public Builder setTitle(@NonNull String title){
            contentValues.put(Schema.COLUMN_TITLE, title);
            return this;
        }

        public Builder setDescription(@NonNull String description){
            contentValues.put(Schema.COLUMN_DESCRIPTION, description);
            return this;
        }

        public Builder setDate(int date){
            contentValues.put(Schema.COLUMN_DATE,date);
            return this;
        }

        public Builder setFutureTasks(@NonNull String futureTasks){
            contentValues.put(Schema.COLUMN_FUTURE_TASKS, futureTasks);
            return this;
        }

        public Builder setSemesterNumber(int number){
            contentValues.put(Schema.COLUMN_SEMESTER_NUMBER,number);
            return this;
        }

        public ContentValues build(){
            return contentValues;
        }
    }
}
