package com.example.smartrestaurant.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Feedback {
    private String feedback,name,time;

    public Feedback() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Feedback(String feedback, String name, String time) {
        this.feedback = feedback;
        this.name = name;
        this.time = time;
    }
}
