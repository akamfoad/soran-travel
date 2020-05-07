package com.fanaye.sorantravel.ui.weather.model;

public class Main {

    private float temp;
    private float pressure;
    private float humidity;
    private float temp_min;
    private float temp_max;

    public Main(int temp, int pressure, int humidity, int temp_min, int temp_max) {
        setTemp(temp);
        setPressure(pressure);
        setHumidity(humidity);
        setTemp_min(temp_min);
        setTemp_max(temp_max);
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(int temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(int temp_max) {
        this.temp_max = temp_max;
    }
}
