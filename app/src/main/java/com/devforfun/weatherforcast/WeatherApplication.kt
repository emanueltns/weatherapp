package com.devforfun.weatherforcast

import android.app.Application
import com.devforfun.weatherforcast.di.ContextModule
import com.devforfun.weatherforcast.di.WeatherComponent
import timber.log.Timber

class WeatherApplication : Application() {

    lateinit var weatherComponent : WeatherComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        weatherComponent = DaggerWeatherComponent.builder().contextModule(ContextModule(this)).build();
    }

}