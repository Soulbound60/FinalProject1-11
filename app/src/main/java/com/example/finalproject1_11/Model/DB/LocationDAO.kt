package com.example.finalproject1_11.Model.DB

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface LocationDAO {
    @Query("select * from location order by id")
    fun getLiveDB(): LiveData<List<LocationTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLocation(location : LocationTable)

    @Delete
    suspend fun deleteLocation(location : LocationTable)

    @Update
    suspend fun updateLocation(location : LocationTable)
}