package com.app.andres.baseapplication.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class GeneralUtils {

    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static int[] getScreenSize(Context context) {

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics metrics = new DisplayMetrics();

        windowManager.getDefaultDisplay().getMetrics(metrics);

        int x = metrics.widthPixels;
        int y = metrics.heightPixels;

        int[] screenSize = new int[2];

        screenSize[0] = Math.min(x, y);
        screenSize[1] = Math.max(x, y);

        return screenSize;
    }
}
