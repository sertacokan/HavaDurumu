package com.sertac.sertac.havadurumu.interfaces;

import com.sertac.sertac.havadurumu.network.network.autocomplete_network_model.AutoCompletePlace;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AutoCompleteServicesGenerator {

    @POST("/maps/api/place/autocomplete/json")
    Call<AutoCompletePlace> getAutoCompleteList(@Query("input") String locationName,
                                                @Query("types") String placeTypes,
                                                @Query("language") String language,
                                                @Query("key") String apiKey);

}
