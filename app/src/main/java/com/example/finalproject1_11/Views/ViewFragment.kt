package com.example.finalproject1_11.Views

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.finalproject1_11.Model.ArgsLocation
import com.example.finalproject1_11.Model.DB.LocationTable
import com.example.finalproject1_11.R
import com.example.finalproject1_11.ViewModel.LocationRV
import com.example.finalproject1_11.ViewModel.MainViewModel
import com.example.finalproject1_11.databinding.FragmentViewBinding

class ViewFragment : Fragment(), LocationRV.ClickListner {
    lateinit var binding: FragmentViewBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(layoutInflater)


        val adapter = LocationRV(this)
        binding.locationRV.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.mapFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_mapFragment)
        }
        binding.entryFrag.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_entryFragment)
        }
        viewModel.getDB().observe(viewLifecycleOwner) { locationList ->
            adapter.submitList(locationList)
        }

        return binding.root

    }


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

    override fun deleteLocation(location: LocationTable) {
        viewModel.deleteLocation(location)
    }

    override fun direction(location: LocationTable) {
        val latitude0 = location.latitude
        val longitude0 = location.longitude
        val auction =  ViewFragmentDirections.actionViewFragmentToMapFragment(ArgsLocation(latitude0,longitude0))
        findNavController().navigate(auction)

    }
}