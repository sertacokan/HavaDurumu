package com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeeklyWeatherStatus {

    @SerializedName("list")
    public ArrayList<WeeklyWeatherStatusList> list;

    @SerializedName("city")
    public WeeklyCity city;
}
