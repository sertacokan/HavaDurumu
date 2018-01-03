package com.sertac.sertac.havadurumu.network.network.retrofit_builders;

import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.PixabayServiceGenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PixabayRetrofitBuilder {

    private static PixabayServiceGenerator pixabayServiceGenerator;

    public static PixabayServiceGenerator getPixabayServiceGenerator(){
        if (pixabayServiceGenerator ==null){
            Retrofit retrofit=new Retrofit.Builder()
                              .baseUrl(Constants.PIXABAY_BASE_URL)
                              .addConverterFactory(GsonConverterFactory.create())
                              .build();
            pixabayServiceGenerator =retrofit.create(PixabayServiceGenerator.class);
            return pixabayServiceGenerator;
        }
        else
            return pixabayServiceGenerator;
    }




}
