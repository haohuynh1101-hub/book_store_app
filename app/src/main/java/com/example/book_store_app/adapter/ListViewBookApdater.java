package com.example.book_store_app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class ListViewBookApdater extends  RecyclerView.Adapter<ListViewBookApdater.ItemHolder> implements View.OnClickListener {

    Context context;
    ArrayList<BookModel> books;

    public ListViewBookApdater(Context context, ArrayList<BookModel> books) {
        this.context = context;
        this.books = books;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_horizental_book,null);
        ItemHolder itemHolder=new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewBookApdater.ItemHolder holder, int position) {
        BookModel book=books.get(position);
//        holder.txtTitle.setText(book.getTitle());
        holder.txtTitle.setMaxLines(2);
        holder.txtTitle.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtTitle.setText(book.getTitle());
        holder.txtAuthor.setText(book.getAuthor());
        holder.rbStar.setNumStars(book.getRate());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(book.getPrice()) +"đ");
        holder.txtPercent.setText(book.getDiscount_rate()+"%");
        Picasso.get().load(book.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imgBook);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ItemHolder extends RecyclerView.ViewHolder {


        ImageView imgBook;
        TextView txtTitle,txtPrice,txtAuthor,txtPercent;
        RatingBar rbStar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgBook=itemView.findViewById(R.id.imgBookHorizontal);
            txtTitle=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPriceHorizontal);
            txtAuthor=itemView.findViewById(R.id.txtAuthorHorizontal);
            txtPercent=itemView.findViewById(R.id.txtPercentHorizontal);
            rbStar=itemView.findViewById(R.id.rbStarHorizontal);
        }
    }

}
