package com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model;

import com.google.gson.annotations.SerializedName;

public class WeeklyWeather {

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}
