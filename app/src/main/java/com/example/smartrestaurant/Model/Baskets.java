package com.example.smartrestaurant.Model;

public class Baskets {
    private String name,price, valuekolvo,category;

    public Baskets(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValuekolvo() {
        return valuekolvo;
    }

    public void setValuekolvo(String valuekolvo) {
        this.valuekolvo = valuekolvo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Baskets(String name, String price, String valuekolvo, String category) {
        this.name = name;
        this.price = price;
        this.valuekolvo = valuekolvo;
        this.category = category;
    }
}
