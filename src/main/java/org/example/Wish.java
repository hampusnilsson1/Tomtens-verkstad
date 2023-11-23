package org.example;

public class Wish {

    int wishId;
    String wishName;
    int quantity;

    public Wish(int wishId, String wishName, int quantity){
        this.wishId = wishId;
        this.wishName = wishName;
        this.quantity = quantity;
    }

    public void printWishes(){
        System.out.println(wishId + ". "+ wishName );
    }
    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
