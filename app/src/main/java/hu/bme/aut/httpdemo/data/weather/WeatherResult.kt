package hu.bme.aut.httpdemo.data.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResult(
    @SerialName("weather")
    val weather: List<WeatherDescription>,

    @SerialName("main")
    val main: MainWeather,

    @SerialName("name")
    val cityName: String
)

@Serializable
data class WeatherDescription(
    @SerialName("description")
    val description: String,

    @SerialName("icon")
    val icon: String
)

@Serializable
data class MainWeather(
    @SerialName("temp")
    val temperature: Double,

    @SerialName("feels_like")
    val feelsLike: Double,

    @SerialName("humidity")
    val humidity: Int
)
