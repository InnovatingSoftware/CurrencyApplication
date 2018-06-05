package com.app.andres.baseapplication.api.config;

import android.content.Context;

import com.app.andres.baseapplication.managers.preferences.PrefsManager;

public class ApiConfig {

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_RT = "Authorization-rt";
    public static final String AUTHORIZATION_WARRANTY = "x-api-key";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEVICE_ID = "DeviceID";
    public static final String APP_VERSION = "APP-VERSION-NEW";
    public static final String MULTI_PART = "multipart/form-data";
    public static final String COUNTRY = "country";

    public static final boolean DEBUG = true;

    private Context context;

    private PrefsManager prefsManager;
    private String resourceUrl;
    private String firebaseUrl;
    private String microServicesUrl;

    public ApiConfig(Context context, PrefsManager prefsManager) {
        this.context = context;
        this.prefsManager = prefsManager;
    }


}
