package com.app.andres.baseapplication.utils

import android.os.Build


    fun hasKitKat(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT


    fun hasLollipop(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP


    fun hasMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M


    fun hasNougat(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N




