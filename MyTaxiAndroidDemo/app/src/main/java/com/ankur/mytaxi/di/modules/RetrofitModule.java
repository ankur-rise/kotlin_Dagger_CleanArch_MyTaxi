package com.ankur.mytaxi.di.modules;


import com.ankur.mytaxi.network.Apis;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://fake-poi-api.mytaxi.com/";

    public RetrofitModule() {

    }

    @Provides
    @Singleton
    public Apis apiClient(Retrofit retrofit) {
        return retrofit.create(Apis.class);
    }

    @Singleton
    @Provides
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    /*@Singleton
    @Provides
    public OkHttpClient getClient()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }*/

    @Singleton
    @Provides
    public GsonConverterFactory gsonConverterFactory()
    {
        return GsonConverterFactory.create();
    }

}
