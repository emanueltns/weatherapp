package com.devforfun.weatherforcast.weather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.devforfun.weatherforcast.api.model.WeatherResponse
import com.devforfun.weatherforcast.weather.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    private val apiManager = WeatherRepository()
    val weatherSuccess = MutableLiveData<WeatherResponse>()
    val weatherError = MutableLiveData<String>()

    fun getWeatherDisposable() : Disposable {
        return apiManager.getWeather("46.771210", "23.623634")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherSuccess.postValue(it)
                }, {
                    weatherError.postValue(it.message)
                })
    }


}