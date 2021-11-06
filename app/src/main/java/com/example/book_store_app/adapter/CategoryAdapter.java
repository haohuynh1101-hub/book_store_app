package com.example.book_store_app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store_app.R;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.models.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemHolder> {
    Context context;
    ArrayList<CategoryModel> categoryModels;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_category,null);
        ItemHolder itemHolder= new ItemHolder(view);

        return itemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CategoryModel categoryModel=categoryModels.get(position);
        holder.txtTitle.setText(categoryModel.getTitle());
        holder.imgCategory.setImageResource(categoryModel.getImage());
        holder.linearLayoutItem.setBackgroundColor(Color.parseColor(categoryModel.getBgColor()));
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayoutItem;
        ImageView imgCategory;
        TextView txtTitle;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory=itemView.findViewById(R.id.imgCategory);
            txtTitle=itemView.findViewById(R.id.txtTitleCategory);
            linearLayoutItem=itemView.findViewById(R.id.lnItemCategory);
        }
    }
}
