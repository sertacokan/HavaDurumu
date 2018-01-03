package com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model;


import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WeeklyWeatherStatusList {

    @SerializedName("weather")
    public ArrayList<WeeklyWeather> weather;

    @SerializedName("temp")
    public WeeklyTemp temp;

    @SerializedName("humidity")
    public int humidity;

    @SerializedName("speed")
    public double speed;

    @SerializedName("dt")
    public long dt;

    @SerializedName("clouds")
    public int clouds;

}
