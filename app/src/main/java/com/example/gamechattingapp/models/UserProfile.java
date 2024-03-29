package com.example.gamechattingapp.models;

public class UserProfile {

    private String nickName;
    private String bio;
    private int image; // Integer
    private int id_;

    public UserProfile(String nickName, String bio, int image, int id_) {
        this.nickName = nickName;
        this.bio = bio;
        this.image = image;
        this.id_ = id_;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }

    public void setBio(String price) {
        this.bio = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return nickName;
    }

    public String getPrice() {
        return bio;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }
}








