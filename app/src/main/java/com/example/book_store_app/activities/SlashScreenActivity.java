package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.book_store_app.R;

public class SlashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SlashScreenActivity.this,MainActivity.class);
                SlashScreenActivity.this.startActivity(mainIntent);
                SlashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}