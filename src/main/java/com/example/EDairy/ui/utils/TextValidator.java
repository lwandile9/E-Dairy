package com.example.EDairy.ui.utils;

import android.text.Editable;

import androidx.annotation.Nullable;

public class TextValidator {

    public static boolean isTextNotNull(@Nullable Editable editable){
        if (editable == null)
            return false;
        return !editable.toString().isEmpty();
    }
}
