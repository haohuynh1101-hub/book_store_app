package com.example.book_store_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.book_store_app.R;
import com.example.book_store_app.models.CartModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<CartModel> carts;

    public CartAdapter(Context context, ArrayList<CartModel> carts) {
        this.context = context;
        this.carts = carts;
    }



    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int i) {
        return carts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtTitle,txtAuthor,txtPrice,txtPercent,txtQuantity;
        Button btnAdd,btnSubtract;
        public ImageView imgBook;
        public RatingBar rbStar;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new CartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_cart_item, null);
            viewHolder.imgBook = convertView.findViewById(R.id.imgBookCart);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitleCart);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPriceCart);
            viewHolder.btnAdd=convertView.findViewById(R.id.btnAdd);
            viewHolder.btnSubtract=convertView.findViewById(R.id.btnSubtract);
            viewHolder.txtAuthor=convertView.findViewById(R.id.txtAuthorCart);
            viewHolder.txtPercent=convertView.findViewById(R.id.txtPercentCart);
            viewHolder.txtQuantity=convertView.findViewById(R.id.txtQuantity);
            viewHolder.rbStar=convertView.findViewById(R.id.rbStarCart);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CartAdapter.ViewHolder) convertView.getTag();
        }

        CartModel cartModel= (CartModel) getItem(i);
        viewHolder.txtTitle.setText(cartModel.getTitle());

        Picasso.get().load(cartModel.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgBook);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText(decimalFormat.format(cartModel.getPrice()) +"đ");
        viewHolder.txtQuantity.setText(String.valueOf(cartModel.getQuantity()));
        viewHolder.rbStar.setNumStars(cartModel.getRate());
        viewHolder.txtAuthor.setText(cartModel.getAuthor());

        return convertView;

    }
}
