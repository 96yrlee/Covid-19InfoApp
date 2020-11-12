package com.example.covid19appretrotest.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static final String BASE_URL = "https://disease.sh/";

    //create the api logging stuff
    private static OkHttpClient createDefaultOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient().newBuilder()
                                .addInterceptor(interceptor)
                                .build();
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //NEW STUFF
            .client(createDefaultOkHttpClient())
            .build();

    // creating method
    public static <S> S createApi(Class<S> apiInterfaceClass){
        return retrofit.create( apiInterfaceClass);
    }
}
