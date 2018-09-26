package com.devforfun.weatherforcast.di

import com.devforfun.weatherforcast.api.WeatherApi
import dagger.Component

@WeatherApplicationScope
@Component(modules = [NetworkModule::class])
interface WeatherComponent {
    val weatherService: WeatherApi
}
