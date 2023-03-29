package com.example.smartrestaurant.Model;

public class WriteBook {
    private String description,pname;
    public WriteBook(){

    }

    public WriteBook( String description, String pname) {

        this.description = description;
        this.pname = pname;
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