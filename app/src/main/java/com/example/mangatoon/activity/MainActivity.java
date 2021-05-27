package com.example.mangatoon.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mangatoon.R;
import com.example.mangatoon.fragment.FavoriteFragment;
import com.example.mangatoon.fragment.HomeFragment;
import com.example.mangatoon.fragment.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change color in status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.home);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.favorite:
                    fragment = new FavoriteFragment();
                    break;
                case R.id.info:
                    fragment = new InfoFragment();
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).commit();
            }
            return true;
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {//Do nothing
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}