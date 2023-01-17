package com.example.finalproject1_11.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.databinding.LocationRowBinding


class LocationRV(var clickListner: ClickListner): ListAdapter<LocationTable, LocationRV.ViewHolder>(
    LocationDiffutil()
) {

    class ViewHolder(var binding: LocationRowBinding): RecyclerView.ViewHolder(binding.root){
    } //end item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LocationRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var location = getItem(position)

        holder.binding.apply {
//            nameTxt.text= location.name
//            summaryTxt.text= location.note
//            nameTxt.text= location.name


            }
        }

    }
    interface ClickListner{

        fun addCollege(location: LocationTable)

    }// interface
