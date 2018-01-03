package com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrentlyWeatherStatus {

    @SerializedName("list")
    public ArrayList<CurrentlyWeatherStatusList> list;
}
