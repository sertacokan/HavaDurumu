package com.sertac.sertac.havadurumu.network.network.autocomplete_network_model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AutoCompletePlace {

    @SerializedName("predictions")
    public ArrayList<AutoCompletePlaceList> predictions;
}
