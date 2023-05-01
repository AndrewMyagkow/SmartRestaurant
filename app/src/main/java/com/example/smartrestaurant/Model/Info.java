package com.example.smartrestaurant.Model;

public class Info {
    private String pid, dishes, symma, komment, barman, table, admin;

    public  Info()
    {}

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public Info(String pid, String dishes, String symma, String komment, String barman, String table, String admin) {
        this.pid = pid;
        this.dishes = dishes;
        this.symma = symma;
        this.komment = komment;
        this.barman = barman;
        this.table = table;
        this.admin = admin;
    }
}
