package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Kid {
    private static int NextKidId = 1;
    private int KidId;
    private String KidName;
    private String KidPassword;
    public HashMap<String,Boolean> wishList = new HashMap<>();

    public Kid(String KidName, String KidPassword) {
        this.KidId = NextKidId;
        NextKidId++;
        this.KidName = KidName;
        this.KidPassword = KidPassword;
    }

    public Kid(String KidName, String KidPassword,HashMap<String,Boolean> KidWishList) {
        this.KidId = NextKidId;
        NextKidId++;
        this.KidName = KidName;
        this.KidPassword = KidPassword;
        wishList = KidWishList;
    }



    //getters setters
    public void setKidId(int kidId) {
        KidId = kidId;
    }
    public int getKidId() {
        return KidId;
    }
    public String getKidName() {
        return KidName;
    }
    public String getKidPassword() {
        return KidPassword;
    }
    // if we want to rename kids.
    public void setKidName(String kidName) {
        KidName = kidName;
    }

    public HashMap<String, Boolean> getWishList() {
        return wishList;
    }

    public void setWishList(HashMap<String, Boolean> wishList) {
        this.wishList = wishList;
    }
}
