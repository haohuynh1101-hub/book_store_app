package com.example.book_store_app.utils;

public class Server {
    public static String localhost = "https://tiki.vn/api";
    public static String apiListBook = localhost + "/personalish/v1/blocks/listings?limit=48&categoryId=8322&category=8322";
    public static String apiBookDetail = localhost + "/v2/products";
    public static String linkSanPhamPhone="http://"+localhost+"/loaispserver/api/SanPham/?pagePhone=";
    public static String linkSanPhamLaptop="http://"+localhost+"/loaispserver/api/SanPham/?pageLaptop=";
}
