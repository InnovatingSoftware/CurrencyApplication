package com.app.andres.baseapplication.viewModels.models

import android.os.Bundle

class StartActivityModel(val activity: Class<*>) {

    var bundle: Bundle? = null

    constructor(activity: Class<*>, bundle: Bundle) : this(activity) {
        this.bundle = bundle
    }

}