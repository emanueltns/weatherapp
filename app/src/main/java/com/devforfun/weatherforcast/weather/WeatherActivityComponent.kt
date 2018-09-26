package com.devforfun.weatherforcast.weather

import com.devforfun.weatherforcast.di.NetworkModule
import com.devforfun.weatherforcast.di.WeatherApplicationScope
import dagger.Component

@Component(dependencies = [NetworkModule::class])
@WeatherApplicationScope
interface WeatherActivityComponent{


}