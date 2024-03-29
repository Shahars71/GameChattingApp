package com.example.gamechattingapp.models;

public class UserProfile {

    private String nickName;
    private String price;
    private int image; // Integer
    private int id_;

    public UserProfile(String nickName, String price, int image, int id_) {
        this.nickName = nickName;
        this.price = price;
        this.image = image;
        this.id_ = id_;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return nickName;
    }

    public String getPrice() {
        return price;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }
}








