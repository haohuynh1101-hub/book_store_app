package com.example.book_store_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.book_store_app.R;
import com.example.book_store_app.adapter.CartAdapter;
import com.example.book_store_app.adapter.CategoryAdapter;
import com.example.book_store_app.databinding.FragmentCartBinding;
import com.example.book_store_app.databinding.FragmentCategoryBinding;
import com.example.book_store_app.models.CategoryModel;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    FragmentCategoryBinding binding;
    Context context;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> arrCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        binding = FragmentCategoryBinding.inflate(inflater,container,false);
        addControls();
        return binding.getRoot();
    }

    private void addControls() {
        arrCategory=new ArrayList<>();
        arrCategory.add(new CategoryModel(1,"Kinh tế",R.drawable.image_category,"#52A593"));
        arrCategory.add(new CategoryModel(2,"Tâm lý",R.drawable.image_psy,"#FFC653"));
        arrCategory.add(new CategoryModel(3,"Khoa học",R.drawable.image_science,"#52A593"));
        arrCategory.add(new CategoryModel(4,"Truyện tranh",R.drawable.image_comic,"#FFC653"));

        categoryAdapter=new CategoryAdapter(getContext(),arrCategory);
        binding.rvPopularCategory.setHasFixedSize(true);

        binding.rvPopularCategory.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvPopularCategory.setItemAnimator(new DefaultItemAnimator());
        binding.rvPopularCategory.setAdapter(categoryAdapter);
    }
}