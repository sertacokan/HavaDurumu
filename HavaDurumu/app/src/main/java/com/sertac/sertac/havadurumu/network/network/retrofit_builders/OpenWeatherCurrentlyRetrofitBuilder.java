package com.sertac.sertac.havadurumu.network.network.retrofit_builders;

import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.CurrentlyOpenWeatherServiceGenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OpenWeatherCurrentlyRetrofitBuilder {

    private static CurrentlyOpenWeatherServiceGenerator currentlyOpenWeatherServiceGenerator;

    public static CurrentlyOpenWeatherServiceGenerator getCurrentlyOpenWeatherServiceGenerator() {
        if (currentlyOpenWeatherServiceGenerator == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.OPEN_WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            currentlyOpenWeatherServiceGenerator = retrofit.create(CurrentlyOpenWeatherServiceGenerator.class);
            return currentlyOpenWeatherServiceGenerator;
        } else
            return currentlyOpenWeatherServiceGenerator;
    }
}
