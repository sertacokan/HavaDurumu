package com.sertac.sertac.havadurumu.interfaces;

import com.sertac.sertac.havadurumu.network.network.open_weather_currently_network_model.CurrentlyWeatherStatus;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface CurrentlyOpenWeatherServiceGenerator {

    @POST("/data/2.5/forecast")
    Call<CurrentlyWeatherStatus> getCurrentlyWeatherStatus(@Query("q") String sehir,@Query("mode") String mode
            ,@Query("units") String units, @Query("APPID") String apiNo);

}
