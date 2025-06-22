package tech.kotlinlang.permission.location

interface LocationHelper {
    suspend fun fetchLastKnownLocation(): LocationRequestResult
    suspend fun fetchCurrentLocation(): LocationRequestResult
}