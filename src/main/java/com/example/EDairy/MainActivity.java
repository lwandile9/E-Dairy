package com.example.EDairy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.EDairy.ui.adapter.FragmentAdapter;
import com.example.EDairy.ui.dialog.ThemeDialog;
import com.example.EDairy.ui.preferences.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Preferences preferences = new Preferences(this);
        getDelegate().setLocalNightMode(preferences.getThemeMode());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        initViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings){
            new ThemeDialog().show(getSupportFragmentManager(), "");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViewPager(){
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setPageTransformer(new CompositePageTransformer());
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText(getString(R.string.create));
                    tab.setIcon(R.drawable.ic_baseline_post_add_24);
                    break;
                case 1:
                    tab.setText(getString(R.string.update));
                    tab.setIcon(R.drawable.ic_baseline_edit_24);
                    break;
                case 2:
                    tab.setText(getString(R.string.read));
                    tab.setIcon(R.drawable.ic_baseline_library_books_24);
                    break;
                case 3:
                    tab.setText("My Notes");
                    tab.setIcon(R.drawable.ic_baseline_library_books_24);
                    break;
            }
        }).attach();
    }
}