package com.example.finalproject1_11.Model

import androidx.lifecycle.LiveData
import com.example.finalproject1_11.Model.DB.LocationDAO
import com.example.finalproject1_11.Model.DB.LocationTable

class Repository (var Dao : LocationDAO) {
    //location
    suspend fun addLocation(location: LocationTable){
        Dao.addLocation(location)
    }

    suspend fun getList():List<LocationTable>{
        return Dao.getlist()
    }

    suspend fun deleteLocation(location: LocationTable){
        Dao.deleteLocation(location)
    }

    suspend fun updateLocation(location: LocationTable){
        Dao.updateLocation(location)

    }
    fun getDataBase(): LiveData<List<LocationTable>> {
        return Dao.getLiveDB()
    }// Done
}