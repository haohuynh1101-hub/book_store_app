package com.example.book_store_app.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book_store_app.R;
import com.example.book_store_app.models.MyCartModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCartAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyCartModel> myCartModels;

    public MyCartAdapter(Context context, ArrayList<MyCartModel> myCartModels) {
        this.context = context;
        this.myCartModels = myCartModels;
    }

    @Override
    public int getCount() {
        return myCartModels.size();
    }

    @Override
    public Object getItem(int i) {
        return myCartModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public static class ViewHolder {
        public TextView txtTitle, txtIdOrder, txtStatus;
        Button btnViewDetail, btnBuyAgain;
        public ImageView imgBook;
    }

    private String handleColorStatus(String status) {
        if (status == "IN_PROGRESS") {
            return "Đang xử lý";
        } else if (status == "CANCEL") {
            return "Hủy bỏ";
        }
        return "Thành công";
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyCartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_my_cart_layout, null);
            viewHolder.imgBook = convertView.findViewById(R.id.imgBookMyCart);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitleBookMyCart);
            viewHolder.txtStatus = convertView.findViewById(R.id.txtStatus);
            viewHolder.txtIdOrder = convertView.findViewById(R.id.txtIdOrder);

            viewHolder.btnBuyAgain = convertView.findViewById(R.id.btnBuyAgain);
            viewHolder.btnViewDetail = convertView.findViewById(R.id.btnViewDetail);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyCartAdapter.ViewHolder) convertView.getTag();
        }

        MyCartModel myCartModel = (MyCartModel) getItem(i);
        viewHolder.txtTitle.setText(myCartModel.getTitle());

        Picasso.get().load(myCartModel.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgBook);
        String status = myCartModel.getStatus();
        if (status == "IN_PROGRESS") {
            viewHolder.txtStatus.setTextColor(Color.parseColor("#fbbf24"));

        } else if (status == "CANCEL") {
            viewHolder.txtStatus.setTextColor(Color.parseColor("#ef4444"));
        } else {
            viewHolder.txtStatus.setTextColor(Color.parseColor("#52A593"));

        }

        viewHolder.txtStatus.setText(handleColorStatus(status));
        viewHolder.txtIdOrder.setText(myCartModel.getIdOrder());

//        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
//        viewHolder.txtPrice.setText(decimalFormat.format(cartModel.getPrice()) +"đ");
//        viewHolder.txtQuantity.setText(String.valueOf(cartModel.getQuantity()));
//        viewHolder.rbStar.setNumStars(cartModel.getRate());
//        viewHolder.txtAuthor.setText(cartModel.getAuthor());

        return convertView;
    }
}
