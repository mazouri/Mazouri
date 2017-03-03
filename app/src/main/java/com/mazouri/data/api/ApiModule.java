package com.mazouri.data.api;

import com.google.gson.Gson;
import com.mazouri.base.injector.scope.PerApplication;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ApiModule {
    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.imgur.com/3/");
    public static final HttpUrl INTERNAL_BASE_URL = HttpUrl.parse("http://192.168.1.100:9000");
    public static final HttpUrl INTERNAL_API_URL = HttpUrl.parse("http://192.168.1.100:9000/api/v1/");

    @Provides
    @PerApplication
    Retrofit provideRetrofit(HttpUrl baseUrl, @Named("Api") OkHttpClient client, Gson gson) {

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @PerApplication
    static OkHttpClient.Builder createApiClient(OkHttpClient client) {
        return client.newBuilder();
    }

}
