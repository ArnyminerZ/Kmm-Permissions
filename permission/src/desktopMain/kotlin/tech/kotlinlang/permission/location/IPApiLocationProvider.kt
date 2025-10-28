package tech.kotlinlang.permission.location

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import java.io.IOException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object IPApiLocationProvider : IPLocationProvider {
    private val client = HttpClient()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun fetchIPLocation(): Pair<Double, Double> {
        val httpResponse = client.get("https://ipapi.co/json")
        if (httpResponse.status == HttpStatusCode.OK) {
            val body = httpResponse.bodyAsText()
            val (latitude, longitude) = json.decodeFromString(IPApiResponse.serializer(), body)
            return latitude to longitude
        } else {
            throw IOException("Failed to fetch IP location: ${httpResponse.status}")
        }
    }

    // There are more fields available, but we only care about latitude and longitude
    @Serializable
    data class IPApiResponse(
        val latitude: Double,
        val longitude: Double
    )
}
