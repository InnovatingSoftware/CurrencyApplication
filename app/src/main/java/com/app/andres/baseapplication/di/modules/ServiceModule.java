package com.app.andres.baseapplication.di.modules;

import android.text.TextUtils;

import com.app.andres.baseapplication.BaseApplication;
import com.app.andres.baseapplication.R;
import com.app.andres.baseapplication.api.config.ApiConfig;
import com.app.andres.baseapplication.api.services.CountryServices;
import com.app.andres.baseapplication.api.services.ExchangeServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.app.andres.baseapplication.api.config.ApiConfig.SERVICE_TIME_OUT;


@Module
public class ServiceModule {


    @Provides
    @Singleton
    public ExchangeServices exchangeServices(@Named("currencyExchange") Retrofit retrofit) {
        return retrofit.create(ExchangeServices.class);
    }

    @Provides
    @Singleton
    public CountryServices copntryInformationServices(@Named("countryInformation") Retrofit retrofit) {
        return retrofit.create(CountryServices.class);
    }

    @Provides
    @Singleton
    public Gson gson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(ApiConfig apiConfig, Gson gson) {

        OkHttpClient.Builder httpClient = getHttpClientBuilder(apiConfig)
                .addInterceptor(addCurrencyAccessToken());

        return getRetrofitBuilder(httpClient.build(), "http://andrespaez90.com", gson).build();
    }

    @Provides
    @Singleton
    @Named("countryInformation")
    public Retrofit getCountryRetrofit(ApiConfig apiConfig, Gson gson){

        OkHttpClient.Builder httpClient = getHttpClientBuilder(apiConfig)
                .addInterceptor(addCurrencyAccessToken());

        return getRetrofitBuilder(httpClient.build(), "https://restcountries.eu/rest/v2", gson).build();
    }


    @Provides
    @Singleton
    @Named("currencyExchange")
    public Retrofit CurrencyExchangeRetrofit(ApiConfig apiConfig, Gson gson) {

        OkHttpClient.Builder httpClient = getHttpClientBuilder(apiConfig)
                .addInterceptor(addCurrencyAccessToken());

        return getRetrofitBuilder(httpClient.build(), "http://apilayer.net/api/", gson).build();
    }

    private OkHttpClient.Builder getHttpClientBuilder(ApiConfig apiConfig) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(SERVICE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(SERVICE_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(SERVICE_TIME_OUT, TimeUnit.SECONDS);

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

    private Interceptor addCurrencyAccessToken() {

        return chain -> {

            Request.Builder newBuilder = chain.request().newBuilder();

            newBuilder.method(chain.request().method(), chain.request().body());

            String token = BaseApplication.get().getString(R.string.exchange_api);

            if (!TextUtils.isEmpty(token)) {
                newBuilder.url(chain.request().url().newBuilder().addQueryParameter(ApiConfig.ACCESS_KEY, token)
                        .build());
            }

            return chain.proceed(newBuilder.build());
        };
    }

}
