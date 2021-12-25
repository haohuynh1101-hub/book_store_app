package com.example.book_store_app.models;

import java.io.Serializable;

public class MyCartModel extends BookModel {

    private String idOrder;
    private String status;

    public MyCartModel(int id, String title, String description, String author, String image, Double price, Integer rate, Integer discount_rate) {
        super(id, title, description, author, image, price, rate, discount_rate);
    }

    public MyCartModel(int id, String title, String description, String author, String image, Double price, Integer rate, Integer discount_rate, String idOrder, String status) {
        super(id, title, description, author, image, price, rate, discount_rate);
        this.idOrder = idOrder;
        this.status = status;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
