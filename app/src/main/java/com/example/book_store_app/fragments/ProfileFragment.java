package com.example.book_store_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.book_store_app.R;
import com.example.book_store_app.activities.LoginActivity;
import com.example.book_store_app.databinding.FragmentHomeBinding;
import com.example.book_store_app.databinding.FragmentProfileBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        addControls();

        return binding.getRoot();


    }

    private void addControls() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.noimage).error(R.drawable.error).into(binding.imgProfile);
        binding.txtFullName.setText(user.getDisplayName());
        binding.txtEmail.setText(user.getEmail());
    }


}