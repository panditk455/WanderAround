package hu.bme.aut.httpdemo.network


import retrofit2.http.GET
import retrofit2.http.Query

// https://api.exchangerate-api.com/v4/latest/USD
// https://api.openweathermap.org/

//Host: https://api.exchangerate-api.com/
//Path: v4/latest/USD
//Query params: we do not have now...


interface WeatherAPI {

    @GET("data/2.5/weather")
    suspend fun getRates(
        @Query("q") city: String,           // City name
        @Query("appid") apiKey: String,    // API key
        @Query("units") units: String = "metric"
    )

}
