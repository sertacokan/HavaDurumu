package com.sertac.sertac.havadurumu.network.network.retrofit_builders;

import com.sertac.sertac.havadurumu.constants.Constants;
import com.sertac.sertac.havadurumu.interfaces.AutoCompleteServicesGenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AutoCompleteRetrofitBuilder {

    private static AutoCompleteServicesGenerator autoCompleteServicesGenerator;

    public static AutoCompleteServicesGenerator getAutoCompleteServicesGenerator(){
        if (autoCompleteServicesGenerator ==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.AUTOCOMPLETE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            autoCompleteServicesGenerator =retrofit.create(AutoCompleteServicesGenerator.class);
            return autoCompleteServicesGenerator;
        }
        else
            return autoCompleteServicesGenerator;

    }

}
