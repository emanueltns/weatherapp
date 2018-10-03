package com.devforfun.weatherforcast.weather

import com.devforfun.weatherforcast.di.NetworkModule
import com.devforfun.weatherforcast.di.WeatherActivityScope
import com.devforfun.weatherforcast.di.WeatherApplicationScope
import com.devforfun.weatherforcast.di.WeatherComponent
import dagger.Component

@Component(dependencies = [WeatherComponent::class])
@WeatherActivityScope
interface WeatherActivityComponent {

    fun injectWeatherRepository(repository: WeatherRepository)
}