package com.app.andres.baseapplication.di.modules;

import com.app.andres.baseapplication.api.config.ApiConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.net.sip.SipErrorCode.TIME_OUT;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Gson gson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(ApiConfig apiConfig, Gson gson) {

        OkHttpClient.Builder httpClient = getHttpClientBuilder(apiConfig);

        return getRetrofitBuilder(httpClient.build(), "andrespaez90.com", gson).build();
    }

    private OkHttpClient.Builder getHttpClientBuilder(ApiConfig apiConfig) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        if (apiConfig.DEBUG) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            clientBuilder.addInterceptor(logging);
        }

        return clientBuilder;
    }

    private Retrofit.Builder getRetrofitBuilder(OkHttpClient httpClient, String url, Gson gson) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

}
