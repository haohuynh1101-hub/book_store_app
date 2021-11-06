package com.example.book_store_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
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
import com.example.book_store_app.fragments.CartFragment;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.models.CartModel;
import com.example.book_store_app.utils.CheckConnection;
import com.example.book_store_app.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class DetailBookActivity extends AppCompatActivity {
    ActivityDetailBookBinding binding;
    Integer IDBook;
    String imgBook="";
    String title="";
    String author="";
    Double price=0.0;
    Integer rate=0;

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
        title=bookModel.getTitle();
        author=bookModel.getAuthor();
        binding.txtAuthorDetail.setText(bookModel.getAuthor());
        binding.txtNameDetail.setText(bookModel.getTitle());

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            getDataBook();
        } else {
            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }

        addEvents();
    }

    private void addEvents() {
        addToCart();
    }

    private void addToCart() {
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.cartModels.size() > 0) {
                    int sl=1;
                    for (int i = 0; i < MainActivity.cartModels.size(); i++) {
                        if (MainActivity.cartModels.get(i).getId() == IDBook) {
                            MainActivity.cartModels.get(i).setQuantity(MainActivity.cartModels.get(i).getQuantity() + sl);
                            MainActivity.cartModels.get(i).setPrice(price * MainActivity.cartModels .get(i).getQuantity());
                        }else {
                            MainActivity.cartModels.add(new CartModel(IDBook,title,"",author,imgBook,price,rate,5,sl));
                            break;
                        }
                    }
                } else {
                    int soLuong = 1;
                    Double total = price * soLuong;
                    MainActivity.cartModels.add(new CartModel(IDBook,title,"",author,imgBook,price,rate,5,soLuong));
                }
//                int soLuong = 1;
//                Double total = price * soLuong;
//                MainActivity.cartModels.add(new CartModel(IDBook,title,"",author,imgBook,price,rate,5,soLuong));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("detail","cart");
                startActivity(intent);

            }
        });

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.cartModels.size() > 0) {
                    int sl=1;
                    for (int i = 0; i < MainActivity.cartModels.size(); i++) {
                        if (MainActivity.cartModels.get(i).getId() == IDBook) {
                            MainActivity.cartModels.get(i).setQuantity(MainActivity.cartModels.get(i).getQuantity() + sl);
                            MainActivity.cartModels.get(i).setPrice(price * MainActivity.cartModels .get(i).getQuantity());
                        }
                    }
                } else {
                    int soLuong = 1;
                    Double total = price * soLuong;
                    MainActivity.cartModels.add(new CartModel(IDBook,title,"",author,imgBook,price,rate,5,soLuong));
                }
                Toast.makeText(DetailBookActivity.this, "Thêm sản phẩm thành công!",
                        Toast.LENGTH_LONG).show();
            }
        });
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

                                    price=jsonObject.getDouble("price");
                                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                    binding.txtPriceDetail.setText(decimalFormat.format(price)+"đ");

                                    imgBook=jsonObject.getString("thumbnail_url");
                                    Picasso.get().load(imgBook).placeholder(R.drawable.noimage).error(R.drawable.error).into(binding.imgBookDetail);

                                    rate=jsonObject.getInt("rating_average");
                                    binding.rbStarDetail.setNumStars(rate);
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