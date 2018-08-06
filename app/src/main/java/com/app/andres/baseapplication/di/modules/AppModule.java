package com.app.andres.baseapplication.di.modules;

import android.app.Application;
import android.content.Context;

import com.app.andres.baseapplication.providers.ResourceProvider;
import com.app.andres.baseapplication.api.config.ApiConfig;
import com.app.andres.baseapplication.eventbus.RxBus;
import com.app.andres.baseapplication.managers.preferences.PrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return app;
    }

    @Provides
    @Singleton
    public PrefsManager preferenceManager(Context context) {
        PrefsManager.init(context);
        return PrefsManager.getInstance();
    }

    @Provides
    @Singleton
    public ApiConfig getApiConfig(Context context, PrefsManager prefsManager) {
        return new ApiConfig(context, prefsManager);
    }

    @Provides
    @Singleton
    public ResourceProvider resourceProvider(Context context) {
        return new ResourceProvider(context);
    }


    @Provides
    @Singleton
    public RxBus rxBus() {
        return RxBus.getInstance();
    }
}
