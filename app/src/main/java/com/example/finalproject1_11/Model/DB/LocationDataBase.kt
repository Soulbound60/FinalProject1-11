package com.example.finalproject1_11.Model.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LocationTable::class], version = 1, exportSchema = false)
abstract class LocationDataBase(): RoomDatabase(){
    abstract fun collegeDAO(): LocationDAO

    companion object // type this just to look smart
    {
        private var INSTANCE : LocationDataBase?=null

        fun getDataBase(context: Context): LocationDataBase
        {
            var tempInstance = INSTANCE
            if (tempInstance !=  null)
            {
                return tempInstance
            }
            tempInstance = Room.databaseBuilder(context.applicationContext,
                LocationDataBase::class.java,"LocationsDataBase")
                .fallbackToDestructiveMigration()
                .build()


            return tempInstance
        }

    }
}