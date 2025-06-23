package tech.kotlinlang.geocoder

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLPlacemark
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class IosGeocoderHelper() : GeocoderHelper {
    override suspend fun fetchGeocoderData(latitude: Double, longitude: Double): GeocoderData? {
        return suspendCancellableCoroutine { continuation ->
            val geocoder = CLGeocoder()
            val clLocation = CLLocation(latitude = latitude, longitude = longitude)

            geocoder.reverseGeocodeLocation(clLocation) { placemarks, error ->
                if (error != null) {
                    continuation.resumeWithException(Exception(error.localizedDescription))
                } else {
                    val placemark = placemarks?.firstOrNull() as? CLPlacemark
                    val city = placemark?.locality
                    val state = placemark?.administrativeArea
                    val country = placemark?.country
                    continuation.resume(GeocoderData(city = city, state = state, country = country))
                }
            }
        }
    }
}