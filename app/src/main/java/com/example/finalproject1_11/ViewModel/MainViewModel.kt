package com.example.finalproject1_11.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finalproject1_11.Model.DB.LocationDAO
import com.example.finalproject1_11.Model.DB.LocationDataBase
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.Model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application): AndroidViewModel(application) {

    lateinit var repository: Repository
    private var locationDB : LocationDataBase
    private var dao : LocationDAO

    init {
        // data base initiate
        locationDB = LocationDataBase.getDataBase(application)
        dao = locationDB.LocationDAO()
        dao = locationDB.LocationDAO()
        repository = Repository(dao)

    }


    // SQL LOCAL DATA BASE FUNCTIONS

    // Live Data
    fun getDB(): LiveData<List<LocationTable>> {
        //CoroutineScope(Dispatchers.IO).launch {
        return repository.getDataBase()
    }

    suspend fun getList():List<LocationTable> {
        //CoroutineScope(Dispatchers.IO).launch {
        return repository.getList()
    }
    // Add
    fun addLocation(locationTable: LocationTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addLocation(locationTable)

        }
    }

    // Delete
    fun deleteLocation(locationTable: LocationTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteLocation(locationTable)
        }
    }

    // Update
    fun updateCollege(locationTable: LocationTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateLocation(locationTable)
        }
    }


}