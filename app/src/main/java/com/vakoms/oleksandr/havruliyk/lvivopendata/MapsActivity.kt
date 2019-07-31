package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var mapFragment: SupportMapFragment

    companion object {
        const val TAG = "MapsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    private fun addMarker(latLng: LatLng, title: String) {
        googleMap?.let {
            it.addMarker(
                MarkerOptions().position(latLng)
                    .title(title)
            )
            it.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            Log.i(TAG, "add Marker ($title (${latLng.latitude}, ${latLng.longitude})")
        }
    }
}
