package com.example.sparrownotes;

public class Shop {private String text1;//商品名称
    private String text2;//商品描叙
    private int price;//商品价格
    private int num;//商品数量
    private int photoId;
    private int photo_addId;
    private int photo_downId;
    private boolean isChecked;

    public Shop(String text1,String text2,int photoId,int num,int price){
        this.text1 = text1;this.text2 = text2;this.photoId = photoId;this.num = num;this.price = price;
//        ,int price,int num,int photoId,int photo_addId,int photo_downId,boolean isChecked
//        this.price = price;this.num = num;
//        this.photoId = photoId;this.photo_downId = photo_downId;this.isChecked =isChecked;
//        this.photo_addId = photo_addId;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setPhoto_addId(int photo_addId) {
        this.photo_addId = photo_addId;
    }

    public void setPhoto_downId(int photo_downId) {
        this.photo_downId = photo_downId;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public int getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getPhoto_addId() {
        return photo_addId;
    }

    public int getPhoto_downId() {
        return photo_downId;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

