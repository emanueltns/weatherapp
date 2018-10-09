package com.devforfun.weatherforcast.weather

import com.devforfun.weatherforcast.WeatherApplication
import com.devforfun.weatherforcast.api.WeatherApi
import com.devforfun.weatherforcast.api.model.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository{

    var weatherApi : WeatherApi = WeatherApplication.weatherComponent.weatherService

    fun getWeather(lat : String, lon : String) : Observable<WeatherResponse> {
        return Observable.create {
            subscriber ->
            val callResponse  = weatherApi.getWeatherOnLocation(lat, lon)
            val response = callResponse.execute()

            if(response.isSuccessful) {
                subscriber.onNext(response.body()!!)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }


}