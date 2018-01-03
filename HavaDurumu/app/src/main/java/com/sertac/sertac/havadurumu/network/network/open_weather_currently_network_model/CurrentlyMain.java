package com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model;

import com.google.gson.annotations.SerializedName;

public class CurrentlyMain {

    @SerializedName("temp")
    public double temp;

    @SerializedName("humidity")
    public int humidity;
}
