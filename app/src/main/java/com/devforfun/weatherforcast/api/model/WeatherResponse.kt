package com.devforfun.weatherforcast.api.model

data class WeatherResponse(val coord : Coordinates,
                      val weather : List<Weather>,
                      val base : String,
                      val main : MainData,
                      val wind : Wind,
                      val clouds : Clouds,
                      val dt : Long,
                      val sys : Sys,
                      val id : Long,
                      val name : String,
                      val cod : Int)

data class Coordinates(val lon : Double, val Double : Int)

data class Weather(val id : Int,
              val main : String,
              val description: String,
              val icon : String)

data class MainData(val temp : Double,
               val pressure : Int,
               val humidity : Int,
               val temp_main : Double,
               val temp_max : Double,
               val sea_level : Double,
               val grnd_level : Double)

data class Wind(val speed : Double, val deg : Int)

data class Clouds(val all : Int)

data class Sys(val message : Double,
              val country : String,
              val sunrise : Long,
              val sunset : Long)





