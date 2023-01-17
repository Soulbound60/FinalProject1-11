package com.example.finalproject1_11.Views

import android.app.Application
import android.content.DialogInterface
import android.location.Location
import android.location.LocationRequest
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject1_11.R
import com.example.finalproject1_11.ViewModel.LatLngFlag
import com.example.finalproject1_11.ViewModel.MainViewModel

import com.example.finalproject1_11.databinding.FragmentMapBinding
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MapFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentMapBinding
    lateinit var mMap: GoogleMap
    lateinit var x: LatLngFlag
    lateinit var viewModel: MainViewModel
    var checlClear = false

    //1
    private var mMap1: GoogleMap? = null
    internal lateinit var mLastLocation: Location
    internal var mCurrLocationMarker: Marker? = null
    internal var mGoogleApiClient: GoogleApiClient? = null
    internal lateinit var mLocationRequest: LocationRequest


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        // initiate viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // initiate MapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // initiate AutoComplete
        //var  ="AIzaSyCYeqxPF4oX57ZOZKEQ_6oc2P2o-Z4eOBk"
//        var apiKey : String = getString(R.string.api_key)
//        if (!Places.isInitialized()){
//            Places.initialize(context,apiKey)
//        }
//        var placesClient = Places.createClient(requireContext())
//
//        // Initialize the AutocompleteSupportFragment.
//
//
//        var fragmentManager: FragmentManager = (activity as FragmentActivity).supportFragmentManager
//        val autocompleteFragment = fragmentManager.findFragmentById(R.id.autocomplete_fragment)
//                    as AutocompleteSupportFragment
//
//        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
//            LatLng(21.444815, 39.820301),
//                    LatLng(21.444815, 39.820301)
//        ))
//
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))
//
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
//            override fun onPlaceSelected(place: Place) {
//                // TODO: Get info about the selected place.
//                Log.i("TAG", "Place: ${place.name}, ${place.id}")
//            }
//
//            override fun onError(status: Status) {
//                // TODO: Handle the error.
//                Log.i("TAG", "An error occurred: $status")
//            }
//        })


        return binding.root
    }

    //
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var makkah = LatLng(21.444815, 39.820301)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(makkah))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 2000, null)
        mMap.setOnMapClickListener {
            x = LatLngFlag(it.latitude, it.longitude, true)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
            if (checlClear == false) {
                mMap.addMarker(MarkerOptions().position(it).title("Here ?"))
                checlClear = true
                Log.d("TAG", "marker done $checlClear")
            } else {
                putMarker(mMap)
                Log.d("TAG", "marker deleted $checlClear")
            }
//            binding.fBtn.setOnClickListener {
//                if (checlClear){
//                    //Toast.makeText(requireContext(),"Pup Up Dialog", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(requireContext(),"Pls Pick a Location", Toast.LENGTH_SHORT).show()
//                }
//            }

        }
    }

    fun putMarker(googleMap: GoogleMap) {
        mMap.clear()
        checlClear = false
    }


//    internal inner class InfoWindowActivity : AppCompatActivity(),
//        GoogleMap.OnInfoWindowClickListener,
//        OnMapReadyCallback {
//        override fun onMapReady(googleMap: GoogleMap) {
//            // Add markers to the map and do other map setup.
//            mMap.setOnMapClickListener {
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
//                mMap.addMarker(MarkerOptions().position(it).title("Here ?"))
//                // ...
//                // Set a listener for info window events.
//                googleMap.setOnInfoWindowClickListener(this)
//            }
//        }
//
//            override fun onInfoWindowClick(marker: Marker) {
//                Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//

        /*
         fun DialogBuilder(){
             val dialogBuilder = AlertDialog.Builder(requireContext())
             val title = EditText(requireContext())
             val note = EditText(requireContext())
             dialogBuilder
                 .setCancelable(false)
                 .setPositiveButton("Save", DialogInterface.OnClickListener {
                         _, _ -> viewModel.updateCollege(CollegeTable(college.pk,college.name,college.countrey,updatedNote.text.toString()))
                 })
                 .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                         dialog, _ -> dialog.cancel()
                 })
             val alert = dialogBuilder.create()
             alert.setTitle("Update College Summary")
             alert.setView(updatedNote)
             alert.show()
         }
         */


    }
