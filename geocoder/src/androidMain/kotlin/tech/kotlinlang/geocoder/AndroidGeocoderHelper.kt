package tech.kotlinlang.geocoder

import android.content.Context
import android.location.Geocoder
import java.util.Locale

class AndroidGeocoderHelper(
    private val context: Context,
) : GeocoderHelper {
    override suspend fun fetchGeocoderData(latitude: Double, longitude: Double): GeocoderData? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val address = addresses?.firstOrNull()
            GeocoderData(
                city = address?.locality,
                state = address?.adminArea,
                country = address?.countryName,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}