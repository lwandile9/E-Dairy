package com.example.EDairy.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EDairy.R;
import com.example.EDairy.ui.adapter.ReadAdapter;
import com.example.EDairy.ui.viewmodels.NoteViewModel;

public class ReadFragment extends Fragment {
    private ReadAdapter adapter;
    private NoteViewModel viewModel;

    public ReadFragment() {
        super(R.layout.fragment_read);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NoteViewModel.Factory(requireActivity()).getNoteViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new ReadAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), list-> adapter.setNotes(list));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.read();
    }
}
