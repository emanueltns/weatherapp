package com.devforfun.weatherforcast.weather

import com.devforfun.weatherforcast.di.NetworkModule
import com.devforfun.weatherforcast.di.WeatherApplicationScope
import com.devforfun.weatherforcast.weather.viewmodel.WeatherViewModel
import dagger.Component

@Component(modules = [WeatherActivityModule::class], dependencies = [NetworkModule::class])
@WeatherApplicationScope
interface WeatherActivityComponent {

    fun injectWeatherViewModel(weatherViewModel: WeatherViewModel)
}