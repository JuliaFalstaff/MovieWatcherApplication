package com.example.myapplication.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGoogleMapsBinding
import com.example.myapplication.model.data.Movie

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_google_maps.*
import java.io.IOException

class GoogleMapsFragment : Fragment() {
    private lateinit var map: GoogleMap
    private val markers: ArrayList<Marker> = arrayListOf()
    private lateinit var binding: FragmentGoogleMapsBinding
    private lateinit var movieBundle: Movie
    private lateinit var initialPlace: LatLng

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        initialPlace = getLocationFromAddress(movieBundle.production_countries?.get(0)?.name)

        googleMap.addMarker(
            MarkerOptions().position(initialPlace).title(getString(R.string.marker_start))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(initialPlace))
        googleMap.setOnMapLongClickListener {
            getAddressAsync(it)
            addMarkerToArray(it)
            drawLine()
        }
        activateMyLocation(googleMap)
    }

    private fun getLocationFromAddress(address: String?): LatLng {
        val geoCoder = Geocoder(context)
        if (address != null) {
            geoCoder.getFromLocationName(address, 1).also {
                return LatLng(it[0].latitude, it[0].longitude)
            }
        } else
            return LatLng(54.98296090807848, 82.89598294067132)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable<Movie>(GoogleMapsFragment.BUNDLE_EXTRA) ?: Movie()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        initSearchByAddress()
    }

    private fun getAddressAsync(latLng: LatLng) {
        context?.let {
            val geoCoder = Geocoder(it)
            Thread {
                try {
                    val addresses = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    binding.apply {
                        textAddressMap.post {
                            textAddressMap.text = addresses.first().getAddressLine(0)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun addMarkerToArray(latLng: LatLng) {
        val marker = setMarker(latLng, markers.size.toString(), R.drawable.ic_map_pin)
        marker?.let { markers.add(it) }
    }

    private fun setMarker(location: LatLng, searchText: String, resourceId: Int): Marker? {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(searchText)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )
    }

    private fun drawLine() {
        val last: Int = markers.size - 1
        if (last >= 1) {
            val previous: LatLng = markers[last - 1].position
            val current: LatLng = markers[last].position
            map.addPolyline(
                PolylineOptions()
                    .add(previous, current)
                    .color(Color.RED)
                    .width(ZOOM_DRAW_LINE)
            )
        }
    }

    private fun initSearchByAddress() {
        binding.buttonSearchMap.setOnClickListener {
            val geoCoder = Geocoder(it.context)
            val searchText = searchAddressMap.text.toString()
            Thread {
                try {
                    val addresses = geoCoder.getFromLocationName(searchText, 1)
                    if (addresses.size > 0) {
                        goToAddress(addresses, it, searchText)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun goToAddress(addresses: List<Address>, it: View?, searchText: String) {
        val location = LatLng(
            addresses.first().latitude,
            addresses.first().longitude
        )
        view?.post {
            setMarker(location, searchText, R.drawable.ic_map_marker)
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    location,
                    ZOOM_CAMERA
                )
            )
        }
    }

    private fun activateMyLocation(googleMap: GoogleMap) {
        context?.let {
            val isPermissionGranted =
                ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED
            googleMap.isMyLocationEnabled = isPermissionGranted
            googleMap.uiSettings.isMyLocationButtonEnabled = isPermissionGranted
        }
    }

    companion object {
        private const val ZOOM_DRAW_LINE = 5f
        private const val ZOOM_CAMERA = 15f

        const val BUNDLE_EXTRA = "movie"


        fun newInstance(bundle: Bundle): GoogleMapsFragment {
            val fragment = GoogleMapsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}