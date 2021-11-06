package com.example.book_store_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.book_store_app.R;
import com.example.book_store_app.fragments.CartFragment;
import com.example.book_store_app.fragments.CategoryFragment;
import com.example.book_store_app.fragments.HomeFragment;
import com.example.book_store_app.fragments.ProfileFragment;
import com.example.book_store_app.models.CartModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    public static ArrayList<CartModel> cartModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        initBadge();
    }

    private void initBadge() {
        BadgeDrawable badgeDrawable=bottomNavigationView.getOrCreateBadge(R.id.nav_cart);
        badgeDrawable.setBackgroundColor(Color.RED);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        badgeDrawable.setMaxCharacterCount(5);
        if(cartModels.size()>0){
            Log.d("TAG", "addControls: "+cartModels.size());
            badgeDrawable.setNumber(cartModels.size());
            badgeDrawable.setVisible(true);
        }else {
            badgeDrawable.setVisible(false);
        }
    }

    private void addEvents() {
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CartFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(navListener);

    }

    private void addControls() {
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        if(cartModels!=null){
            return;
        }else {
            cartModels=new ArrayList<>();
        }


    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectFragment = new HomeFragment();

                    break;
                case R.id.nav_cart:
                    selectFragment = new CartFragment();
                    break;
                case R.id.nav_category:
                    selectFragment = new CategoryFragment();
                    break;
                case R.id.nav_profile:
                    selectFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectFragment).commit();
            return true;
        }
    };

}