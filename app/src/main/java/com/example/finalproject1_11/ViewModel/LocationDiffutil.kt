package com.example.finalproject1_11.ViewModel

import androidx.recyclerview.widget.DiffUtil
import com.example.finalproject1_11.Model.DB.LocationTable

class LocationDiffutil (): DiffUtil.ItemCallback<LocationTable>() {
    override fun areItemsTheSame(oldItem: LocationTable, newItem: LocationTable): Boolean {

        return false
    }
    override fun areContentsTheSame(oldItem: LocationTable, newItem: LocationTable): Boolean {

        return false

    } //end content


}