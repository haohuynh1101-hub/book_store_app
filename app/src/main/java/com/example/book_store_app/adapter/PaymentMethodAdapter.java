package com.example.book_store_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book_store_app.R;
import com.example.book_store_app.models.CartModel;
import com.example.book_store_app.models.PaymentMethod;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaymentMethodAdapter extends BaseAdapter {
    Context context;
    ArrayList<PaymentMethod> arrayList;

    public PaymentMethodAdapter(Context context, ArrayList<PaymentMethod> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class Viewholder{
        ImageView imgPaymentMethod;
        TextView txtPaymentName;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final Viewholder viewHolder;
        if (convertView == null) {
            viewHolder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_paymentmethod_layout, null);
            viewHolder.imgPaymentMethod = convertView.findViewById(R.id.imgPaymentMethod);
            viewHolder.txtPaymentName = convertView.findViewById(R.id.txtPaymentName);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Viewholder) convertView.getTag();
        }

        PaymentMethod paymentModal= (PaymentMethod) getItem(i);
        viewHolder.txtPaymentName.setText(paymentModal.getName());

        Picasso.get().load(paymentModal.getImage()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgPaymentMethod);


        return convertView;
    }
}
