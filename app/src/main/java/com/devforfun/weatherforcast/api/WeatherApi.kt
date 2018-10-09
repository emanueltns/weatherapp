package com.devforfun.weatherforcast.api

import com.devforfun.weatherforcast.api.model.WeatherResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather/")
    fun getWeatherOnLocation(@Query("lat") lat: String,
                             @Query("lon") lon: String,
                             @Query("appid") appId : String) : Call<WeatherResponse>

}
