package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.book_store_app.R;
import com.example.book_store_app.adapter.MyCartAdapter;
import com.example.book_store_app.databinding.ActivityMyCartBinding;
import com.example.book_store_app.databinding.ActivityPaymentBinding;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.models.MyCartModel;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {
    ActivityMyCartBinding binding;
    MyCartAdapter myCartAdapter;
    ArrayList<MyCartModel> myCartModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addControls();
        getData();
    }

    private void getData() {
        myCartModels.add(new MyCartModel(0, "Trầm lặng - Sức mạnh tiềm ẩn của người hướng nội", "", "author", "https://salt.tikicdn.com/cache/400x400/ts/product/ac/80/5c/f91ecd9f50a35284e3fd68accf974d1d.jpg.webp", 12.000, 1, 1, "VN-123", "IN_PROGRESS"));
        myCartModels.add(new MyCartModel(1, "Đừng sợ cuộc chơi", "", "", "https://salt.tikicdn.com/cache/400x400/ts/product/46/2a/0c/817b892dcaf521ca203c8a1d14c47d57.jpg.webp", 102.000, 1, 1, "US-123", "CANCEL"));
        myCartModels.add(new MyCartModel(2, "Muôn kiếp nhân sinh", "", "author", "https://salt.tikicdn.com/cache/400x400/ts/product/aa/ce/e5/38cde4cbfa65e85905301a1f759d5e4d.jpg.webp", 42.000, 1, 1, "GE-123", "SUCCESS"));

    }

    private void addControls() {
        myCartModels = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(MyCartActivity.this, myCartModels);
        binding.lvMyCart.setAdapter(myCartAdapter);

       binding.tbMyCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }
}