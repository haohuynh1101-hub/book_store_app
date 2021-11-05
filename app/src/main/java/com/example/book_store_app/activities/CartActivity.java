package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.book_store_app.R;
import com.example.book_store_app.adapter.CartAdapter;
import com.example.book_store_app.databinding.ActivityCartBinding;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eventUtil();
        addControls();
        checkData();
    }

    private void addControls() {
        cartAdapter=new CartAdapter(getApplicationContext(),MainActivity.cartModels);
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

    private void checkData() {
        if (MainActivity.cartModels.size() <= 0) {
            binding.txtThongBao.setVisibility(View.VISIBLE);
            binding.lvCart.setVisibility(View.INVISIBLE);
        } else {
            binding.txtThongBao.setVisibility(View.INVISIBLE);
            binding.lvCart.setVisibility(View.VISIBLE);

        }
    }
}