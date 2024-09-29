package com.example.EDairy.ui.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class Preferences {
    private final SharedPreferences preferences;
    public Preferences(Context context) {
        preferences = context.getSharedPreferences("Notes", Context.MODE_PRIVATE);
    }

    public void setThemeMode(int mode){
        preferences.edit().putInt("theme",mode ).apply();
    }

    public int getThemeMode(){
        return preferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_NO);
    }

}
