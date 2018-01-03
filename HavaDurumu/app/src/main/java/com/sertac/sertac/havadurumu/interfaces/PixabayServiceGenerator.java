package com.sertac.sertac.havadurumu.interfaces;

import com.sertac.sertac.havadurumu.network.network.pixabay_network_model.Pixabay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayServiceGenerator {
    @GET("/api/")
    Call<Pixabay> getPixabay(@Query("key") String apiKey, @Query("q") String locationName, @Query("orientation") String pictureOrientation,
                             @Query("category") String pictureCategory,@Query("image_type") String imageType);
}
