package com.fanaye.sorantravel.ui.weather.model;

public class Wind {

    private double speed;

    // Wind direction, degrees (meteorological)
    private int deg;

    public Wind(int speed, int deg) {
        setSpeed(speed);
        setDeg(deg);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
