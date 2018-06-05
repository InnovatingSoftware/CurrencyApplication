package com.app.andres.baseapplication.di.components;

import android.content.Context;

import com.app.andres.baseapplication.Providers.ResourceProvider;
import com.app.andres.baseapplication.api.config.ApiConfig;
import com.app.andres.baseapplication.di.modules.ApiModule;
import com.app.andres.baseapplication.di.modules.AppModule;
import com.app.andres.baseapplication.eventbus.RxBus;
import com.app.andres.baseapplication.managers.preferences.PrefsManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    PrefsManager preferenceManager();

    RxBus rxBus();

    Context context();

    Gson gson();


    /**
     * Apis
     **/

    ApiConfig getApiConfig();

    Retrofit retrofit();

    /**
     * Providers
     */

    ResourceProvider resourceProvider();


}
