package com.example.maps.gpslocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GetGPSLocation(private val context: Context) {

    interface LocationCallback {
        fun onLocationResult(latitude: Double, longitude: Double)
        fun onLocationFailed(error: String)
    }

    private var locationCallback: LocationCallback? = null
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun setLocationCallback(callback: LocationCallback) {
        locationCallback = callback
    }

    fun requestLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationCallback?.onLocationFailed("Location permission not granted")
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    locationCallback?.onLocationResult(location.latitude, location.longitude)
                } else {
                    locationCallback?.onLocationFailed("Location not available")
                }
            }
            .addOnFailureListener { e ->
                locationCallback?.onLocationFailed("Failed to get location: ${e.message}")
            }
    }
}


