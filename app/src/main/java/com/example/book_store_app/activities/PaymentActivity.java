package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.book_store_app.R;
import com.example.book_store_app.adapter.PaymentMethodAdapter;
import com.example.book_store_app.databinding.ActivityLoginBinding;
import com.example.book_store_app.databinding.ActivityPaymentBinding;
import com.example.book_store_app.models.PaymentMethod;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    PaymentMethodAdapter paymentMethodAdapter;
    ArrayList<PaymentMethod> paymentMethods;

    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addControls();
        initData();
        addEvents();
        eventUtil();
    }

    private void addEvents() {
        binding.lnDeliveryMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(PaymentActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout, findViewById(R.id.btsMethodShip));
               bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        binding.lnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(PaymentActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.address_bottom_sheet, findViewById(R.id.btsAddress));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    private void initData() {
        paymentMethods.add(new PaymentMethod(0, "https://cdn.pixabay.com/photo/2016/03/31/21/41/cash-1296585_960_720.png", "Thanh toán tiền mặt"));
        paymentMethods.add(new PaymentMethod(1, "https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png", "Ví momo"));
        paymentMethods.add(new PaymentMethod(2, "https://file.hstatic.net/1000012850/file/zalopay_logo_grande.png", "Ví ZaloPay"));
        paymentMethods.add(new PaymentMethod(3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGNNNMNmBqHY1rqG9TcbbsuY3BJa3j9kSzlg&usqp=CAU", "Thẻ ATM"));
//        paymentMethods.add(new PaymentMethod(4, "data:image/jpeg;base64,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", "Thêm thẻ tín dụng/Ghi nợ"));

    }

    private void addControls() {
        paymentMethods = new ArrayList<>();
        paymentMethodAdapter = new PaymentMethodAdapter(PaymentActivity.this, paymentMethods);
        binding.lvPaymentMethod.setAdapter(paymentMethodAdapter);

        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PaymentActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });

        binding.tbBackBookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void eventUtil() {
        double toltalMoney = 0;
        for (int i = 0; i < MainActivity.cartModels.size(); i++) {
            toltalMoney = toltalMoney + MainActivity.cartModels.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        binding.txtValuePayment.setText(decimalFormat.format(toltalMoney) + " đ");
    }
}