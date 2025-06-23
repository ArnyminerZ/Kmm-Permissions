package tech.kotlinlang.geocoder

actual fun getGeocoderHelper(): GeocoderHelper {
    return IosGeocoderHelper()
}