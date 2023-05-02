package com.example.smartrestaurant.Model;

public class Reserved {
    private String pname, description, price, image, category, pid, date, time,primer,clock,minuts,kolvoguest, dishes, symma, komment, barman, table, admin,place;

    public Reserved(){

    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
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

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getMinuts() {
        return minuts;
    }

    public void setMinuts(String minuts) {
        this.minuts = minuts;
    }

    public String getKolvoguest() {
        return kolvoguest;
    }

    public void setKolvoguest(String kolvoguest) {
        this.kolvoguest = kolvoguest;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getSymma() {
        return symma;
    }

    public void setSymma(String symma) {
        this.symma = symma;
    }

    public String getKomment() {
        return komment;
    }

    public void setKomment(String komment) {
        this.komment = komment;
    }

    public String getBarman() {
        return barman;
    }

    public void setBarman(String barman) {
        this.barman = barman;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Reserved(String pname, String description, String price, String image, String category, String pid, String date, String time, String primer, String clock, String minuts, String kolvoguest, String dishes, String symma, String komment, String barman, String table, String admin, String place) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.primer = primer;
        this.clock = clock;
        this.minuts = minuts;
        this.kolvoguest = kolvoguest;
        this.dishes = dishes;
        this.symma = symma;
        this.komment = komment;
        this.barman = barman;
        this.table = table;
        this.admin = admin;
        this.place = place;
    }
}
