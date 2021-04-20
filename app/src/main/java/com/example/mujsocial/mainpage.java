package com.example.mujsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragments.home_fragment;
import fragments.notification_fragment;
import fragments.profile_fragment;
import fragments.search_fragment;

public class mainpage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_home:
                        selectorFragment = new home_fragment();
                        break;

                    case R.id.nav_search:
                        selectorFragment = new search_fragment();
                        break;

                    case R.id.nav_add:
                        selectorFragment = null;
                        startActivity(new Intent(mainpage.this, postActivity.class));
                        break;

                    case  R.id.nav_profile:
                        selectorFragment = new profile_fragment();
                        break;

                    case R.id.nav_heart:
                        selectorFragment = new notification_fragment();
                        break;
                }

                if(selectorFragment!= null)
                {
                  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home_fragment()).commit();
    }
}