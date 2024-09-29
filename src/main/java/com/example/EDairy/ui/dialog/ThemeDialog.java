package com.example.EDairy.ui.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.example.EDairy.R;
import com.example.EDairy.ui.preferences.Preferences;

public class ThemeDialog extends BottomSheetDialogFragment {
    private RadioGroup radioGroup;
    private MaterialButton save;
    private Preferences preferences;

    public ThemeDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_theme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup = view.findViewById(R.id.radioGroup);
        save = view.findViewById(R.id.saveButton);
        preferences = new Preferences(getContext());
        setSelectedTheme();
        saveTheme();
    }

    private void setSelectedTheme(){
        switch (preferences.getThemeMode()){
            case AppCompatDelegate.MODE_NIGHT_NO:
                radioGroup.check(R.id.light);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                radioGroup.check(R.id.dark);
                break;
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                radioGroup.check(R.id.system);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void saveTheme(){
        save.setOnClickListener(v->{
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.light:
                    changeTheme(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case R.id.dark:
                    changeTheme(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case R.id.system:
                    changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
            }
            dismiss();
        });
    }

    private void changeTheme(int themeMode){
        if (themeMode != preferences.getThemeMode()){
            preferences.setThemeMode(themeMode);
            requireActivity().recreate();
        }
    }
}
