package com.example.EDairy.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.textfield.TextInputEditText;
import com.example.EDairy.R;
import com.example.EDairy.model.Note;
import com.example.EDairy.ui.adapter.OnClickListener;
import com.example.EDairy.ui.adapter.UpdateAdapter;
import com.example.EDairy.ui.dialog.UpdateDialog;
import com.example.EDairy.ui.viewmodels.NoteViewModel;

public class UpdateFragment extends Fragment implements OnClickListener {
    private StaggeredGridLayoutManager layoutManager;
    private UpdateAdapter adapter;
    private RecyclerView recyclerView;
    private NoteViewModel viewModel;

    public UpdateFragment() {
        super(R.layout.fragment_update);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NoteViewModel.Factory(requireActivity()).getNoteViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        initViews();
    }

    private void findViews(){
        recyclerView = getView().findViewById(R.id.recyclerView);
        layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        adapter = new UpdateAdapter();
        adapter.setListener(this);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), list-> adapter.setNoteList(list));
    }

    private void initViews(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ((TextInputEditText) getView().findViewById(R.id.searchInput)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.read();
    }

    @Override
    public void onClick(@NonNull Note note) {
        UpdateDialog dialog = new UpdateDialog();
        dialog.setNote(note);
        dialog.setViewModel(viewModel);
        dialog.setOnUpdateInterface( ()-> viewModel.read());
        dialog.show(getFragmentManager(),"");
    }

    @Override
    public void onDeleteClick(@NonNull Note note) {
        viewModel.delete(note.getId());
        viewModel.read();
    }
}
