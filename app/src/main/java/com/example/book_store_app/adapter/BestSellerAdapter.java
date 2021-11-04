package com.example.book_store_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store_app.R;
import com.example.book_store_app.models.BookModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ItemHolder> {
    Context context;
    ArrayList<BookModel> bookArrayList;

    public BestSellerAdapter(Context context, ArrayList<BookModel> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bestseller_book,null);
        ItemHolder itemHolder=new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        BookModel book=bookArrayList.get(position);
        holder.txtTitle.setText(book.getTitle());
        holder.rbStar.setNumStars(book.getRate());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(book.getPrice()) +"đ");

        Picasso.get().load(book.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imgBook);
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView txtTitle,txtPrice;
        RatingBar rbStar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgBook=itemView.findViewById(R.id.imgBook);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            rbStar=itemView.findViewById(R.id.rbStar);
        }
    }
}
