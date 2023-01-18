package com.example.finalproject1_11.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.R
import com.example.finalproject1_11.Views.ViewFragment
import com.example.finalproject1_11.databinding.FragmentViewBinding
import com.example.finalproject1_11.databinding.LocationRowBinding


class LocationRV(var Click: ViewFragment) : ListAdapter<LocationTable, LocationRV.ViewHolder>(
    LocationDiffutil()
) {


    class ViewHolder(var binding: LocationRowBinding) : RecyclerView.ViewHolder(binding.root) {
    } //end item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LocationRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var location = getItem(position)

        holder.binding.apply {
            nameTxt.text = location.name
            summaryTxt.text = location.note
            nameTxt.text = location.name
            direction.setImageResource(R.drawable.directions)
            deleteImg.setImageResource(R.drawable.garbage)
            if (location.whatKind == "restaurant") {
                //typeIMG.setImageResource(R.drawable.resturant)
                //typeIMG.setImageDrawable(R.drawable.resturant.toDrawable())
                typeIMG.setImageResource(R.drawable.location1)
            }
            if (location.whatKind == "family") {
                // typeIMG.setImageDrawable(R.drawable.gathering.toDrawable())
                typeIMG.setImageResource(R.drawable.location1)
            }
            if (location.whatKind == "house") {
                typeIMG.setImageResource(R.drawable.location1)
                //typeIMG.setImageDrawable(R.drawable.directions.toDrawable())
            }
            deleteImg.setOnClickListener {
                Click.deleteLocation(location)
            }
            direction.setOnClickListener {
                Click.direction(location)
            }
        }

    }

    interface ClickListner {

        fun deleteLocation(location: LocationTable)
        fun direction(location: LocationTable)

    }

}
// interface

