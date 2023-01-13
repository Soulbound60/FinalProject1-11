package com.example.finalproject1_11.Model.DB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location")
data class LocationTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name : String,
    var note : String,
    var longitude : Long,
    var latitude : Long,
    var whatKind : String
    )