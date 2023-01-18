package com.example.finalproject1_11.Views

import android.location.Location
import android.location.LocationRequest
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras.Empty.equals
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproject1_11.Model.ArgsLocation
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.R
import com.example.finalproject1_11.ViewModel.LatLngFlag
import com.example.finalproject1_11.ViewModel.MainViewModel

import com.example.finalproject1_11.databinding.FragmentMapBinding
import com.example.finalproject1_11.databinding.FragmentViewBinding
import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentMapBinding
    lateinit var binding1 : FragmentViewBinding
    lateinit var mMap: GoogleMap
    lateinit var x: LatLngFlag
    lateinit var viewModel: MainViewModel
    var checlClear = false
     private val args by navArgs<MapFragmentArgs>()


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
        val lati = args.cordinates?.lati
        val longi = args.cordinates?.longi
        //Log.d("ABDULLAZIZ","$lati. $longi")
        Log.d("ABDULLAZIZ"," $lati $longi")
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.fBtn.setOnClickListener {
            if (checlClear){
               popUpMenu(x)
            }else{
                Toast.makeText(requireActivity(),"Pls Pick a Location", Toast.LENGTH_SHORT).show()
            }
        }
        binding.homeFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_viewFragment)
        }

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
//            mMap.addMarker(MarkerOptions().position(it).title("Here ?"))
//            checlClear = true
            if (checlClear == false) {
                mMap.addMarker(MarkerOptions().position(it).title("Here ?"))
                x = LatLngFlag(it.latitude, it.longitude, true)
                checlClear = true
                Log.d("TAG", "newLatLAng ${x.lat} ${x.lng}")
            } else {
                putMarker(mMap)
                Log.d("TAG", "marker deleted $checlClear")
            }



        }
        if (args.cordinates!=null){
            val lati = args.cordinates!!.lati
            val longi = args.cordinates!!.longi
            Log.d("ABDULLAZIZ","afterNull $lati $longi")
            var detour = LatLng(lati,longi)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(detour))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
            mMap.addMarker(MarkerOptions().position(detour).title("Here ?"))
            checlClear = true

            makkah = LatLng(lati, longi)
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



    fun popUpMenu(x: LatLngFlag) {
        val inflater = LayoutInflater.from(requireActivity())
        val layout = inflater.inflate(R.layout.dialog_pop,null)
        // Button's
        var type = " "
        var cancelBtn = layout.findViewById<Button>(R.id.cancelBtn)
        var saveBtn = layout.findViewById<Button>(R.id.saveBtn)
        // Text's
        var title = layout.findViewById<TextView>(R.id.title)
        title.text = "Add New Location"
        var locationName = layout.findViewById<EditText>(R.id.locationName)
        var discription = layout.findViewById<EditText>(R.id.descriptionTxt)
        // img's
        var resturantIMG = layout.findViewById<ImageView>(R.id.resturantIMG)
        var familyIMG = layout.findViewById<ImageView>(R.id.familyIMG)
        var houseIMG = layout.findViewById<ImageView>(R.id.homeIMG)
        var viktorHouse =
        resturantIMG.setOnClickListener {
            type = "restaurant"
            resturantIMG.setImageResource(R.drawable.estaurant1)
            // changing other twoo
            familyIMG.setImageResource(R.drawable.family)
            houseIMG.setImageResource(R.drawable.house)
            Log.d("TAGTAG","should be restaurant : $type")
        }
        familyIMG.setOnClickListener {
            type = "family"
            familyIMG.setImageResource(R.drawable.family1)
            // changing other twoo
            resturantIMG.setImageResource(R.drawable.restaurant)
            houseIMG.setImageResource(R.drawable.house)
            Log.d("TAGTAG","should be family : $type")
        }
        houseIMG.setOnClickListener {
            type = "house"
            houseIMG.setImageResource(R.drawable.home_image1)
            // changing other twoo
            resturantIMG.setImageResource(R.drawable.restaurant)
            familyIMG.setImageResource(R.drawable.family)
            Log.d("TAGTAG","should be house : $type")
        }

        val dialogBuilder = AlertDialog.Builder(requireActivity())
                var dialog = dialogBuilder.create()

        saveBtn.setOnClickListener {
            if (locationName.text.isNotEmpty()&&discription.text.isNotEmpty())
            addLocation(LocationTable(0,locationName.text.toString(), discription.text.toString(),x.lng,x.lat,type)

            )else{
                Toast.makeText(requireActivity(),"fill the the info pls",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.dismiss()
        }
       cancelBtn.setOnClickListener {
           dialog.dismiss()
       }



        //val alert = dialogBuilder.create()
        dialog.setView(layout)
        //alert.setTitle("Test")
        dialog.show()
    } //pop up fun.
    fun addLocation(location: LocationTable){
        viewModel.addLocation(location)
    }
}



