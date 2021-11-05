package com.example.book_store_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.book_store_app.R;
import com.example.book_store_app.fragments.CartFragment;
import com.example.book_store_app.fragments.CategoryFragment;
import com.example.book_store_app.fragments.HomeFragment;
import com.example.book_store_app.fragments.ProfileFragment;
import com.example.book_store_app.models.CartModel;
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

    }

    private void addEvents() {
        bottomNavigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
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