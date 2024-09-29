package com.example.EDairy.ui.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.example.EDairy.R;
import com.example.EDairy.DB.ValuesBuilder;
import com.example.EDairy.ui.dialog.CalenderDialog;
import com.example.EDairy.ui.utils.TextValidator;
import com.example.EDairy.ui.viewmodels.NoteViewModel;

import java.util.Calendar;

public class CreateFragment extends Fragment implements CalendarView.OnDateChangeListener {
    private TextInputEditText topic,description, tasks;
    private AppCompatSpinner semester;
    private MaterialTextView dateTextView;
    private NoteViewModel viewModel;
    private Calendar calendar;

    public CreateFragment() {
        super(R.layout.fragment_create);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NoteViewModel.Factory(requireActivity()).getNoteViewModel();
        calendar = Calendar.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        initViews();
    }

    private void findViews(){
        dateTextView = getView().findViewById(R.id.date);
        topic = getView().findViewById(R.id.topicInput);
        description = getView().findViewById(R.id.descriptionInput);
        tasks = getView().findViewById(R.id.taskInput);
        semester = getView().findViewById(R.id.semesterSpinner);
    }

    private void initViews(){
        dateTextView.setOnClickListener(v-> new CalenderDialog(this).show(getFragmentManager(), ""));
        dateTextView.setText(calendar.getTime().toString());
        getView().findViewById(R.id.createButton).setOnClickListener(v->{
            if (TextValidator.isTextNotNull(topic.getText())
                    && TextValidator.isTextNotNull(description.getText())
                    && TextValidator.isTextNotNull(tasks.getText())){
                ContentValues values = new ValuesBuilder.Builder()
                        .setDate((int)calendar.getTimeInMillis())
                        .setTitle(topic.getText().toString())
                        .setDescription(description.getText().toString())
                        .setSemesterNumber(semester.getSelectedItemPosition())
                        .setFutureTasks(tasks.getText().toString())
                        .build();
                viewModel.create(values);
                topic.setText(null);
                description.setText(null);
                tasks.setText(null);
                semester.setSelection(0);
            }else
                Snackbar.make(getView(), "Some values are missing",Snackbar.LENGTH_LONG).show();
        });
    }


    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        calendar.set(year,month,dayOfMonth);
        dateTextView.setText(calendar.getTime().toString());
    }
}
