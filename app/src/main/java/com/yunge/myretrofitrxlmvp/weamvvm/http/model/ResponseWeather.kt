package com.yunge.myretrofitrxlmvp.weamvvm.http.model

class ResponseWeather<T>(var error_code: Int, var reason: String, var result: T)


//class OptionT<T>(val value: T)


data class Result(var data: Data) {


}

data class Data(var weather: List<Weather>) { //var realtime: Realtime

//            //实时的天气
//            class Realtime(var city_name: String,var time: String,var weather: Weather){
//                class Weather(
//                    @SerializedName("temperature") var tmp:String,
//                    var info: String)
//            }


}

data class Weather(
    var date: String,
    var week: String,
    var nongli: String
)