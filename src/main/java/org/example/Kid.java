package org.example;

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

    public String getWishesCSV(){
        String wishesCSV = getWishList().toString().replaceAll("=",",") // bytar ut = tecknet som kommer mellan String och boolean när man listar ut wishen till ,
                                                   .replaceAll("\\{", "") // tar bort start tecknet som kommer när man listar ut alla wishes
                                                   .replaceAll("}", "") // Tar bort slut tecknet som kommer när man listar ut alla wishes
                                                   .replaceAll(", ",","); // Tar bort mellanrum som kommer efter , när man listar alla wishes

        return (getKidName()+","+wishesCSV);
    }


    public HashMap<String, Boolean> getWishList() {
        return wishList;
    }

    public void setWishList(HashMap<String, Boolean> wishList) {
        this.wishList = wishList;
    }
}
