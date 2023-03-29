package com.example.smartrestaurant.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Feedback {
    private String pname, description, price, image, category, pid, date, time, primer,TimeWrite,DateWrite,Timez;

    public Feedback() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat Time = new SimpleDateFormat("HH:mm ");
        TimeWrite = Time.format(calendar.getTime());
        SimpleDateFormat Date = new SimpleDateFormat("dd.MM.yyyy");
        DateWrite = Date.format(calendar.getTime());

        Timez = TimeWrite+DateWrite;

    }

    public Feedback(String pname, String description, String price, String image, String category, String pid, String date, String time, String primer) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.primer = primer;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return Timez;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrimer() {
        return primer;
    }

    public void setPrimer(String primer) {
        this.primer = primer;
    }
}
