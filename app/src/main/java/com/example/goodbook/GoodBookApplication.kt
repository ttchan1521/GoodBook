package com.example.goodbook

import android.app.Application
import com.example.goodbook.data.GoodBookDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [GoodBookDatabase]
 */
class GoodBookApplication : Application() {

    // Provide a GoodBookDatabase value by lazy
    val database: GoodBookDatabase by lazy { GoodBookDatabase.getInstance(this)}
}