package com.fanaye.sorantravel.ui.weather.model;

import java.util.ArrayList;

public class DATA {

    //Internal parameter of openweathermap.com
    private String base;

    //unknown, it exists in JSON response but not in the SITE docs.
    private int visibility;

    //Time of data calculation, unix, UTC
    private long dt;

    //the id of city
    private int id;

    //the name of city
    private String name;

    //internal parameter in the openweathermap.com
    private int cod;

    //the coordinate object
    private Coord coord;

    //the Weather object
    private ArrayList<Weather> weather;

    //the main object
    private Main main;

    //the wind object
    private Wind wind;

    //the clouds object
    private Clouds clouds;

    //the Sys object
    private Sys sys;


    public String getBase() {
        return base;
    }

    public int getVisibility() {
        return visibility;
    }

    public long getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }
}
