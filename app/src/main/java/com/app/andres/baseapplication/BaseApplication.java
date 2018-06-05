package com.app.andres.baseapplication;

import android.app.Application;

import com.app.andres.baseapplication.di.components.AppComponent;
import com.app.andres.baseapplication.di.components.DaggerAppComponent;
import com.app.andres.baseapplication.di.modules.AppModule;
import com.app.andres.baseapplication.managers.NotificationsManagerApp;

public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        updateDagger();
        NotificationsManagerApp.createNotificationChannels();
    }


    public static BaseApplication get() {
        return sInstance;
    }

    public void updateDagger() {

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
