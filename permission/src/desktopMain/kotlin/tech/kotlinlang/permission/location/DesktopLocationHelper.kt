package tech.kotlinlang.permission.location

import java.io.IOException

class DesktopIPLocationHelper(private val provider: IPLocationProvider) : LocationHelper {
    override suspend fun fetchLastKnownLocation(): LocationRequestResult {
        return try {
            provider.fetchIPLocation()
        } catch (_: IOException) {
            LocationRequestResult.NoLastLocationFound
        }
    }
}
