package com.sertac.sertac.havadurumu.interfaces;

import com.sertac.sertac.havadurumu.network.network.open_weather_weekly_network_model.WeeklyWeatherStatus;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeeklyOpenWeatherServiceGenerator {

    @POST("/data/2.5/forecast/daily")
    Call<WeeklyWeatherStatus> getWeeklyWeatherStatus(@Query("q") String sehir, @Query("cnt") int alinanGunSayisi,@Query("mode") String mode
            ,@Query("units") String units,@Query("APPID") String apiNo);

}
