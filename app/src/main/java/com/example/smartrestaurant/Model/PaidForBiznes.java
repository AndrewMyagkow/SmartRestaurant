package com.example.smartrestaurant.Model;

public class PaidForBiznes {
    private String bar,date,pid,symma,table,time,zakaz;
    public PaidForBiznes()
    {}

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZakaz() {
        return zakaz;
    }

    public void setZakaz(String zakaz) {
        this.zakaz = zakaz;
    }

    public PaidForBiznes(String bar, String date, String pid, String symma, String table, String time, String zakaz) {
        this.bar = bar;
        this.date = date;
        this.pid = pid;
        this.symma = symma;
        this.table = table;
        this.time = time;
        this.zakaz = zakaz;
    }
}
