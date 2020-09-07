package com.codexo.gads2020leaderboard.model;

public class SkillLeader {
    private String name;
    private String country;
    private int score;

    public SkillLeader(String name, String country, int score) {
        this.name = name;
        this.country = country;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
