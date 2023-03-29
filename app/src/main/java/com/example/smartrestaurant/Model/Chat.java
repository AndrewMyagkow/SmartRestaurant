package com.example.smartrestaurant.Model;

public class Chat {
    private String descriptionz , price,description,pname;
    public Chat(){

    }

    public Chat(String descriptionz, String price, String description, String pname) {
        this.descriptionz = descriptionz;
        this.price = price;
        this.description = description;
        this.pname = pname;
    }

    public String getDescriptionz() {
        return descriptionz;
    }

    public void setDescriptionz(String descriptionz) {
        this.descriptionz = descriptionz;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}