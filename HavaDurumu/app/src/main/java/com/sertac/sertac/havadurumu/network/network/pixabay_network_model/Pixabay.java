package com.sertac.sertac.havadurumu.network.network.pixabay_network_model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pixabay {
    @SerializedName("hits")
    public ArrayList<PixabayPhoto> pixabayPhotos;
}
