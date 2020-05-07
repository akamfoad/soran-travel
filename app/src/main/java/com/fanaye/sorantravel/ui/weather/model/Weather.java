package com.fanaye.sorantravel.ui.weather.model;

// Second paramater of JSON from apiOpenWeatherMap.com
public class Weather {

//    for getting images from the openwethermap.com
//    String imgStrURL = "http://openweathermap.org/img/wn/01n@2x.png";
//    URL Imageurl = new URL(imgStrURL);

    // Weather condition id
    private int id;

    // Group of weather parameters (Rain, Snow, Extreme etc.)
    private String main;

    //Weather condition within the group
    private String description;

    //private Image icon;
    private String icon;

    public Weather(int id, String main, String description, String icon) {

        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
