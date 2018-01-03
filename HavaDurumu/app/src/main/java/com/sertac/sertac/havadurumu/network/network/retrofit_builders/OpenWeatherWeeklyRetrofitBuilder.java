package com.sertac.sertac.havadurumu.network.network.retrofit_builders;

import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.WeeklyOpenWeatherServiceGenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherWeeklyRetrofitBuilder {

    private static WeeklyOpenWeatherServiceGenerator weeklyOpenWeatherServiceGenerator;

    public static WeeklyOpenWeatherServiceGenerator getWeeklyOpenWeatherServiceGenerator() {
        if (weeklyOpenWeatherServiceGenerator == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.OPEN_WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weeklyOpenWeatherServiceGenerator = retrofit.create(WeeklyOpenWeatherServiceGenerator.class);
            return weeklyOpenWeatherServiceGenerator;
        } else
            return weeklyOpenWeatherServiceGenerator;
    }

}
