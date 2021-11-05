package com.example.book_store_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.book_store_app.R;
import com.example.book_store_app.databinding.ActivityDetailBookBinding;
import com.example.book_store_app.databinding.ActivityLoginBinding;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.utils.CheckConnection;
import com.example.book_store_app.utils.Server;

import org.json.JSONObject;

public class DetailBookActivity extends AppCompatActivity {
    ActivityDetailBookBinding binding;
    Integer IDBook;
    String imgBook="";
    String title="";
    String author="";
    String description="";
    Double price=0.0;
    Integer discount_rate=0;
    Integer rate=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            getDataBook();
        } else {
            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }
        addEvents();
    }

    private void getDataBook() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.apiListBook+IDBook, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(response!=null){
                                    JSONObject jsonObject = response;
                                     title = jsonObject.getString("name");
                                    price = jsonObject.getDouble("price");
                                    imgBook = jsonObject.getString("thumbnail_url");
                                    rate=jsonObject.getInt("rating_average");
                                     author=jsonObject.getString("author_name");
                                     discount_rate=jsonObject.getInt("discount_rate");
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void addEvents() {

    }


}