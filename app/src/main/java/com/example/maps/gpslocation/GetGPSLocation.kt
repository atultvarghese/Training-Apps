import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object GetGPSLocation {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getLatLong(context: Context, callback: (lat: Double, long: Double) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission not granted, handle accordingly
            callback.invoke(0.0, 0.0)
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        callback.invoke(location.latitude, location.longitude)
                    } else {
                        // Location not available, handle accordingly
                        callback.invoke(0.0, 0.0)
                    }
                }
                .addOnFailureListener { e ->
                    // Failed to get location, handle accordingly
                    callback.invoke(0.0, 0.0)
                }
        }
    }
}
