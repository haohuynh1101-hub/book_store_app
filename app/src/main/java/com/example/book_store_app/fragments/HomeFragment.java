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
import com.example.book_store_app.adapter.BestSellerAdapter;
import com.example.book_store_app.databinding.FragmentHomeBinding;
import com.example.book_store_app.models.BookModel;
import com.example.book_store_app.utils.CheckConnection;
import com.example.book_store_app.utils.Server;

import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    Context context;
    ArrayList<BookModel> arrBookBestSeller;
    ArrayList<BookModel> arrBookNewRelease;

    BestSellerAdapter bestSellerAdapter;
    BestSellerAdapter newReleaseAdapter;

    FragmentHomeBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        binding = FragmentHomeBinding.inflate(inflater,container,false);
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
                                for (int i=0;i<length;i++){
                                    JSONObject jsonObject = response.getJSONArray("data").getJSONObject(i);

                                    int Id = jsonObject.getInt("id");
                                    String Name = jsonObject.getString("name");
                                    Double Price = jsonObject.getDouble("price");
                                    String Img = jsonObject.getString("thumbnail_url");
                                    Integer rate=jsonObject.getInt("rating_average");

                                    arrBookBestSeller.add(new BookModel(Id, Name,"","",Img, Price,rate));

                                    bestSellerAdapter.notifyDataSetChanged();
                                }

                                for (int i=length-1;i>=0;i--){
                                    JSONObject jsonObject = response.getJSONArray("data").getJSONObject(i);

                                    int Id = jsonObject.getInt("id");
                                    String Name = jsonObject.getString("name");
                                    Double Price = jsonObject.getDouble("price");
                                    String Img = jsonObject.getString("thumbnail_url");
                                    Integer rate=jsonObject.getInt("rating_average");

                                    arrBookNewRelease.add(new BookModel(Id, Name,"","",Img, Price,rate));

                                    newReleaseAdapter.notifyDataSetChanged();
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
        arrBookBestSeller=new ArrayList<>();
        arrBookNewRelease=new ArrayList<>();

        bestSellerAdapter=new BestSellerAdapter(context,arrBookBestSeller);
        binding.rvBestSeller.setHasFixedSize(true);

        binding.rvBestSeller.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvBestSeller.setItemAnimator(new DefaultItemAnimator());
        binding.rvBestSeller.setAdapter(bestSellerAdapter);

        newReleaseAdapter=new BestSellerAdapter(context, arrBookNewRelease);
        binding.rvNewRelease.setHasFixedSize(true);

        binding.rvNewRelease.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvNewRelease.setItemAnimator(new DefaultItemAnimator());
        binding.rvNewRelease.setAdapter(newReleaseAdapter);
    }
}