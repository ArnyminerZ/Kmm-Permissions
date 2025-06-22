package tech.kotlinlang.geocoder

import tech.kotlinlang.permission.PermissionInitiation

actual fun getGeocoderHelper(): GeocoderHelper {
    val permissionInitiation = PermissionInitiation.getInstance()
    return AndroidGeocoderHelper(permissionInitiation.getActivity())
}