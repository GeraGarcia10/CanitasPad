package com.lggpmlrg.canitaspadv2_a

import android.app.Application
import com.lggpmlrg.canitaspadv2_a.data.AppContainer
import com.lggpmlrg.canitaspadv2_a.data.AppDataContainer

class NotasApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}