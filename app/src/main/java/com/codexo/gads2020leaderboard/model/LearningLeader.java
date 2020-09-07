package com.codexo.gads2020leaderboard.model;

public class LearningLeader {
    private String name;
    private String country;
    private int hours;

    public LearningLeader(String name, String country, int hours) {
        this.name = name;
        this.country = country;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
