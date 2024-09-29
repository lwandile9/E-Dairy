package com.example.EDairy.ui.dialog;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.example.EDairy.R;
import com.example.EDairy.DB.ValuesBuilder;
import com.example.EDairy.model.Note;
import com.example.EDairy.ui.utils.TextValidator;
import com.example.EDairy.ui.viewmodels.NoteViewModel;

import java.util.Calendar;

public class UpdateDialog extends BottomSheetDialogFragment implements CalendarView.OnDateChangeListener {
    private  Note note;
    private TextInputEditText topic,description, tasks;
    private AppCompatSpinner semester;
    private MaterialTextView date;
    private NoteViewModel viewModel;
    private Calendar calendar;
    private OnUpdateInterfaces updateInterface;

    public UpdateDialog() {
        calendar = Calendar.getInstance();
        calendar.clear();
    }

    public void setNote(Note note){
        this.note = note;
        calendar.setTimeInMillis(note.getDate());
    }

    public void setViewModel(NoteViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void setOnUpdateInterface(OnUpdateInterfaces updateInterface){
        this.updateInterface = updateInterface;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("Note",note);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (note == null){
            note = (Note) savedInstanceState.getSerializable("Note");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        viewModel = new NoteViewModel.Factory(requireActivity()).getNoteViewModel();
        return inflater.inflate(R.layout.dialog_update, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        findViews();
        initViews();
    }

    private void findViews(){
        topic = getView().findViewById(R.id.topicInput);
        topic.setText(note.getTitle());
        description = getView().findViewById(R.id.descriptionInput);
        description.setText(note.getDescription());
        tasks = getView().findViewById(R.id.taskInput);
        tasks.setText(note.getFutureTasks());
        semester = getView().findViewById(R.id.semesterSpinner);
        semester.setSelection(note.getSemesterNumber());
        date = getView().findViewById(R.id.dateEdit);
        date.setText(calendar.getTime().toString());
    }

    private void initViews(){
        getView().findViewById(R.id.dateEdit).setOnClickListener(v-> new CalenderDialog(this).show(getFragmentManager(), ""));
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
                viewModel.update(note.getId(),values);
                updateInterface.onUpdated();
                dismiss();
            }else
                Snackbar.make(getView(), "Some values are missing",Snackbar.LENGTH_LONG).show();
        });
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        calendar.set(year,month,dayOfMonth);
        date.setText(calendar.getTime().toString());
    }
}
