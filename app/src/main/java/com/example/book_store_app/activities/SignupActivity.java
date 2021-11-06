package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.book_store_app.R;
import com.example.book_store_app.fragments.ForgotPasswordFrafment;
import com.example.book_store_app.fragments.SignUpFragment;

public class SignupActivity extends AppCompatActivity {
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.layoutSignUpContainer,new SignUpFragment());
        transaction.commit();
    }
}