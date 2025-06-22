package tech.kotlinlang.geocoder

interface GeocoderHelper {

    suspend fun fetchGeocoderData(latitude: Double, longitude: Double): GeocoderData?

    companion object {
        private var geocoderHelper: GeocoderHelper? = null
        fun getInstance(): GeocoderHelper {
            return geocoderHelper ?: getGeocoderHelper().also {
                geocoderHelper = it
            }
        }
    }
}

expect fun getGeocoderHelper(): GeocoderHelper