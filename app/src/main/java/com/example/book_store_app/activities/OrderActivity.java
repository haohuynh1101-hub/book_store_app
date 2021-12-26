package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.book_store_app.R;
import com.example.book_store_app.databinding.ActivityMyCartBinding;
import com.example.book_store_app.databinding.ActivityOrderBinding;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.models.MyCartModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        getData();
    }

    private void getData() {
        Bundle bundle=getIntent().getExtras();
        if(bundle == null){
            return;
        }
        MyCartModel bookModel= (MyCartModel) bundle.get("book-mycart");
        binding.txtTitleBookOrder.setText(bookModel.getTitle());
        binding.txtIdOrderDetail.setText(bookModel.getIdOrder());
        binding.txtQuantityOrder.setText("1");
    }

    private void addEvents() {
        binding.btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenshot();
            }
        });
    }


    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }


}