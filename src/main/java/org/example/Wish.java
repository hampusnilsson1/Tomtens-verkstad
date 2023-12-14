package org.example;

public class Wish {

    private int wishId;
    private String wishName;
    private int quantity;

    public Wish(String wishName){
        this.wishName=wishName;
    }
    public Wish(int wishId, String wishName, int quantity){
        this.wishId = wishId;
        this.wishName = wishName;
        this.quantity = quantity;
    }

    public String getWishName() {
        return wishName;
    }

    public String getWishCSV()
    {
        return (wishId+","+wishName+","+quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
