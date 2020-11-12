package com.example.covid19appretrotest.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidApi {
    @GET("v3/covid-19/all/")
    Call<GlobalModel> getGlobalStats();

    @GET("v3/covid-19/countries/{country}?strict=true")
    Call<CountryModel> getCountryStats(@Path("country") String country);

    @GET("v3/covid-19/countries/")
    Call<List<CountryModel>> getCountryStats();
}
