package com.example.maps

import GetGPSLocation
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.maps.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var requestcode = 1
    var lattitude = 11.453121
    var longitude = 76.061947

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {


        mMap = googleMap
//        mMap.isMyLocationEnabled = true
        // Add a marker in Sydney and move the camera

        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        val myHome = LatLng(lattitude, longitude)
        mMap.addMarker(MarkerOptions().position(myHome).title("Marker in Sydney"))
        GetGPSLocation.getLatLong(this) { lat, long ->
            // Handle latitude and longitude here
            val currentLocation = LatLng(lat, long)
            Log.i("latlong","Latitude: $lattitude, Longitude: $longitude")
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))

//            mMap.moveCamera(CameraUpdateFactory.newLatLng(myHome))
        }
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myHome, 20f))

    }
}