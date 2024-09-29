package com.example.EDairy.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.example.EDairy.R;
import com.example.EDairy.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder>{
    private List<Note> notes = new ArrayList<>();

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_read, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.topic.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.tasks.setText(note.getFutureTasks());
        holder.semester.setText("Semester: " + note.getSemesterNumber());
        holder.date.setText(new Date(note.getDate()).toString());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public MaterialTextView topic, description, date, tasks, semester;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            tasks = itemView.findViewById(R.id.tasks);
            semester = itemView.findViewById(R.id.semester);
        }
    }
}
