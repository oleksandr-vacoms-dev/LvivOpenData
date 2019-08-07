package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vakoms.oleksandr.havruliyk.lvivopendata.R
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MapViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.back_button.*
import kotlinx.android.synthetic.main.item_list.*
import javax.inject.Inject

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val DEFAULT_ZOOM = 14.0f
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MapViewModel

    private var googleMap: GoogleMap? = null
    private lateinit var mapFragment: SupportMapFragment

    private val records = mutableListOf<MapRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        initView()
        initMapFragment()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        label_view.text = resources.getString(R.string.map_label)

        back_button.setOnClickListener { finish() }
    }

    private fun initMapFragment() {
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MapViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.getMapsRecords()?.observe(
            this,
            androidx.lifecycle.Observer
            {
                refreshRecordsAndView(it)
            })
    }

    private fun refreshRecordsAndView(newRecord: MapRecord) {
        records.add(newRecord)
        showMarkers()
    }

    private fun showMarkers() {
        for (r: MapRecord in records) {
            addMarker(r.latLng, r.adrress.title)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    private fun addMarker(latLng: LatLng, title: String) {
        googleMap?.addMarker(MarkerOptions().position(latLng).title(title))
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM)
        )
    }
}