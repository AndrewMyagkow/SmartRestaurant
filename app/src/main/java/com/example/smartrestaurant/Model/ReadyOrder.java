package com.example.smartrestaurant.Model;

public class ReadyOrder {
    private String barman,zakaz,komment,symma,table,pid;
    public ReadyOrder()
    {}

    public String getBarman() {
        return barman;
    }

    public void setBarman(String barman) {
        this.barman = barman;
    }

    public String getZakaz() {
        return zakaz;
    }

    public void setZakaz(String zakaz) {
        this.zakaz = zakaz;
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

    public ReadyOrder(String barman, String zakaz, String komment, String symma, String table, String pid) {
        this.barman = barman;
        this.zakaz = zakaz;
        this.komment = komment;
        this.symma = symma;
        this.table = table;
        this.pid = pid;
    }
}
