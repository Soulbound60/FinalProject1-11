package com.example.finalproject1_11.Views

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproject1_11.Model.ArgsLocation
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.R
import com.example.finalproject1_11.ViewModel.LocationRV
import com.example.finalproject1_11.ViewModel.MainViewModel
import com.example.finalproject1_11.databinding.FragmentViewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import kotlin.math.pow
import kotlin.math.roundToInt

class ViewFragment : Fragment(), LocationRV.ClickListner {
    lateinit var binding: FragmentViewBinding
    lateinit var viewModel: MainViewModel
    var apiKey = "AIzaSyCYeqxPF4oX57ZOZKEQ_6oc2P2o-Z4eOBk"
    lateinit var locationManager: LocationManager
    var deviceLat = 24.801557
    var deviceLong = 46.677208

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(layoutInflater)


        val adapter = LocationRV(this)
        binding.locationRV.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //0 out 10 ?
        binding.mapFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_mapFragment)
        }
        binding.entryFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_entryFragment)
        }
        viewModel.getDB().observe(viewLifecycleOwner) { locationList ->
            adapter.submitList(locationList)
        }
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //  locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//------------------------------------------------------//
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        // vid
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fechLocation()
        //**********


        return binding.root

    }

    //@SuppressLint("MissingPermission")
    @SuppressLint("MissingPermission")
    override fun fechLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                //deviceLat = it.latitude
               // deviceLong = it.longitude

                Toast.makeText(requireContext(), "${it.latitude}${it.longitude}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
//    fun getDistance(distenation : String, lat:Double , lan : Double){
//
//        val apiClient = APIClient().getClient()
//        if (apiClient != null) {
//            // if the connection to the main page is good
//            //1-
//            val apiInterface = apiClient.create(Interface::class.java)
//            //2-
//            apiInterface.Search("makkah",).enqueue(object : Callback<College> {
//                override fun onResponse(call: Call<College>, response: Response<College>) {
//                    Log.d("TAG","Before the Null Check")
//                    val body = response.body()
//                    if (body != null) {
//                        Log.d("TAG","Before the loop")
//                        val collegelist1 = arrayListOf<CollegeItem>()
////                    val photosList = response.body()
//                        //mutableLiveCollege.postValue(collegelist1)
//                        for (user in body){
//                            Log.d("TAG","Inside the loop")
//                            CollegeList.add(user)
//                            Log.d("SOULBOUND","$user")
//                            mutableLiveCollege.postValue(CollegeList)
//                        }
//
//                    }
//                }
//                override fun onFailure(call: Call<College>, t: Throwable) {
//                    Log.d("TAG", "onFailure: ${t.message}")
//                }
//
//            })
//        }
//        }


    fun pupUpmenu() {

//        val popupMenu = PopupMenu(requireActivity(), binding.rvItems.findViewById(R.id.options))
//        // add the menu
//        popupMenu.inflate(R.menu.menu)
//        // implement on menu item click Listener
//        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.editbtn -> {
//                        dialogBuilder
//                            .setPositiveButton("Edit") { dialog, _ ->
//
//                                if (editTitle.text.toString().isEmpty() && editDesc.text.toString()
//                                        .isEmpty()
//                                ) {
//                                    editTitle.setError("Please provide a name.")
//                                    editTitle.requestFocus()
//                                    editDesc.setError("Please provide a name.")
//                                    editDesc.requestFocus()
//                                    Toast.makeText(
//                                        this@MainActivity,
//                                        "Need ti fill all boxes",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }// if
//                                else {
//                                    task.taskName = editTitle.text.toString()
//                                    task.taskDescription = editDesc.text.toString()
//                                    task.priority = color
//                                    viewModel.updateTask(task)
//                                    Toast.makeText(
//                                        this@MainActivity,
//                                        "Task has been updated",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//
//
//                                }//else
//
//                            }
//                            .setNegativeButton("Cancel") { dialog, _ ->
//                                dialog.dismiss()
//                            }
//                        val alert = dialogBuilder.create()
//                        alert.setTitle("Update Task")
//                        TotalTime()
//                        alert.setView(layout)
//                        alert.show()
//
//                        true
//                    }// edit
//                    R.id.deletebtn -> {
//                        /**set delete*/
//                        dialogBuilder
//                            .setTitle("Delete Confirmation")
//                            .setMessage("Are you sure delete this Task?")
//                            .setPositiveButton("Delete") { dialog, _ ->
//                                viewModel.deleteTask(task)
//                                Toast.makeText(
//                                    this@MainActivity,
//                                    "Deleted this Task",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                dialog.dismiss()
//                                TotalTime()
//                            }
//                            .setNegativeButton("No") { dialog, _ ->
//                                dialog.dismiss()
//                            }
//                            .create()
//                            .show()
//
//                        true
//                    } //delet item
//                }//when
//                return false
//            }// menu on click
//        })// obj
//        popupMenu.show()

    }

    override fun CalDistance(lat: Double, long: Double): Double {

        var distance = distance(lat, long, deviceLat.toDouble(), deviceLong.toDouble())
        Log.d("TRUTH","lat :$lat, long : $long , DLat $deviceLat , DLOng $deviceLong")
        var distance1 = distance.roundTo(3)
        return distance1
    }

    fun Double.roundTo(numFractionDigits: Int): Double {
        val factor = 10.0.pow(numFractionDigits.toDouble())
        return (this * factor).roundToInt() / factor
    }

    override fun deleteLocation(location: LocationTable) {
        viewModel.deleteLocation(location)
    }

    override fun direction(location: LocationTable) {
        val latitude0 = location.latitude
        val longitude0 = location.longitude
        val auction = ViewFragmentDirections.actionViewFragmentToMapFragment(
            ArgsLocation(
                latitude0,
                longitude0
            )
        )
        findNavController().navigate(auction)

    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + (Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta))))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        return dist * 1.6
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }


}