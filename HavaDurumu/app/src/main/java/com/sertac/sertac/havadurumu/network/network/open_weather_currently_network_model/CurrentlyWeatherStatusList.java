package com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CurrentlyWeatherStatusList {

    @SerializedName("dt")
    public Long dt;

    @SerializedName("main")
    public CurrentlyMain main;

    @SerializedName("weather")
    public ArrayList<CurrentlyWeather> weather;

    @SerializedName("wind")
    public CurrentlyWind wind;

    @SerializedName("clouds")
    public CurrentlyClouds clouds;
}
