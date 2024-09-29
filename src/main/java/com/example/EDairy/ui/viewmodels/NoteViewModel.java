package com.example.EDairy.ui.viewmodels;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.EDairy.NoteApplication;
import com.example.EDairy.DB.CollegeDiaryTable;
import com.example.EDairy.model.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NoteViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> mutableLiveData;
    private final ExecutorService executorService;
    private final CollegeDiaryTable diaryTable;
    private final NoteApplication noteApplication;

    public NoteViewModel(ExecutorService executorService, NoteApplication noteApplication) {
        this.executorService = executorService;
        this.noteApplication = noteApplication;
        this.diaryTable = new CollegeDiaryTable();
        mutableLiveData = new MutableLiveData<>();
    }


    public void update(int noteId,@NonNull ContentValues values){
        executorService.execute(()->{
            diaryTable.setDatabase(noteApplication.getLiteDatabase());
            diaryTable.update(noteId, values);
        });
    }

    public void create(@NonNull ContentValues values){
        executorService.execute(()->{
            diaryTable.setDatabase(noteApplication.getLiteDatabase());
            diaryTable.insert(values);
        });
    }

    public void delete(int noteId){
        executorService.execute(()->{
            diaryTable.setDatabase(noteApplication.getLiteDatabase());
            diaryTable.delete(noteId);
        });
    }

    public void read(){
        executorService.execute(()-> {
            diaryTable.setDatabase(noteApplication.getLiteDatabase());
            mutableLiveData.postValue(diaryTable.read());
        });
    }

    public MutableLiveData<List<Note>> getMutableLiveData(){
        return mutableLiveData;
    }


    public static class Factory implements ViewModelProvider.Factory {
        private final NoteApplication application;

        public Factory(FragmentActivity activity) {
            application = (NoteApplication) activity.getApplication();
        }

        public NoteViewModel getNoteViewModel(){
            return create(NoteViewModel.class);
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(NoteViewModel.class))
                return (T) new NoteViewModel(application.getExecutorService(), application);
            return null;
        }
    }
}
