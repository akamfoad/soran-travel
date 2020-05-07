package com.fanaye.sorantravel.ui.weather.model;


// coord | coordinates, which is first parameter of JSON file from
// api.OpenWeatherMap.com

public class Coord {

    private double lon;
    private double lat;

    public Coord(double lon, double lat) {
        setLon(lon);
        setLat(lat);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
