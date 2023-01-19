package com.example.finalproject1_11.Model.DB

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {
  @GET("maps/api/distancematrix/json/")
  //https://maps.googleapis.com/maps/api/distancematrix/json
  //  ?destinations=New%20York%20City%2C%20NY
  //  &origins=Washington%2C%20DC
  //  &units=imperial
  //  &key=YOUR_API_KEY
 // https://maps.googleapis.com/maps/api/distancematrix/json?destinations=New%20York%20City%2C%20NY&origins=Washington%2C%20DC&units=imperial&key=AIzaSyCYeqxPF4oX57ZOZKEQ_6oc2P2o-Z4eOBk
    // this call will get us all what in the Query
    fun Search(@Query("destinations") destinations: String,@Query("origins") origins: String,@Query("units") units: String,@Query("key") key: String): Call<Distance>
}