package com.example.book_store_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toolbar;

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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class DetailBookActivity extends AppCompatActivity {
    ActivityDetailBookBinding binding;
    Integer IDBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle=getIntent().getExtras();
        if(bundle == null){
            return;
        }

        BookModel bookModel= (BookModel) bundle.get("book-info");
        IDBook=bookModel.getId();
        binding.txtAuthorDetail.setText(bookModel.getAuthor());

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            getDataBook();
        } else {
            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDataBook() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.apiBookDetail+"/"+IDBook, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response!=null){
                                    JSONObject jsonObject = response;
                                    binding.txtNameDetail.setText(jsonObject.getString("name"));
                                     DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                    binding.txtPriceDetail.setText(decimalFormat.format(jsonObject.getDouble("price"))+"đ");
                                    Picasso.get().load(jsonObject.getString("thumbnail_url")).placeholder(R.drawable.noimage).error(R.drawable.error).into(binding.imgBookDetail);

                                    binding.rbStarDetail.setNumStars(jsonObject.getInt("rating_average"));
                                    binding.txtPercentDetail.setText("-"+String.valueOf(jsonObject.getInt("discount_rate"))+"%");

                                    String description = jsonObject.getString("description");
                                    binding.txtDescriptionDetail.setText(Html.fromHtml(description).toString());
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

}