package com.example.book_store_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.book_store_app.R;
import com.example.book_store_app.adapter.CartAdapter;
import com.example.book_store_app.adapter.CategoryAdapter;
import com.example.book_store_app.adapter.ListViewBookApdater;
import com.example.book_store_app.databinding.FragmentCartBinding;
import com.example.book_store_app.databinding.FragmentCategoryBinding;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.models.CategoryModel;
import com.example.book_store_app.utils.CheckConnection;
import com.example.book_store_app.utils.Server;

import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    FragmentCategoryBinding binding;
    Context context;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> arrCategory;

    ListViewBookApdater listViewBookApdater;
    ArrayList<BookModel> arrBook;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        binding = FragmentCategoryBinding.inflate(inflater,container,false);
        addControls();

        if (CheckConnection.haveNetworkConnection(context)) {
            getDataBooks();
        } else {
            CheckConnection.showToast(context, "Bạn hãy kiểm tra lại kết nối");
        }

        return binding.getRoot();
    }

    private void getDataBooks() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.apiListBook, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int length=response.getJSONArray("data").length();
                            if(response!=null){
                                for (int i=0;i<length-20;i++){
                                    JSONObject jsonObject = response.getJSONArray("data").getJSONObject(i);
                                    int Id = jsonObject.getInt("id");
                                    String Name = jsonObject.getString("name");
                                    Double Price = jsonObject.getDouble("price");
                                    String Img = jsonObject.getString("thumbnail_url");
                                    Integer rate=jsonObject.getInt("rating_average");
                                    String author=jsonObject.getString("author_name");
                                    Integer discount_rate=jsonObject.getInt("discount_rate");
                                    arrBook.add(new BookModel(Id, Name,"",author,Img, Price,rate,discount_rate));

                                    listViewBookApdater.notifyDataSetChanged();
                                }

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

    private void addControls() {
        arrCategory=new ArrayList<>();
        arrCategory.add(new CategoryModel(1,"Kinh tế",R.drawable.image_category,"#52A593"));
        arrCategory.add(new CategoryModel(2,"Tâm lý",R.drawable.image_psy,"#FFC653"));
        arrCategory.add(new CategoryModel(3,"Khoa học",R.drawable.image_science,"#52A593"));
        arrCategory.add(new CategoryModel(4,"Truyện tranh",R.drawable.image_comic,"#FFC653"));

        categoryAdapter=new CategoryAdapter(getContext(),arrCategory);
        binding.rvPopularCategory.setHasFixedSize(true);

        binding.rvPopularCategory.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvPopularCategory.setItemAnimator(new DefaultItemAnimator());
        binding.rvPopularCategory.setAdapter(categoryAdapter);

        //Book items
        arrBook=new ArrayList<>();
        listViewBookApdater=new ListViewBookApdater(getContext(),arrBook);
        binding.rvCategoryBook.setHasFixedSize(true);

        binding.rvCategoryBook.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        binding.rvCategoryBook.setItemAnimator(new DefaultItemAnimator());
        binding.rvCategoryBook.setAdapter(listViewBookApdater);
    }
}