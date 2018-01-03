package com.sertac.sertac.havadurumu.models;

public class MainGridModel {

    private String cityName;
    private String icon;
    private String temperature;

    public MainGridModel(String cityName, String icon, String temperature) {
        this.cityName = cityName;
        this.icon = icon;
        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public String getActivity() {
        return icon;
    }

    public String getDate() {
        return temperature;
    }


}
