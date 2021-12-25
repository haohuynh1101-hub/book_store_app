package com.example.book_store_app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.book_store_app.models.MyCartModel;

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


    public static class ViewHolder{
        public TextView txtTitle,txtidOrder,txtStatus;
        Button btnViewDetail,btnBuyAgain;
        public ImageView imgBook;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
