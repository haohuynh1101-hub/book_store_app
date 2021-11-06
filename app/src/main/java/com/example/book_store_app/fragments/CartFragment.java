package com.example.book_store_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.book_store_app.R;
import com.example.book_store_app.activities.MainActivity;
import com.example.book_store_app.adapter.CartAdapter;
import com.example.book_store_app.databinding.FragmentCartBinding;
import com.example.book_store_app.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    Context context;
    CartAdapter cartAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        binding = FragmentCartBinding.inflate(inflater,container,false);

        eventUtil();
        addControls();
        checkData();

        return binding.getRoot();
    }

    private void checkData() {
        if (MainActivity.cartModels.size() <= 0) {
            binding.txtThongBao.setVisibility(View.VISIBLE);
            binding.lvCart.setVisibility(View.INVISIBLE);
        } else {
            binding.txtThongBao.setVisibility(View.INVISIBLE);
            binding.lvCart.setVisibility(View.VISIBLE);

        }
    }

    private void addControls() {
        cartAdapter=new CartAdapter(getContext(),MainActivity.cartModels);
        binding.lvCart.setAdapter(cartAdapter);
    }

    private void eventUtil() {
        double toltalMoney = 0;
        for (int i = 0; i < MainActivity.cartModels.size(); i++) {
            toltalMoney = toltalMoney + MainActivity.cartModels.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        binding.txtValue.setText(decimalFormat.format(toltalMoney) + " đ");
    }
}