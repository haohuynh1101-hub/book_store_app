package com.example.book_store_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.book_store_app.R;
import com.example.book_store_app.activities.LoginActivity;
import com.example.book_store_app.databinding.FragmentHomeBinding;
import com.example.book_store_app.databinding.FragmentSignUpBinding;


public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        binding = FragmentSignUpBinding.inflate(inflater,container,false);
        addEvents();

        return binding.getRoot();
    }

    private void addEvents() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment myFragment = new EnterOTPFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layoutSignUpContainer,myFragment).addToBackStack(null).commit();
            }
        });

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}