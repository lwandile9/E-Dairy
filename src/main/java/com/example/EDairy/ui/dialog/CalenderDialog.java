package com.example.EDairy.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.example.EDairy.R;

public class CalenderDialog extends DialogFragment {
    private CalendarView calendarView;
    private final CalendarView.OnDateChangeListener listener;

    public CalenderDialog(CalendarView.OnDateChangeListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_calender,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calenderView);
        calendarView.setOnDateChangeListener(listener);
        MaterialButton saveButton = view.findViewById(R.id.saveButton);
    }

}
