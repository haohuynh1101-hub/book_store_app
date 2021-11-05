package com.example.book_store_app.models;

public class CartModel extends BookModel {
    private Integer quantity;


    public CartModel(int id, String title, String description, String author, String image, Double price, Integer rate, Integer discount_rate) {
        super(id, title, description, author, image, price, rate, discount_rate);
    }

    public CartModel(int id, String title, String description, String author, String image, Double price, Integer rate, Integer discount_rate, Integer quantity) {
        super(id, title, description, author, image, price, rate, discount_rate);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
