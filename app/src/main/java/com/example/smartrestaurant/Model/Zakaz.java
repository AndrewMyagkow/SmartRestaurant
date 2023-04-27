package com.example.smartrestaurant.Model;

public class Zakaz {
    private String barman,dishes,komment,symma,table,pid;
    public  Zakaz()
    {}

    public String getBarman() {
        return barman;
    }

    public void setBarman(String barman) {
        this.barman = barman;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getKomment() {
        return komment;
    }

    public void setKomment(String komment) {
        this.komment = komment;
    }

    public String getSymma() {
        return symma;
    }

    public void setSymma(String symma) {
        this.symma = symma;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Zakaz(String barman, String dishes, String komment, String symma, String table, String pid) {
        this.barman = barman;
        this.dishes = dishes;
        this.komment = komment;
        this.symma = symma;
        this.table = table;
        this.pid = pid;
    }
}
