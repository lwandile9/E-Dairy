package com.example.EDairy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.example.EDairy.R;
import com.example.EDairy.model.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.ViewHolder> implements Filterable {
    private List<Note> noteList = new ArrayList<>();
    private final List<Note> searchList = new ArrayList<>();
    private OnClickListener listener;
    private boolean isUserSearching = false;
    private Calendar calendar = Calendar.getInstance();

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_update,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        if (isUserSearching)
            note = searchList.get(position);
        calendar.clear();
        calendar.setTimeInMillis(note.getDate());
        holder.topic.setText(note.getTitle());
        holder.date.setText(calendar.getTime().toString());
    }

    @Override
    public int getItemCount() {
        if (isUserSearching)
            return searchList.size();
        return noteList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null){
                    if (constraint.length() > 0){
                        searchList.clear();
                        isUserSearching = true;
                        for (Note note: noteList){
                            if (note.getTitle().contains(constraint))
                                searchList.add(note);
                        }
                        return null;
                    }else
                        isUserSearching = false;
                }
                isUserSearching = false;
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public MaterialTextView topic, date;
        public ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            date = itemView.findViewById(R.id.date);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            itemView.setOnClickListener(v-> listener.onClick(noteList.get(getAdapterPosition())));
            deleteButton.setOnClickListener(v-> listener.onDeleteClick(noteList.get(getAdapterPosition())));
        }
    }
}
