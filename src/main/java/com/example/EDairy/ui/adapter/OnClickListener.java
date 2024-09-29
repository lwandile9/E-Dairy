package com.example.EDairy.ui.adapter;

import androidx.annotation.NonNull;

import com.example.EDairy.model.Note;

public interface OnClickListener {

    void onClick(@NonNull Note note);

    void onDeleteClick(@NonNull Note note);
}
