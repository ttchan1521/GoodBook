package com.example.goodbook

import android.app.Application
import com.example.forage.data.GoodBookDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [GoodBookDatabase]
 */
class GoodBookApplication : Application() {

    // Provide a ForageDatabase value by lazy
    val database: GoodBookDatabase by lazy { GoodBookDatabase.getDatabase(this) }
}