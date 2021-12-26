package com.example.book_store_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store_app.R;
import com.example.book_store_app.activities.DetailBookActivity;
import com.example.book_store_app.models.BookModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ItemHolder> implements View.OnClickListener {
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
//        holder.txtTitle.setText(book.getTitle());

        holder.txtTitle.setMaxLines(1);
        holder.txtTitle.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtTitle.setText(book.getTitle());
        holder.txtAuthor.setText(book.getAuthor());
        holder.rbStar.setNumStars(book.getRate());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(book.getPrice()) +"đ");
        holder.txtPercent.setText(book.getDiscount_rate()+"%");
        Picasso.get().load(book.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imgBook);

        holder.layoutItemVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGotoDetailBook(book);
            }
        });
    }

    private void handleGotoDetailBook(BookModel book) {
        Intent intent=new Intent(context, DetailBookActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("book-info",book);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItemVertical;

        ImageView imgBook;
        TextView txtTitle,txtPrice,txtAuthor,txtPercent;
        RatingBar rbStar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemVertical=itemView.findViewById(R.id.layoutItemVertical);

            imgBook=itemView.findViewById(R.id.imgBook);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtAuthor=itemView.findViewById(R.id.txtAuthor);
            txtPercent=itemView.findViewById(R.id.txtPercent);

//            rbStar=itemView.findViewById(R.id.rbStar);
        }
    }
}
