package com.example.book_store_app.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int id;
    private String title;
    private Integer image;
    private String bgColor;

    public CategoryModel(int id, String title, Integer image, String bgColor) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.bgColor = bgColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
