package com.fanaye.sorantravel.ui.weather.model;

public class Sys {

    private int type;
    private int id;
    private double message;
    private String country;
    private long sunrise;
    private int sunset;

    public Sys(int type, int id, double message, String country, long sunrise, int sunset) {
        setType(type);
        setId(id);
        setMessage(message);
        setCountry(country);
        setSunrise(sunrise);
        setSunset(sunset);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
